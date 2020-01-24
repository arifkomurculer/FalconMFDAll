Imports System.Threading
Imports System.IO
Imports F4SharedMem
Imports System.Net
Imports System.Net.Sockets
Imports System.Text

''' <summary>
''' X Coordinate
''' Y Coordinate
''' Heading
''' </summary>
''' <remarks></remarks>
Public Class clsReadMemoryThreadFlightDataNavMap
    Private _reader As New Reader()
    Private vrIpPortInfo() As String

    Public Sub New(ByVal ThreadIndex As Integer, ByRef MainWindow As frmServer, prIpPortInfo() As String)
        'Yeni
        vrIpPortInfo = prIpPortInfo
    End Sub


    Dim vrFlightData(20) As String
    Dim vrFlightDataTest(20) As String
    Dim vrIndex As Integer

    '1  -   XCoordinate
    '2  -   YCoordinate
    '3  -   currentHeading


    Public Sub StartThreadFlightDataNavMap()

        


        Dim vrMsg As String

        Dim _keepRunning As Boolean = False
        Dim m As Integer

        Dim vrAyirac() As String


        Dim vrX, vrY, vrH As String

        For m = 0 To 15
            vrFlightData(m) = ""
        Next


        Dim vrHarita As String = vrIpPortInfo(4)


        Using reader As New Reader(FalconDataFormats.BMS4)
            _keepRunning = True
            While _keepRunning
                If reader.IsFalconRunning Then
                    Dim sharedMem As FlightData = reader.GetCurrentData()

                    'YCoordinate
                    vrIndex = 2
                    vrAyirac = Split(sharedMem.x.ToString, ",")
                    vrAyirac = Split(vrAyirac(0), ".")
                    vrY = vrAyirac(0)

                    'XCoordinate 
                    vrIndex = 1
                    vrAyirac = Split(sharedMem.y.ToString, ",")
                    vrAyirac = Split(vrAyirac(0), ".")
                    vrX = vrAyirac(0)



                    '------------------------ YCoordinate ---------------------------
                    vrIndex = 2
                    If vrHarita = "EMF" Then
                        vrFlightData(vrIndex) = Trim(vrAyirac(0))
                        YCoordinate = vrWidthHeight - (vrY * vrWidthHeight / YMaxEMF)
                        vrFlightData(vrIndex) = Math.Round(YCoordinate, 0)
                        'SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "YCoordinate" & "@" & Math.Round(YCoordinate, 0))
                    Else
                        vrFlightData(vrIndex) = Trim(vrAyirac(0))
                        YCoordinate = vrWidthHeight - (vrY - YMin) / ((YMax - YMin) / vrWidthHeight)
                        vrFlightData(vrIndex) = Math.Round(YCoordinate, 0)
                        'SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "YCoordinate" & "@" & Math.Round(YCoordinate, 0))
                    End If



                    '------------------------ XCoordinate ---------------------------
                    vrIndex = 1
                    If vrHarita = "EMF" Then
                        vrFlightData(vrIndex) = Trim(vrAyirac(0))
                        XCoordinate = (vrX * vrWidthHeight / XMaxEMF)
                        'SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "XCoordinate" & "@" & Math.Round(XCoordinate, 0))
                        vrFlightData(vrIndex) = Math.Round(XCoordinate, 0)
                    Else
                        vrFlightData(vrIndex) = Trim(vrAyirac(0))
                        XCoordinate = (vrWidthHeight) / (XMax - XMin) * (vrX - XMin)
                        vrFlightData(vrIndex) = Math.Round(XCoordinate, 0)
                        'SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "XCoordinate" & "@" & Math.Round(XCoordinate, 0))
                    End If


                    'Heading
                    vrIndex = 3
                    vrFlightData(vrIndex) = Math.Round(sharedMem.currentHeading, 0)


                    vrMsg = "FlightDataNavMap" & "@" & vrFlightData(1) & "@" & vrFlightData(2) & "@" & vrFlightData(3)
                    SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), vrMsg)

                    System.Threading.Thread.Sleep(150)
                    Application.DoEvents()
                End If
            End While
        End Using


    End Sub

    

    Public Sub SendMessage(ByVal Ip As String, ByVal Port As Integer, ByVal Message As String)
        'Send TCP
        Try
            Dim client As New System.Net.Sockets.TcpClient
            Dim Buffer() As Byte = System.Text.Encoding.Default.GetBytes(Message.ToCharArray)
            client.Connect(Ip, Port)
            client.GetStream.Write(Buffer, 0, Buffer.Length)
            client.Close()
        Catch ex As Exception
            'MsgBox("Unable to connect to IP Adress")
            'FrmServer.fnkStarStopRWR("Stop")
        End Try
    End Sub

    'Yeni
    Public Function imageToByteArray(imageIn As System.Drawing.Image) As Byte()
        Dim ms As New MemoryStream()
        imageIn.Save(ms, System.Drawing.Imaging.ImageFormat.Gif)
        Return ms.ToArray()
    End Function


    Protected Overrides Sub Finalize()
        _reader.Dispose()
        MyBase.Finalize()
    End Sub


End Class
