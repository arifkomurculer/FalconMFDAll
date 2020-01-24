Imports System.Threading
Imports System.IO
Imports F4SharedMem
Imports System.Net
Imports System.Net.Sockets
Imports System.Text

Public Class clsReadMemoryThreadFlightDataLoad
    Private _reader As New Reader()
    Private vrIpPortInfo() As String

    Public Sub New(ByVal ThreadIndex As Integer, ByRef MainWindow As frmServer, prIpPortInfo() As String)
        'Yeni
        vrIpPortInfo = prIpPortInfo
    End Sub


    Dim vrFlightData(20) As String
    Dim vrFlightDataTest(20) As String
    Dim vrIndex As Integer
    'Private vrWidthHeight As Integer = 4096

    '
    '1  -   Flare
    '2  -   Chaff
    '3  -   Yakıt Miktarı
    '4  -   Fuel Flow
    '5  -   currentHeading
    '6  -   desCrs
    '7  -   brng2Bnc
    '8  -   crsDev
    '9  -   distanceToBeacon
    '10 -   Nav Mode
    '11 -   AirBreak

    Public Sub StartThreadFlightDataLoad()

        Dim _keepRunning As Boolean = False
        Dim m As Integer
        Dim vrMsg As String

        For m = 0 To 15
            vrFlightData(m) = ""
        Next

        SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Msg@" + "Info@" + "Flight Load Data Reading Started")

        Using reader As New Reader(FalconDataFormats.BMS4)
            _keepRunning = True



            While _keepRunning
                If reader.IsFalconRunning Then
                    Dim sharedMem As FlightData = reader.GetCurrentData()

                    'Flare Count
                    vrIndex = 1
                    vrFlightData(vrIndex) = sharedMem.FlareCount
                    If Val(vrFlightData(vrIndex)) < 0 Then
                        vrFlightData(vrIndex) = 0
                    End If


                    'Chaff Count
                    vrIndex = 2
                    vrFlightData(vrIndex) = sharedMem.ChaffCount
                    If Val(vrFlightData(vrIndex)) < 0 Then
                        vrFlightData(vrIndex) = 0
                    End If



                    'Yakıt Miktarı
                    vrIndex = 3
                    vrFlightData(vrIndex) = CInt(sharedMem.internalFuel + sharedMem.externalFuel)


                    'Fuel Flow
                    vrIndex = 4
                    vrFlightData(vrIndex) = CInt(sharedMem.fuelFlow)

                    'Heading
                    vrIndex = 5
                    vrFlightData(vrIndex) = Math.Round(sharedMem.currentHeading, 0)

                    'desCrs
                    vrIndex = 6
                    vrFlightData(vrIndex) = CInt(sharedMem.desiredCourse)

                    'brng2Bnc
                    vrIndex = 7
                    vrFlightData(vrIndex) = CInt(sharedMem.bearingToBeacon)

                    'crsDev
                    vrIndex = 8
                    vrFlightData(vrIndex) = CInt(sharedMem.courseDeviation)

                    'distanceToBeacon
                    vrIndex = 9
                    vrFlightData(vrIndex) = CInt(sharedMem.distanceToBeacon)

                    'Nav Mode
                    vrIndex = 10
                    vrFlightData(vrIndex) = sharedMem.navMode

                    'Speed Breake
                    vrIndex = 11
                    vrFlightData(vrIndex) = sharedMem.speedBrake
                    If vrFlightData(vrIndex) > 0 Then
                        vrFlightData(vrIndex) = 1
                    End If


                    vrMsg = "FlightDataLoad" & "@" _
                                             & vrFlightData(1) _
                                             & "@" & vrFlightData(2) _
                                             & "@" & vrFlightData(3) _
                                             & "@" & vrFlightData(4) _
                                             & "@" & vrFlightData(5) _
                                             & "@" & vrFlightData(6) _
                                             & "@" & vrFlightData(7) _
                                             & "@" & vrFlightData(8) _
                                             & "@" & vrFlightData(9) _
                                             & "@" & vrFlightData(10) _
                                             & "@" & vrFlightData(11)

                    SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), vrMsg)

                    System.Threading.Thread.Sleep(200)
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

