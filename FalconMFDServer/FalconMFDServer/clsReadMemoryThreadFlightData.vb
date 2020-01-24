Imports System.Threading
Imports System.IO
Imports F4SharedMem
Imports System.Net
Imports System.Net.Sockets
Imports System.Text

Public Class clsReadMemoryThreadFlightData
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

    'Test Amaçlı

    '0  -   Speed Break
    '1  -   Flare
    '2  -   Chaff
    '3  -   gearPos
    '4  -   Flare Low
    '5  -   Chaff Low
    '6  -   XCoordinate
    '7  -   YCoordinate
    '8  -   currentHeading
    '9  -   Rpm
    '10 -   Yakıt Miktarı
    '11 -   Fuel Flow
    '12 -   kias
    '13 -   aauz
    '14 -   curHdg, desCrs, brng2Bnc, crsDev

    Public Sub StartThreadFlightData()

        Dim curHdg As Double = 0
        Dim desCrs As Double = 0
        Dim brng2Bnc As Double = 0
        Dim crsDev As Double = 0


        Dim XCoordinate As Double = 0
        Dim YCoordinate As Double = 0

        Dim XMax As Double = 3332460
        Dim XMin As Double = 0

        Dim YMax As Double = 3358700
        Dim YMin As Double = 26239


        Dim _keepRunning As Boolean = False
        Dim m As Integer

        Dim vrAyirac() As String


        Dim vrX, vrY, vrH As String

        For m = 0 To 15
            vrFlightData(m) = ""
        Next

        Using reader As New Reader(FalconDataFormats.BMS4)
            _keepRunning = True
            While _keepRunning
                If reader.IsFalconRunning Then
                    Dim sharedMem As FlightData = reader.GetCurrentData()


                    'Silinecek
                    'SendMessageTofrmServer(sharedMem.AUXTChan.ToString )


                    'Speed Breake
                    vrIndex = 0
                    'MsgBox(vrFlightData(vrIndex))
                    If vrFlightData(vrIndex) <> sharedMem.speedBrake.ToString Then
                        vrFlightData(vrIndex) = sharedMem.speedBrake

                        If vrFlightData(vrIndex) > 0 Then
                            vrFlightData(vrIndex) = 1
                        End If

                        SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "SpeedBreak" & "@" & vrFlightData(vrIndex))
                    End If



                    'Flare Count
                    vrIndex = 1
                    If (vrFlightData(vrIndex) <> sharedMem.FlareCount.ToString) Then
                        vrFlightData(vrIndex) = sharedMem.FlareCount
                        If Val(vrFlightData(vrIndex)) >= 0 Then
                            SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "FlareCount" & "@" & vrFlightData(vrIndex))
                        Else
                            SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "FlareCount" & "@" & 0)
                        End If

                    End If

                    'Chaff Count
                    vrIndex = 2
                    If vrFlightData(vrIndex) <> sharedMem.ChaffCount.ToString Then
                        vrFlightData(vrIndex) = sharedMem.ChaffCount
                        If Val(vrFlightData(vrIndex)) >= 0 Then
                            SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "ChaffCount" & "@" & vrFlightData(vrIndex))
                        Else
                            SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "ChaffCount" & "@" & 0)
                        End If
                    End If

                    'GearPos 0 : Up 1 : Down
                    vrIndex = 3
                    If vrFlightData(vrIndex) <> sharedMem.gearPos.ToString Then
                        'GearPos , Ön , Sol , Sağ
                        vrFlightData(vrIndex) = sharedMem.gearPos

                        If vrFlightData(vrIndex) > 0 Then
                            vrFlightData(vrIndex) = 1 & "#" & sharedMem.NoseGearPos & "#" & sharedMem.LeftGearPos & "#" & sharedMem.RightGearPos
                        Else
                            vrFlightData(vrIndex) = 0 & "#" & sharedMem.NoseGearPos & "#" & sharedMem.LeftGearPos & "#" & sharedMem.RightGearPos
                        End If
                        SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "GearPos" & "@" & vrFlightData(vrIndex))
                    End If

                    'YCoordinate
                    vrIndex = 6
                    vrAyirac = Split(sharedMem.x.ToString, ",")
                    vrAyirac = Split(vrAyirac(0), ".")
                    vrY = vrAyirac(0)

                    'XCoordinate 
                    vrIndex = 7
                    vrAyirac = Split(sharedMem.y.ToString, ",")
                    vrAyirac = Split(vrAyirac(0), ".")
                    vrX = vrAyirac(0)

                    'Heading
                    vrIndex = 8
                    vrAyirac = Split(sharedMem.distanceToBeacon.ToString, ",")
                    vrAyirac = Split(vrAyirac(0), ".")
                    vrH = vrAyirac(0)

                    '------------------------ YCoordinate ---------------------------
                    vrIndex = 6
                    If vrFlightData(6) <> Trim(vrY) Then
                        vrFlightData(vrIndex) = Trim(vrAyirac(0))
                        YCoordinate = vrWidthHeight - (vrY - YMin) / ((YMax - YMin) / vrWidthHeight)
                        SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "YCoordinate" & "@" & Math.Round(YCoordinate, 0))
                    End If

                    '------------------------ XCoordinate ---------------------------
                    vrIndex = 7
                    If vrFlightData(7) <> Trim(vrX) Then
                        vrFlightData(vrIndex) = Trim(vrAyirac(0))
                        XCoordinate = (vrWidthHeight) / (XMax - XMin) * (vrX - XMin)
                        SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "XCoordinate" & "@" & Math.Round(XCoordinate, 0))
                    End If

                    '------------------------ Heading ---------------------------
                    vrIndex = 8
                    If vrFlightData(8) <> Trim(vrH) Then
                        vrFlightData(vrIndex) = Trim(vrH)
                        SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "CurrentHeading" & "@" & vrFlightData(vrIndex))
                    End If


                    'RPM
                    vrIndex = 9
                    If vrFlightData(vrIndex) <> sharedMem.rpm.ToString Then
                        vrFlightData(vrIndex) = Mid(sharedMem.rpm - 2, 1, 5)
                        SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "RPM" & "@" & vrFlightData(vrIndex))
                    End If

                    'Yakıt Miktarı
                    vrIndex = 10
                    If vrFlightData(vrIndex) <> CStr(sharedMem.internalFuel + sharedMem.externalFuel) Then
                        vrFlightData(vrIndex) = CInt(sharedMem.internalFuel + sharedMem.externalFuel)
                        SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "TotalFuel" & "@" & vrFlightData(vrIndex))
                    End If

                    'Fuel Flow
                    vrIndex = 11
                    If vrFlightData(vrIndex) <> sharedMem.fuelFlow.ToString Then
                        vrFlightData(vrIndex) = CInt(sharedMem.fuelFlow)
                        SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "FuelFlow" & "@" & vrFlightData(vrIndex))
                    End If

                    'kias : 	// Ownship Indicated Airspeed (Knots)
                    vrIndex = 12
                    If vrFlightData(vrIndex) <> sharedMem.kias.ToString Then
                        vrFlightData(vrIndex) = CInt(sharedMem.kias)
                        SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "kias" & "@" & vrFlightData(vrIndex))
                    End If

                    'aauz : //AAU altimeter indicated altitude (new in BMS4)
                    vrIndex = 13
                    If vrFlightData(vrIndex) <> sharedMem.aauz.ToString Then
                        vrFlightData(vrIndex) = CInt(sharedMem.aauz)
                        SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "aauz" & "@" & vrFlightData(vrIndex) * -1)
                    End If

                    'curHdg, desCrs, brng2Bnc, crsDev
                    vrIndex = 14
                    If vrFlightData(vrIndex) <> "curHdg" & "~" & sharedMem.currentHeading.ToString & "@" & "desCrs" & "~" & sharedMem.desiredCourse.ToString & "@" & "brng2Bnc" & "~" & sharedMem.bearingToBeacon.ToString &
                    "crsDev" & "~" & sharedMem.courseDeviation.ToString Then
                        vrFlightData(vrIndex) = "curHdg" & "~" & sharedMem.currentHeading.ToString & "@" & "desCrs" & "~" & sharedMem.desiredCourse.ToString & "@" & "brng2Bnc" & "~" & sharedMem.bearingToBeacon.ToString &
                    "crsDev" & "~" & sharedMem.courseDeviation.ToString
                        SendMessage(vrIpPortInfo(0), vrIpPortInfo(1), "Data" & "@" & "HSI" & "@" & vrFlightData(vrIndex))
                    End If


                    'System.Threading.Thread.Sleep(vrIpPortInfo(3))
                    System.Threading.Thread.Sleep(300)
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
