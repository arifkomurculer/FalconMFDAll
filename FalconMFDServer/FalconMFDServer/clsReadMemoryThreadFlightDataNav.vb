Imports System.Threading
Imports System.IO
Imports F4SharedMem
Imports System.Net
Imports System.Net.Sockets
Imports System.Text

''' <summary>
''' Air Break
''' Gear Position
''' Speed
''' Altitute
''' Head
''' </summary>
''' <remarks></remarks>
Public Class clsReadMemoryThreadFlightDataNav
    'Private _reader As New Reader()
    Private vrIpPortInfo() As String

    Public Sub New(ByVal ThreadIndex As Integer, ByRef MainWindow As frmServer, prIpPortInfo() As String)
        'Yeni
        vrIpPortInfo = prIpPortInfo
    End Sub


    Dim vrFlightData(20) As String
    Dim vrFlightDataTest(20) As String
    Dim vrIndex As Integer
    'Private vrWidthHeight As Integer = 4096

    '       1  -  Speed Break
    '       2  -  gearPos
    '       3  -  Nav Mode
    '       4  -  currentHeading
    '       5 -   kias
    '       6 -   aauz
    '       7 -   desCrs
    '       8 -   brng2Bnc
    '       9 -   crsDev
    '       10-   distanceToBeacon

    Public Sub StartThreadFlightDataNav()

        Dim _keepRunning As Boolean = False
        Dim m As Integer
        Dim vrMsg As String


        For m = 0 To 15
            vrFlightData(m) = ""
        Next

        Using reader As New Reader(FalconDataFormats.BMS4)
            _keepRunning = True
            While _keepRunning
                If reader.IsFalconRunning Then
                    Dim sharedMem As FlightData = reader.GetCurrentData()

                    'Speed Breake
                    vrIndex = 1
                    vrFlightData(vrIndex) = sharedMem.speedBrake
                    If vrFlightData(vrIndex) > 0 Then
                        vrFlightData(vrIndex) = 1
                    End If

                    'GearPos 0 : Up 1 : Down
                    vrIndex = 2
                    vrFlightData(vrIndex) = sharedMem.gearPos

                    If vrFlightData(vrIndex) > 0 Then
                        vrFlightData(vrIndex) = 1
                    End If

                    'Nav Mode
                    vrIndex = 3
                    vrFlightData(vrIndex) = sharedMem.navMode


                    'Heading
                    vrIndex = 4

                    vrFlightData(vrIndex) = Math.Round(sharedMem.currentHeading, 0)

                    'kias : 	// Ownship Indicated Airspeed (Knots)
                    vrIndex = 5
                    vrFlightData(vrIndex) = CInt(sharedMem.kias)

                    'aauz : //AAU altimeter indicated altitude (new in BMS4)
                    vrIndex = 6
                    vrFlightData(vrIndex) = -1 * CInt(sharedMem.aauz)

                    'desCrs
                    vrIndex = 7
                    vrFlightData(vrIndex) = CInt(sharedMem.desiredCourse)

                    'brng2Bnc
                    vrIndex = 8
                    vrFlightData(vrIndex) = CInt(sharedMem.bearingToBeacon)

                    'crsDev
                    vrIndex = 9
                    vrFlightData(vrIndex) = CInt(sharedMem.courseDeviation)

                    '       1  -  Speed Break
                    '       2  -  gearPos
                    '       3  -  Nav Mode
                    '       4  -  currentHeading
                    '       5 -   kias
                    '       6 -   aauz
                    '       7 -   desCrs
                    '       8 -   brng2Bnc
                    '       9 -   crsDev
                    '       10- distanceToBeacon

                    'vrFlightData(vrIndex) = Replace(Math.Round(sharedMem.currentHeading, 0) & "@" & sharedMem.desiredCourse & "@" & sharedMem.bearingToBeacon &
                    ' "@" & sharedMem.courseDeviation.ToString & "@" & Math.Round(sharedMem.distanceToBeacon, 0), ",", ".")

                    vrIndex = 10
                    vrFlightData(vrIndex) = CInt(sharedMem.distanceToBeacon)

                    vrMsg = "FlightDataNav" & "@" & vrFlightData(1) & "@" & vrFlightData(2) & "@" & vrFlightData(3) & "@" & vrFlightData(4) & "@" & vrFlightData(5) & "@" & vrFlightData(6) & "@" & vrFlightData(7) & "@" & vrFlightData(8) & "@" & vrFlightData(9) & "@" & vrFlightData(10)

                    SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), vrMsg)

                    'System.Threading.Thread.Sleep(vrIpPortInfo(3))
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
        '_reader.Dispose()
        MyBase.Finalize()
    End Sub


End Class
