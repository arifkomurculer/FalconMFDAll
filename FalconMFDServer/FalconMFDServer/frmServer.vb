Imports System
Imports System.Drawing
Imports System.Threading
Imports System.Windows.Forms
Imports F4TexSharedMem
Imports System.IO
Imports System.Xml
Imports System.Net
Imports System.Net.Sockets
Imports System.Text
Imports System.Diagnostics
Imports Nini.Config 'ini file için
Imports Microsoft.Win32
Imports System.Net.NetworkInformation

Partial Public Class frmServer
    Inherits Form

    Private ReadOnly _reader As New Reader()
    Private _closing As Boolean

    Public m_ThreadList As New ArrayList

    'SolMFD				m_ThreadList(0)
    'SagMFD				m_ThreadList(1)
    'DED 				m_ThreadList(2)
    'RWR				m_ThreadList(3)
    'Test				m_ThreadList(4)
    'FlightData			m_ThreadList(5)
    'FlightDataLoad		m_ThreadList(6)
    'FlightDataNav		m_ThreadList(7)
    'FlightDataNavMap	m_ThreadList(8)

    Dim vrSolMFD(2, 4) As Integer '0 : WinXP/7  1 : Win 8

    Dim vrSagMFD(2, 4) As Integer '0 : WinXP/7  1 : Win 8

    Dim vrRWRoP(2, 4) As Integer '0 : WinXP/7  1 : Win 8

    Dim vrICPop(2, 4) As Integer '0 : WinXP/7  1 : Win 8

    Public vrSol(6) As String
    Public vrSag(6) As String
    Public vrDED(6) As String
    Public vrRWR(6) As String
    Public vrFlightData(6) As String
    Public vrFlightDataLoad(6) As String
    Public vrFlightDataNav(6) As String
    Public vrFlightDataNavMap(6) As String


    Public vrINISource As IniConfigSource
    Dim vrModifiedDataINI As String = ""

    Dim vrFalconPath As String = ""
    Dim exePath As String = ""

    Dim WithEvents Srv As ServerClass
    Dim vrAcildi As Boolean = False

    Public Sub GetLocalIPv4()
        Dim output As String = ""
        For Each item As NetworkInterface In NetworkInterface.GetAllNetworkInterfaces()
            If item.OperationalStatus = OperationalStatus.Up Then
                For Each ip As UnicastIPAddressInformation In item.GetIPProperties().UnicastAddresses
                    If ip.Address.AddressFamily = AddressFamily.InterNetwork Then
                        grdIPList.Rows.Add(ip.Address.ToString())
                    End If
                Next
            End If
        Next

        grdIPList.Sort(grdIPList.Columns(0), SortOrder.Ascending)
    End Sub

    Public Sub New()
        InitializeComponent()
    End Sub



    Private Sub frmServer_FormClosing(sender As Object, e As FormClosingEventArgs) Handles MyBase.FormClosing
        _closing = True
        If _reader IsNot Nothing Then
            Try
                _reader.Dispose()
            Catch generatedExceptionName As Exception
            End Try
        End If
    End Sub


    Private Sub frmServer_Load(sender As Object, e As EventArgs) Handles MyBase.Load


        fnkHataKayit("Falcon MFD", "Başladı", False)
        'StartThreadFlightDataNavMap Bu işlem başladığında aşağıdakileri yapacak
        cmbAirCraft.SelectedIndex = 0
        cmbBMSVerion.SelectedIndex = 0

        gbTest.Left = 3

        GetLocalIPv4()

    End Sub

    Private Sub frmServer_Shown(sender As Object, e As EventArgs) Handles Me.Shown



        'pnlResim.Top = 44

        'Sıkıntılı Threadleri sorun yapmayacak
        Control.CheckForIllegalCrossThreadCalls = False

        Srv = New ServerClass(ServerPort.Value, False)
        LocalIPLabel.Text = Srv.LocalIP

        Try

            exePath = Application.StartupPath()
            If My.Computer.FileSystem.FileExists(exePath & "/FalconBMSDatas.xml") Then
                'MsgBox("File found.")               
            Else

                If vrFalconPath = "" Then

                    ofDB.DefaultExt = "keystrokes.key"
                    ofDB.Title = "Please Select Key Stroke File      " & vbCrLf & "Path : ...\Falcon BMS 4.32\User\Config\....key (i.e.: keystrokes.key)"
                    ofDB.InitialDirectory = vrFalconPath & "\User\Config"


                    If ofDB.ShowDialog = DialogResult.OK Then
                        Dim fi As New FileInfo(ofDB.FileName)
                        vrFalconPath = Replace(fi.Directory.ToString, "\User\Config", "")

                        Me.Text = "Falcon MFD Server" & " ( Falcon MFD Path :  " & vrFalconPath & ")"
                    Else
                        Exit Sub
                    End If

                End If

                'MsgBox("File not found.")
                fnkFalconMFDDataXMLOlustur()
            End If


            'Son Açılış Versiyonunu Alacak

            cmbBMSVerion.Text = fnkFalconMFDDataXMLDegeriOku("KeyName", "FalconBMSVersion", "KeyValue")

        Catch ex As Exception

        End Try

        Try
            fnkGetFalconInstalledPath(cmbBMSVerion.Text)
        Catch ex As Exception

        End Try




        Try
            fnkXMLOku()
        Catch ex As Exception
            'MsgBox("Please Select KeyStroke File")
            fnkFindKeyStrokeFile()

            Try
                fnkXMLOku()
            Catch ex1 As Exception
                Exit Sub
            End Try

        End Try


        txtIp.Text = fnkFalconMFDDataXMLDegeriOku("KeyName", "AndroidIP", "KeyValue")
        vrAcildi = True

        'CallSign.ini güncellendiyse tekrardan okunacak
        If vrModifiedDataINI <> fnkGetLatestModifiedDate(fnkFalconMFDDataXMLDegeriOku("KeyName", "CallSign", "KeyValuePath")) Then
            vrModifiedDataINI = fnkGetLatestModifiedDate(fnkFalconMFDDataXMLDegeriOku("KeyName", "CallSign", "KeyValuePath"))

            txtCallSign.Text = fnkFalconMFDDataXMLDegeriOku("KeyName", "CallSign", "KeyValue")
            fnkReadCallSign_INI_File(fnkFalconMFDDataXMLDegeriOku("KeyName", "CallSign", "KeyValuePath"))
        End If

        'uçak moduna göre seçim yapacak
        'fnkSetAirCraft("F16")

        m_ThreadList.Add(Nothing) 'SolMFD
        m_ThreadList.Add(Nothing) 'SağMFD
        m_ThreadList.Add(Nothing) 'ICP
        m_ThreadList.Add(Nothing) 'RWR
        m_ThreadList.Add(Nothing) 'Test
        m_ThreadList.Add(Nothing) 'FlightData

        m_ThreadList.Add(Nothing) 'FlightDataLoad
        m_ThreadList.Add(Nothing) 'FlightDataNav
        m_ThreadList.Add(Nothing) 'FlightDataNavMap



        'Geçici Değer Tanımlama
        vrFlightData(0) = "192.168.0.97"
        vrFlightData(1) = "21111" 'vrMsgPort
        vrFlightData(2) = "21112" 'vrImgPort
        vrFlightData(3) = "100" ' Refresh Rate


        'Auto Start
        If Not Srv.isRunning Then
            'Clear messages from previous session(don't care to this)
            Me.IncomingMessagesList.Items.Clear()
            'Retrieve PORT
            Dim PORT As Integer = ServerPort.Value
            'Start the server
            'You can use AutoStart = true as here or call later Srv.StartServer
            Srv = Nothing
            Srv = New ServerClass(PORT, True)
            'It's already Started!
            Me.StartStopButton.Text = "Stop"

            fnkResimDosyasiniGoster()


        Else
            'Stop and terminate the server
            Srv.StopServer()
            Me.StartStopButton.Text = "Start"


            fnkStarStopDED("Stop")
            fnkStarStopRWR("Stop")
            fnkStarStopSagMFD("Stop")
            fnkStarStopSolMFD("Stop")
            fnkStarStopTest("Stop")
            fnkStarStopFlightData("Stop")

        End If

    End Sub


    Public Shared Function Is64BitOperatingSystem() As Boolean
        If Not Environment.GetFolderPath(Environment.SpecialFolder.SystemX86).ToUpper().Contains("SYSTEM32") Then
            Return True
        End If
        'Check for the SystemX86 variable. If is not 'SYSTEM32' then the Operating System is x64 (SystemX86 = SYSWOW64).
        Return False
        'Return false when the method was not blocked by the if statement.
    End Function

    Public Sub fnkGetFalconInstalledPath(prBMSVer As String)

        'vrFalconPath = ""

        If Is64BitOperatingSystem() = True Then ' 64 Bit

            Try
                Dim readValue = My.Computer.Registry.GetValue(
        "HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\Benchmark Sims\Falcon " & prBMSVer, "baseDir", Nothing)

                If readValue Is Nothing Then
                    MsgBox("Falcon " & prBMSVer & " Version Does Not Exist" & vbCrLf & "Please Select Correct Version")
                    cmbBMSVerion.Focus()
                End If

                vrFalconPath = readValue
            Catch ex As Exception

            End Try

        Else '32 Bit

            Try
                Dim readValue = My.Computer.Registry.GetValue(
        "HKEY_LOCAL_MACHINE\SOFTWARE\Benchmark Sims\Falcon " & prBMSVer, "baseDir", Nothing)

                If readValue Is Nothing Then
                    MsgBox("Falcon " & prBMSVer & " Version Does Not Exist" & vbCrLf & "Please Select Correct Version")
                    cmbBMSVerion.Focus()
                End If

                vrFalconPath = readValue
            Catch ex As Exception

            End Try
        End If

        Me.Text = "Falcon MFD Server" & " ( Falcon MFD Path : " & vrFalconPath & ")"

    End Sub

    Public Function fnkGetLatestModifiedDate(prINIPath As String) As String

        Dim infoReader As System.IO.FileInfo
        infoReader = My.Computer.FileSystem.GetFileInfo(prINIPath)
        Return infoReader.LastWriteTime


    End Function





    Public Sub fnkStarStopDED(prStartStop As String)

        Try



            If Not m_ThreadList(2) Is Nothing Then
                If CType(m_ThreadList(2), Thread).IsAlive Then
                    'MsgBox("You can not start this thread again. Becoz this thread is still alive !!", MsgBoxStyle.Critical)
                    If prStartStop = "Stop" Then
                        If Not m_ThreadList(2) Is Nothing Then
                            If CType(m_ThreadList(2), Thread).IsAlive Then
                                CType(m_ThreadList(2), Thread).Abort()
                                m_ThreadList(2) = Nothing
                                btnICP.Text = "ICP De Active"
                                btnICP.BackColor = Control.DefaultBackColor
                                IncomingMessagesList.Items.Add(vrDED(0) + " --> (" + "DED Data Reading Stopped" + ")")
                            Else
                                'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                            End If
                        Else
                            'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                        End If
                    End If


                Else
                    GoTo StartThread
                End If
            Else
                GoTo StartThread
            End If
            Exit Sub
StartThread:
            If prStartStop = "Stop" Then
                Exit Sub
            End If

            Dim m As Integer = 0

            'If cmbOperatingSystem.SelectedIndex = 2 Then
            '    m = 1
            'Else
            '    m = 0
            'End If

            'This our second thrread, So we will Pass '3' as the tread index
            Dim objThreadClass As New clsReadMemoryThreadDED(3, Me, vrDED, vrICPop(m, 0), vrICPop(m, 1), vrICPop(m, 2), vrICPop(m, 3))
            Dim objNewThread As New Thread(AddressOf objThreadClass.StartThreadICP)
            objNewThread.IsBackground = True
            objNewThread.Start()
            m_ThreadList.Item(2) = objNewThread

            btnICP.Text = "ICP Is Active"
            btnICP.BackColor = Color.Lime
            IncomingMessagesList.Items.Add(vrDED(0) + " --> (" + "DED Data Reading Stated" + ")")

        Catch ex As Exception
            MsgBox(ex.Message.ToString)
        End Try
    End Sub


    Public Sub fnkStarStopFlightData(prStartStop As String)

        Try



            If Not m_ThreadList(5) Is Nothing Then
                If CType(m_ThreadList(5), Thread).IsAlive Then
                    'MsgBox("You can not start this thread again. Becoz this thread is still alive !!", MsgBoxStyle.Critical)
                    If prStartStop = "Stop" Then
                        If Not m_ThreadList(5) Is Nothing Then
                            If CType(m_ThreadList(5), Thread).IsAlive Then
                                CType(m_ThreadList(5), Thread).Abort()
                                m_ThreadList(5) = Nothing
                                btnFlightData.Text = "Flgh.Dat. De Active"
                                btnFlightData.BackColor = Control.DefaultBackColor
                                IncomingMessagesList.Items.Add(vrFlightData(0) + " --> (" + "Flight Data Reading Stopped" + ")")
                            Else
                                'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                            End If
                        Else
                            'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                        End If
                    End If


                Else
                    GoTo StartThread
                End If
            Else
                GoTo StartThread
            End If
            Exit Sub
StartThread:

            If prStartStop = "Stop" Then
                Exit Sub
            End If

            'This our second thrread, So we will Pass '6' as the tread index
            Dim objThreadClass As New clsReadMemoryThreadFlightData(6, Me, vrFlightData)
            Dim objNewThread As New Thread(AddressOf objThreadClass.StartThreadFlightData)
            objNewThread.IsBackground = True
            objNewThread.Start()
            m_ThreadList.Item(5) = objNewThread

            btnFlightData.Text = "Flgh.Dat. Is Active"
            btnFlightData.BackColor = Color.Lime
            IncomingMessagesList.Items.Add(vrFlightData(0) + " --> (" + "Flight Data Reading Started" + ")")

        Catch ex As Exception
            MsgBox(ex.Message.ToString)
        End Try

    End Sub




    Public Sub fnkStarStopFlightDataLoad(prStartStop As String)

        Try



            If Not m_ThreadList(6) Is Nothing Then
                If CType(m_ThreadList(6), Thread).IsAlive Then
                    'MsgBox("You can not start this thread again. Becoz this thread is still alive !!", MsgBoxStyle.Critical)
                    If prStartStop = "Stop" Then
                        If Not m_ThreadList(6) Is Nothing Then
                            If CType(m_ThreadList(6), Thread).IsAlive Then
                                CType(m_ThreadList(6), Thread).Abort()
                                m_ThreadList(6) = Nothing
                                btnFlightDataLoad.Text = "Load De Active"
                                btnFlightDataLoad.BackColor = Control.DefaultBackColor
                                IncomingMessagesList.Items.Add(vrFlightDataLoad(0) + " --> (" + "Flight Load Data Reading Stopped" + ")")
                            Else
                                'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                            End If
                        Else
                            'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                        End If
                    End If


                Else
                    GoTo StartThread
                End If
            Else
                GoTo StartThread
            End If
            Exit Sub
StartThread:

            If prStartStop = "Stop" Then
                Exit Sub
            End If

            'This our second thrread, So we will Pass '7' as the tread index
            Dim objThreadClass As New clsReadMemoryThreadFlightDataLoad(7, Me, vrFlightDataLoad)
            Dim objNewThread As New Thread(AddressOf objThreadClass.StartThreadFlightDataLoad)
            objNewThread.IsBackground = True
            objNewThread.Start()
            m_ThreadList.Item(6) = objNewThread

            btnFlightDataLoad.Text = "Load Is Active"
            btnFlightDataLoad.BackColor = Color.Lime
            IncomingMessagesList.Items.Add(vrFlightDataLoad(0) + " --> (" + "Flight Load Data Reading Started" + ")")

        Catch ex As Exception
            MsgBox(ex.Message.ToString)
        End Try

    End Sub


    Public Sub fnkStarStopFlightDataNav(prStartStop As String)

        Try

            If Not m_ThreadList(7) Is Nothing Then
                If CType(m_ThreadList(7), Thread).IsAlive Then
                    'MsgBox("You can not start this thread again. Becoz this thread is still alive !!", MsgBoxStyle.Critical)
                    If prStartStop = "Stop" Then
                        If Not m_ThreadList(7) Is Nothing Then
                            If CType(m_ThreadList(7), Thread).IsAlive Then
                                CType(m_ThreadList(7), Thread).Abort()
                                m_ThreadList(7) = Nothing
                                btnFlightDataNav.Text = "Nav De Active"
                                btnFlightDataNav.BackColor = Control.DefaultBackColor
                                IncomingMessagesList.Items.Add(vrFlightDataNav(0) + " --> (" + "Flight Navigation Data Reading Stopped" + ")")
                            Else
                                'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                            End If
                        Else
                            'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                        End If
                    End If


                Else
                    GoTo StartThread
                End If
            Else
                GoTo StartThread
            End If
            Exit Sub
StartThread:

            If prStartStop = "Stop" Then
                Exit Sub
            End If

            'This our second thrread, So we will Pass '8' as the tread index
            Dim objThreadClass As New clsReadMemoryThreadFlightDataNav(8, Me, vrFlightDataNav)
            Dim objNewThread As New Thread(AddressOf objThreadClass.StartThreadFlightDataNav)
            objNewThread.IsBackground = True
            objNewThread.Start()
            m_ThreadList.Item(7) = objNewThread

            btnFlightDataNav.Text = "Nav Is Active"
            btnFlightDataNav.BackColor = Color.Lime
            IncomingMessagesList.Items.Add(vrFlightDataNav(0) + " --> (" + "Flight Navigation Data Reading Started" + ")")

        Catch ex As Exception
            MsgBox(ex.Message.ToString)
        End Try

    End Sub

    Public Sub fnkStarStopFlightDataNavMap(prStartStop As String)
        Try



            If Not m_ThreadList(8) Is Nothing Then
                If CType(m_ThreadList(8), Thread).IsAlive Then
                    'MsgBox("You can not start this thread again. Becoz this thread is still alive !!", MsgBoxStyle.Critical)
                    If prStartStop = "Stop" Then
                        If Not m_ThreadList(8) Is Nothing Then
                            If CType(m_ThreadList(8), Thread).IsAlive Then
                                CType(m_ThreadList(8), Thread).Abort()
                                m_ThreadList(8) = Nothing
                                btnFlightDataNavMap.Text = "NavMap De Active"
                                btnFlightDataNavMap.BackColor = Control.DefaultBackColor
                                IncomingMessagesList.Items.Add(vrFlightDataNavMap(0) + " --> (" + "Flight Map Data Reading Stopped" + ")")
                            Else
                                'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                            End If
                        Else
                            'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                        End If
                    End If


                Else
                    GoTo StartThread
                End If
            Else
                GoTo StartThread
            End If
            Exit Sub
StartThread:

            If prStartStop = "Stop" Then
                Exit Sub
            End If

            'This our second thrread, So we will Pass '9' as the tread index
            Dim objThreadClass As New clsReadMemoryThreadFlightDataNavMap(9, Me, vrFlightDataNavMap)
            Dim objNewThread As New Thread(AddressOf objThreadClass.StartThreadFlightDataNavMap)
            objNewThread.IsBackground = True
            objNewThread.Start()
            m_ThreadList.Item(8) = objNewThread

            btnFlightDataNavMap.Text = "Nav Map Is Active"
            btnFlightDataNavMap.BackColor = Color.Lime
            IncomingMessagesList.Items.Add(vrFlightDataNavMap(0) + " --> (" + "Flight Map Data Reading Started" + ")")

        Catch ex As Exception
            MsgBox(ex.Message.ToString)
        End Try

    End Sub






    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="prStartStop"> Başlatmak veya Durdurmak İçin Kullanılır </param>
    ''' <remarks></remarks>
    Public Sub fnkStarStopSagMFD(prStartStop As String)

        Try


            If Not m_ThreadList(1) Is Nothing Then
                If CType(m_ThreadList(1), Thread).IsAlive Then
                    'MsgBox("You can not start this thread again. Becoz this thread is still alive !!", MsgBoxStyle.Critical)
                    If prStartStop = "Stop" Then
                        If Not m_ThreadList(1) Is Nothing Then
                            If CType(m_ThreadList(1), Thread).IsAlive Then
                                CType(m_ThreadList(1), Thread).Abort()
                                m_ThreadList(1) = Nothing
                                btnRightMFD.Text = "Rg.MFD De Active"
                                btnRightMFD.BackColor = Control.DefaultBackColor
                                IncomingMessagesList.Items.Add(vrSag(0) + " --> (" + "Right MFD Reading Stopped" + ")")
                            Else
                                'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                            End If
                        Else
                            'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                        End If
                    End If

                Else
                    GoTo StartThread
                End If
            Else
                GoTo StartThread
            End If
            Exit Sub
StartThread:

            If prStartStop = "Stop" Then
                Exit Sub
            End If



            Dim m As Integer = 0

            'If cmbOperatingSystem.SelectedIndex = 2 Then
            '    m = 1
            'Else
            '    m = 0
            'End If


            'This our second thrread, So we will Pass '2' as the tread index
            Dim objThreadClass As New clsReadMemoryThreadSagMFD(2, Me, vrSag, vrSagMFD(m, 0), vrSagMFD(m, 1), vrSagMFD(m, 2), vrSagMFD(m, 3))
            Dim objNewThread As New Thread(AddressOf objThreadClass.StartThreadSagMFD)
            objNewThread.IsBackground = True
            objNewThread.Start()
            m_ThreadList.Item(1) = objNewThread

            btnRightMFD.Text = "Rg.MFD Is Active"
            btnRightMFD.BackColor = Color.Lime

            IncomingMessagesList.Items.Add(vrSag(0) + " --> (" + "Right MFD Reading Started" + ")")


        Catch ex As Exception
            MsgBox(ex.Message.ToString)
        End Try

    End Sub

    Public Sub fnkStarStopSolMFD(prStartStop As String)
        Try


            'Check if the 1st element of the arraylist (m_ThreadList) contains any thread object
            If Not m_ThreadList(0) Is Nothing Then
                'If the 1st element contains any thread object. Then check its status. If its running (IsAlive) then no need start it again
                If CType(m_ThreadList(0), Thread).IsAlive Then
                    'MsgBox("You can not start this thread again. Becoz this thread is still alive !!", MsgBoxStyle.Critical)

                    'We will first check our Thread List if the Thread Object is there.
                    'If it there, then we will see its status.
                    'If its status is Alive or Active we will kill the thread.

                    If prStartStop = "Stop" Then
                        If Not m_ThreadList(0) Is Nothing Then
                            If CType(m_ThreadList(0), Thread).IsAlive Then
                                CType(m_ThreadList(0), Thread).Abort()
                                m_ThreadList(0) = Nothing
                                btnLeftMfd.Text = "Lf.MFD De Active"
                                btnLeftMfd.BackColor = Control.DefaultBackColor
                                IncomingMessagesList.Items.Add(vrSol(0) + " --> (" + "Left MFD Reading Stopped" + ")")
                            Else
                                'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                            End If
                        Else
                            'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                        End If
                    End If


                Else
                    'Thread object is there but thread is not active. Its dead !! You can create new again
                    GoTo StartThread
                End If
            Else
                'No thread object is there in 1st element of our thread list. You can create new 
                GoTo StartThread
            End If
            Exit Sub


StartThread:

            If prStartStop = "Stop" Then
                Exit Sub
            End If


            Dim m As Integer = 0

            'If cmbOperatingSystem.SelectedIndex = 2 Then
            '    m = 1
            'Else
            '    m = 0
            'End If

            Dim objThreadClass As New clsReadMemoryThreadSolMFD(1, Me, vrSol, vrSolMFD(m, 0), vrSolMFD(m, 1), vrSolMFD(m, 2), vrSolMFD(m, 3))




            Dim objNewThread As New Thread(AddressOf objThreadClass.StartThreadSolMFD)


            objNewThread.IsBackground = True


            objNewThread.Start()


            m_ThreadList.Item(0) = objNewThread

            btnLeftMfd.Text = "Lf.MFD Is Active"
            btnLeftMfd.BackColor = Color.Lime
            IncomingMessagesList.Items.Add(vrSol(0) + " --> (" + "Left MFD Reading Started" + ")")

        Catch ex As Exception
            MsgBox(ex.Message.ToString)
        End Try

    End Sub

    Public Sub fnkStarStopTest(prStartStop As String)


        Try



            If Not m_ThreadList(4) Is Nothing Then
                If CType(m_ThreadList(4), Thread).IsAlive Then
                    'MsgBox("You can not start this thread again. Becoz this thread is still alive !!", MsgBoxStyle.Critical)

                    If Not m_ThreadList(4) Is Nothing Then
                        If CType(m_ThreadList(4), Thread).IsAlive Then
                            CType(m_ThreadList(4), Thread).Abort()
                            m_ThreadList(4) = Nothing
                            btnReadMem.Text = "Start Reading Mem."
                            btnReadMem.BackColor = Control.DefaultBackColor
                        Else
                            'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                        End If
                    Else
                        'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                    End If



                Else
                    GoTo StartThread
                End If
            Else
                GoTo StartThread
            End If
            Exit Sub
StartThread:

            If prStartStop = "Stop" Then
                Exit Sub
            End If

            'This our second thrread, So we will Pass '5' as the tread index
            Dim objThreadClass As New clsReadMemoryThread(5, Me, cmbAirCraft.Text)
            Dim objNewThread As New Thread(AddressOf objThreadClass.StartThreadFull)
            objNewThread.IsBackground = True
            objNewThread.Start()
            m_ThreadList.Item(4) = objNewThread

            btnReadMem.Text = "Stop Reading Falcon MFD"
            btnReadMem.BackColor = Color.Lime

        Catch ex As Exception
            MsgBox(ex.Message.ToString)
        End Try

    End Sub

    Public Sub fnkStarStopRWR(prStartStop As String)

        Try



            If Not m_ThreadList(3) Is Nothing Then
                If CType(m_ThreadList(3), Thread).IsAlive Then
                    'MsgBox("You can not start this thread again. Becoz this thread is still alive !!", MsgBoxStyle.Critical)
                    If prStartStop = "Stop" Then
                        If Not m_ThreadList(3) Is Nothing Then
                            If CType(m_ThreadList(3), Thread).IsAlive Then
                                CType(m_ThreadList(3), Thread).Abort()
                                m_ThreadList(3) = Nothing
                                btnRWR.Text = "RWR De Active"
                                btnRWR.BackColor = Control.DefaultBackColor
                                IncomingMessagesList.Items.Add(vrRWR(0) + " --> (" + "RWR Reading Stopped" + ")")
                            Else
                                'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                            End If
                        Else
                            'MsgBox("No thread is active in this section", MsgBoxStyle.Critical)
                        End If
                    End If


                Else
                    GoTo StartThread
                End If
            Else
                GoTo StartThread
            End If
            Exit Sub
StartThread:

            If prStartStop = "Stop" Then
                Exit Sub
            End If


            Dim m As Integer = 0

            'If cmbOperatingSystem.SelectedIndex = 2 Then
            '    m = 1
            'Else
            '    m = 0
            'End If


            'This our second thrread, So we will Pass '4' as the tread index
            Dim objThreadClass As New clsReadMemoryThreadRWR(4, Me, vrRWR, vrRWRoP(m, 0), vrRWRoP(m, 1), vrRWRoP(m, 2), vrRWRoP(m, 3))
            Dim objNewThread As New Thread(AddressOf objThreadClass.StartThreadRWR)
            objNewThread.IsBackground = True
            objNewThread.Start()
            m_ThreadList.Item(3) = objNewThread

            btnRWR.Text = "RWR Is Active"
            btnRWR.BackColor = Color.Lime
            IncomingMessagesList.Items.Add(vrRWR(0) + " --> (" + "RWR Reading Started" + ")")



        Catch ex As Exception
            MsgBox(ex.Message.ToString)
        End Try



    End Sub

    Public Sub ReceiveThreadMessage(ByVal ThreadIndex As Integer, ByVal prImg As Bitmap)
        'The arguments ThreadIndex and Counter will be recieved from the thread. The thread will pass these arguments, 
        'While invoking this method

        'Now first of all we will check from which thread we are getting message, its indicated by the argument ThreadIndex
        'According to this value we will look into our ThreadList (m_ThreadList).
        'We can access the corresponding thread object by m_ThreadList(ThreadIndex-1)
        'Here we have -1 becoz we have thread index from 1 but start index of arraylist is 0.
        'So position of the thread object in arraylist is ThreadIndex-1.

        'We will show the counter to the appropriate label on the form. There are 4 counter.
        'We will show according to, from which thread we are getting message

        'We will also check if we checked the checkbox to stop the thread on a particular value of counter
        Select Case ThreadIndex
            Case 1

                ''picSolMFD.Image = prImg

                'If vrSol(0) <> "" Then
                '    sockSendRec(vrSol, prImg, "vrSol")
                'End If

            Case 2

                'picSagMFD.Image = prImg

                'If vrSag(0) <> "" Then
                '    sockSendRec(vrSag, prImg, "vrSag")
                'End If


            Case 3

                'picICP.Image = prImg

                'If vrICP(0) <> "" Then
                '    sockSendRec(vrICP, prImg, "vrICP")
                'End If

            Case 4

                ''picICP.Image = prImg

                'If vrRWR(0) <> "" Then
                '    sockSendRec(vrRWR, prImg, "vrRWR")
                'End If

            Case 5
                picIMGAll.Image = prImg
        End Select
    End Sub


    Public Sub ReceiveTextThreadMessage(ByVal ThreadIndex As Integer, ByVal prError As String)
        'The arguments ThreadIndex and Counter will be recieved from the thread. The thread will pass these arguments, 
        'While invoking this method

        'Now first of all we will check from which thread we are getting message, its indicated by the argument ThreadIndex
        'According to this value we will look into our ThreadList (m_ThreadList).
        'We can access the corresponding thread object by m_ThreadList(ThreadIndex-1)
        'Here we have -1 becoz we have thread index from 1 but start index of arraylist is 0.
        'So position of the thread object in arraylist is ThreadIndex-1.

        'We will show the counter to the appropriate label on the form. There are 4 counter.
        'We will show according to, from which thread we are getting message

        'We will also check if we checked the checkbox to stop the thread on a particular value of counter
        Select Case ThreadIndex
            Case 1
                IncomingMessagesList.Items.Add(prError.ToString)
                IncomingMessagesListError.Items.Add(prError.ToString)
            Case 2

                'picSagMFD.Image = prImg

                'If vrSag(0) <> "" Then
                '    sockSendRec(vrSag, prImg, "vrSag")
                'End If


            Case 3

                'picICP.Image = prImg

                'If vrICP(0) <> "" Then
                '    sockSendRec(vrICP, prImg, "vrICP")
                'End If

            Case 4

                ''picICP.Image = prImg

                'If vrRWR(0) <> "" Then
                '    sockSendRec(vrRWR, prImg, "vrRWR")
                'End If

            Case 5
                'picIMGAll.Image = prImg
        End Select
    End Sub

    Private Sub btnReadMem_Click(sender As Object, e As EventArgs) Handles btnReadMem.Click
        fnkStarStopTest("Start")
    End Sub

    Private Sub btnOpenClose_Click(sender As Object, e As EventArgs) Handles btnOpenClose.Click
        gbTest.Visible = True
    End Sub

    Private Sub btnCloseTest_Click(sender As Object, e As EventArgs) Handles btnCloseTest.Click
        gbTest.Visible = False
    End Sub



    'Yeni
    Public Function imageToByteArray(imageIn As System.Drawing.Image) As Byte()
        Dim ms As New MemoryStream()
        imageIn.Save(ms, System.Drawing.Imaging.ImageFormat.Gif)
        Return ms.ToArray()
    End Function

    Private Function checkip(ByVal ip As String) As Boolean
        Try
            Dim ss() As String = ip.Split(".")
            Dim bb(3) As Byte
            Dim i As Integer
            For i = 0 To 3
                bb(i) = Byte.Parse(ss(i))
            Next
            Return True
        Catch ex As Exception
            Return False
        End Try
    End Function

    Private Sub btnSendMSG_Click(sender As Object, e As EventArgs) Handles btnSendMSG.Click
        If txtIp.Text = "" Then
            MsgBox("Please Enter IP of Android Tablet", vbCritical)
            Exit Sub
        End If

        SendMessage(txtIp.Text, txtPortMsg.Value, txtMsg.Text)
    End Sub

    Private Sub btnReadSendMFD_Click(sender As Object, e As EventArgs) Handles btnReadSendMFD.Click


        If txtIp.Text = "" Then
            MsgBox("Please Enter IP of Android Tablet", vbCritical)
            Exit Sub
        End If

        vrSag(0) = txtIp.Text
        vrSag(1) = txtPortMsg.Value  'vrMsgPort
        vrSag(2) = txtPortRMFD.Value  'vrImgPort
        vrSag(3) = txtRefreshTime.Value  ' Refresh Rate

        vrSol(0) = txtIp.Text
        vrSol(1) = txtPortMsg.Value  'vrMsgPort
        vrSol(2) = txtPortLMFD.Value  'vrImgPort
        vrSol(3) = txtRefreshTime.Value  ' Refresh Rate



        vrFlightData(0) = txtIp.Text
        vrFlightData(1) = txtPortMsg.Value  'vrMsgPort
        'vrFlightData(2) = txtPortLMFD.Value  'vrImgPort
        vrFlightData(3) = txtRefreshTime.Value  ' Refresh Rate


        fnkStarStopSolMFD("Start")

        'Sonradan Deneme için Eklendi Başı

        fnkStarStopSagMFD("Start")


        'fnkStarStopFlightData("Start")

        'Sonradan Deneme için Eklendi Sonu

    End Sub

    Private Sub StartStopButton_Click(sender As Object, e As EventArgs) Handles StartStopButton.Click

        'Try
        '    fnkXMLOku()
        'Catch ex As Exception
        '    'MsgBox("Please Select KeyStroke File")
        '    fnkFindKeyStrokeFile()

        '    Try
        '        fnkXMLOku()
        '    Catch ex1 As Exception
        '        Exit Sub
        '    End Try

        'End Try

        If Not Srv.isRunning Then
            'Clear messages from previous session(don't care to this)
            IncomingMessagesList.Items.Clear()
            IncomingMessagesListError.Items.Clear()
            'Retrieve PORT
            Dim PORT As Integer = ServerPort.Value
            'Start the server
            'You can use AutoStart = true as here or call later Srv.StartServer
            Srv = Nothing
            Srv = New ServerClass(PORT, True)
            'It's already Started!
            Me.StartStopButton.Text = "Stop"

            fnkResimDosyasiniGoster()


        Else
            'Stop and terminate the server
            Srv.StopServer()
            Me.StartStopButton.Text = "Start"


            fnkStarStopDED("Stop")
            fnkStarStopRWR("Stop")
            fnkStarStopSagMFD("Stop")
            fnkStarStopSolMFD("Stop")
            fnkStarStopTest("Stop")
            fnkStarStopFlightData("Stop")

            fnkStarStopFlightDataLoad("Stop")
            fnkStarStopFlightDataNav("Stop")
            fnkStarStopFlightDataNavMap("Stop")

        End If


    End Sub

    Public Sub fnkFindKeyStrokeFile()

        ofDB.DefaultExt = "keystrokes.key"
        ofDB.Title = "Please Select Key Stroke File      " & vbCrLf & "Path : ...\Falcon BMS 4.32\User\Config\....key (i.e.: keystrokes.key)"
        ofDB.InitialDirectory = vrFalconPath & "\User\Config"

        Dim dosya As String

        If ofDB.ShowDialog = DialogResult.OK Then
            dosya = ofDB.FileName

            'Resim Patını 


            'System.IO.File.Copy(dosya, System.IO.Path.GetTempPath() & "FalconKeyStrokes.txt", True)

            'Dim objReader As New System.IO.StreamReader(System.IO.Path.GetTempPath() & "FalconKeyStrokes.txt")
            Dim objReader As New System.IO.StreamReader(dosya)

            'Save file contents to textbox
            txtKeyFile.Text = objReader.ReadToEnd
            objReader.Close()


            fnkXMLOlustur()

        Else
            Exit Sub
        End If
    End Sub


    Public Sub fnkXMLOlusturMustDeclare()
        ' Create array of employees.

        ' Create XmlWriterSettings.
        Dim settings As XmlWriterSettings = New XmlWriterSettings()
        settings.Indent = True

        ' Create XmlWriter.
        Using writer As XmlWriter = XmlWriter.Create("FalconBMSKeystrokesMustDeclare.xml", settings)
            ' Begin writing.
            writer.WriteStartDocument()
            writer.WriteStartElement("FalconBMS") ' Root.

            ' Loop over employees in array.

            Dim vrID As String = ""
            Dim vrButton As String = ""
            Dim vrModifier As String = ""
            Dim vrDescription As String = ""

            Dim vrLine As String = ""
            Dim vrTut() As String




            For i As Integer = 0 To txtKeyFile.Lines.Length - 1
                vrLine = txtKeyFile.Lines(i)

                Try

                    vrID = Trim(vrLine)

                    writer.WriteStartElement("MustDeclare")
                    writer.WriteElementString("ID", vrID)
                    writer.WriteEndElement()
                Catch ex As Exception

                End Try

            Next



            ' End document.
            writer.WriteEndElement()
            writer.WriteEndDocument()
        End Using

        MsgBox("İşlem Tamamlandı")


    End Sub

    Public Sub fnkFalconMFDDataXMLGuncelle(vrItemPKName As String, vrItemPKValue As String, vrItemChangeName As String, vrItemChangeValue As String)
        Dim xmlDoc As XmlDocument
        Dim xmlDataNode As XmlNode
        Dim xmlNode As XmlNode
        Dim FileName As String = exePath & "/FalconBMSDatas.xml"

        xmlDoc = New XmlDocument
        xmlDoc.Load(FileName)
        xmlDataNode = xmlDoc.SelectSingleNode("//Datas[" & vrItemPKName & " = '" & vrItemPKValue & "']")

        If Not xmlDataNode Is Nothing Then
            xmlNode = xmlDataNode.SelectSingleNode(vrItemChangeName)
            If Not xmlNode Is Nothing Then
                xmlNode.InnerText = vrItemChangeValue
                xmlDoc.Save(FileName)
            End If
        End If
    End Sub

    Public Function fnkFalconMFDDataXMLDegeriOku(vrItemPKName As String, vrItemPKValue As String, vrItemName As String) As String

        Dim vrValue As String = ""
        Dim xmlDoc As XmlDocument
        Dim xmlDataNode As XmlNode
        Dim xmlNode As XmlNode
        Dim FileName As String = exePath & "/FalconBMSDatas.xml"

        xmlDoc = New XmlDocument
        xmlDoc.Load(FileName)
        xmlDataNode = xmlDoc.SelectSingleNode("//Datas[" & vrItemPKName & " = '" & vrItemPKValue & "']")

        If Not xmlDataNode Is Nothing Then
            xmlNode = xmlDataNode.SelectSingleNode(vrItemName)
            If Not xmlNode Is Nothing Then
                vrValue = xmlNode.InnerText
            End If
        End If


        Return vrValue

    End Function


    Public Sub fnkFalconMFDDataXMLOlustur()
        ' Create array of employees.

        ' Create XmlWriterSettings.
        Dim settings As XmlWriterSettings = New XmlWriterSettings()
        settings.Indent = True

        ' Create XmlWriter.
        Using writer As XmlWriter = XmlWriter.Create("FalconBMSDatas.xml", settings)
            ' Begin writing.
            writer.WriteStartDocument()
            writer.WriteStartElement("FalconBMS") ' Root.

            writer.WriteStartElement("Datas")
            writer.WriteElementString("KeyName", "Picture")
            writer.WriteElementString("KeyValue", vrFalconPath & "\User\Pictures")
            writer.WriteEndElement()

            writer.WriteStartElement("Datas")
            writer.WriteElementString("KeyName", "FalconBMSVersion")
            If cmbBMSVerion.Text = "" Then
                writer.WriteElementString("KeyValue", "BMS 4.33")
            Else
                writer.WriteElementString("KeyValue", cmbBMSVerion.Text)
            End If
            writer.WriteEndElement()

            writer.WriteStartElement("Datas")
            writer.WriteElementString("KeyName", "AndroidIP")
            writer.WriteElementString("KeyValue", "192.168.1.101")
            writer.WriteEndElement()


            Dim vrTmp As String = InputBox("Enter Call Sign", "Falcon MFD", "Viper")

            If vrTmp = "" Then
                vrTmp = "Viper"
            End If

            writer.WriteStartElement("Datas")
            writer.WriteElementString("KeyName", "CallSign")
            writer.WriteElementString("KeyValue", vrTmp)
            writer.WriteElementString("KeyValuePath", vrFalconPath & "\User\Config\" & vrTmp & ".ini")
            writer.WriteEndElement()

            ' End document.
            writer.WriteEndElement()
            writer.WriteEndDocument()
        End Using


    End Sub


    Public Sub fnkXMLOlustur()
        ' Create array of employees.

        ' Create XmlWriterSettings.
        Dim settings As XmlWriterSettings = New XmlWriterSettings()
        settings.Indent = True

        ' Create XmlWriter.
        Using writer As XmlWriter = XmlWriter.Create("FalconBMSKeystrokes.xml", settings)
            ' Begin writing.
            writer.WriteStartDocument()
            writer.WriteStartElement("FalconBMS") ' Root.

            ' Loop over employees in array.

            Dim vrID As String = ""
            Dim vrButton As String = ""
            Dim vrModifier As String = ""
            Dim vrDescription As String = ""

            Dim vrLine As String = ""
            Dim vrTut() As String


            For i As Integer = 0 To txtKeyFile.Lines.Length - 1
                vrLine = txtKeyFile.Lines(i)

                Try

                    vrTut = Split(vrLine, """")
                    vrDescription = vrTut(1)

                    vrTut = Split(vrLine, " ")



                    If txtMustDeclare.Find(vrTut(0)) >= 0 Then

                        vrID = vrTut(0)
                        vrButton = vrTut(3)
                        vrModifier = vrTut(4)

                        writer.WriteStartElement("Keystrokes")
                        writer.WriteElementString("ID", vrID)
                        writer.WriteElementString("Button", vrButton)
                        writer.WriteElementString("Modifier", vrModifier)
                        writer.WriteElementString("Description", vrDescription)
                        writer.WriteEndElement()

                    End If

                Catch ex As Exception

                End Try


            Next

            ' End document.
            writer.WriteEndElement()
            writer.WriteEndDocument()
        End Using


        fnkXMLOku()

    End Sub


    Dim vrKeys() As String

    Public Sub fnkXMLOku()

        Dim vrUnDeclared As Boolean = False
        Dim vrUnDeclaredRow As String

        grdKeys.RowCount = 300
        Dim vrCnt As Integer = 0

        Dim a As Integer
        For a = 0 To grdKeys.RowCount - 1
            grdKeys.Rows(a).DefaultCellStyle.BackColor = Color.White
        Next

        ' Create an XML reader.
        Using reader As XmlReader = XmlReader.Create("FalconBMSKeystrokes.xml")
            While reader.Read()
                ' Check for start elements.
                If reader.IsStartElement() Then

                    If reader.Name = "ID" Then
                        reader.Read()
                        grdKeys.Rows(vrCnt).Cells("ColID").Value = reader.Value
                    End If

                    If reader.Name = "Button" Then
                        reader.Read()
                        grdKeys.Rows(vrCnt).Cells("ColButtons").Value = reader.Value

                        grdKeys.Rows(vrCnt).Cells("ColButtonsAciklama").Value = fnkBtnScanCode(reader.Value)

                        If reader.Value = "0XFFFFFFFF" Then

                            grdKeys.Rows(vrCnt).DefaultCellStyle.BackColor = Color.Yellow

                            vrUnDeclared = True
                            vrUnDeclaredRow = grdKeys.Rows(vrCnt).Cells("ColID").Value

                        End If

                    End If

                    If reader.Name = "Modifier" Then
                        reader.Read()
                        grdKeys.Rows(vrCnt).Cells("ColModifiers").Value = reader.Value


                        Select Case reader.Value
                            Case 0
                                grdKeys.Rows(vrCnt).Cells("CollModifiyelerAcik").Value = ""
                            Case 1
                                grdKeys.Rows(vrCnt).Cells("CollModifiyelerAcik").Value = "Shift"
                            Case 2
                                grdKeys.Rows(vrCnt).Cells("CollModifiyelerAcik").Value = "Ctrl"
                            Case 3
                                grdKeys.Rows(vrCnt).Cells("CollModifiyelerAcik").Value = "Shift + Ctrl"
                            Case 4
                                grdKeys.Rows(vrCnt).Cells("CollModifiyelerAcik").Value = "Alt"
                            Case 5
                                grdKeys.Rows(vrCnt).Cells("CollModifiyelerAcik").Value = "Shift + Alt"
                            Case 6
                                grdKeys.Rows(vrCnt).Cells("CollModifiyelerAcik").Value = "Ctrl + Alt"
                            Case 7
                                grdKeys.Rows(vrCnt).Cells("CollModifiyelerAcik").Value = "Shift + Ctrl + Alt"
                            Case Else
                                grdKeys.Rows(vrCnt).Cells("CollModifiyelerAcik").Value = "Sorunlu"
                        End Select


                    End If

                    If reader.Name = "Description" Then
                        reader.Read()
                        grdKeys.Rows(vrCnt).Cells("ColDescription").Value = reader.Value

                        vrCnt += 1
                    End If




                End If
            End While
        End Using

        Dim m As Integer



        'Olması gerekenleri Grid İle Karşılaştır
        Dim vrLine As String = ""
        Dim vrBuldu As Boolean = False

        For i As Integer = 0 To txtMustDeclare.Lines.Length - 1
            vrLine = txtMustDeclare.Lines(i)
            vrBuldu = False

            For m = 0 To grdKeys.RowCount - 1
                If grdKeys.Rows(m).Cells("ColID").Value = vrLine Then
                    vrBuldu = True
                    Exit For
                End If
            Next

            If vrBuldu = False Then
                grdKeys.Rows(vrCnt).Cells("ColID").Value = vrLine
                grdKeys.Rows(vrCnt).Cells("ColDescription").Value = "Please Add to Your Key File"
                grdKeys.Rows(vrCnt).DefaultCellStyle.BackColor = Color.Red
                vrCnt += 1
            End If

        Next



        For m = 0 To grdKeys.RowCount - vrCnt - 1
            grdKeys.Rows.RemoveAt(grdKeys.RowCount - 1)
        Next

        grdKeys.Sort(grdKeys.Columns("ColButtonsAciklama"), System.ComponentModel.ListSortDirection.Ascending)

        grdKeys.ClearSelection()

        grdKeys.Rows(0).Selected = True

        grdKeys.FirstDisplayedScrollingRowIndex = 0

        If vrUnDeclared = True Then

            'For m = 0 To grdKeys.RowCount - 1
            '    If grdKeys.Rows(m).Cells("ColID").Value = vrUnDeclaredRow Then

            '        grdKeys.Rows(m).Selected = True

            '        grdKeys.FirstDisplayedScrollingRowIndex = m
            '        Exit For
            '    End If
            'Next

            MsgBox("You Have Un Assigned Key" & vbCrLf & "For Use Program Performly, You Have to Assign Keys", MsgBoxStyle.Critical, "Falcon MDS")

        End If

        ReDim vrKeys(grdKeys.RowCount)


        For m = 0 To grdKeys.RowCount - 1
            vrKeys(m) = grdKeys.Rows(m).Cells("ColID").Value
        Next

    End Sub

    Public Function fnkBtnScanCode(ByVal prBtn As String) As String

        Select Case UCase(prBtn)
            Case "0X1E"
                Return "A"
            Case "0X30"
                Return "B"
            Case "0X2E"
                Return "C"
            Case "0X20"
                Return "D"
            Case "0X12"
                Return "E"
            Case "0X21"
                Return "F"
            Case "0X22"
                Return "G"
            Case "0X23"
                Return "H"
            Case "0X17"
                Return "I"
            Case "0X24"
                Return "J"
            Case "0X25"
                Return "K"
            Case "0X26"
                Return "L"
            Case "0X32"
                Return "M"
            Case "0X31"
                Return "N"
            Case "0X18"
                Return "O"
            Case "0X19"
                Return "P"
            Case "0X10"
                Return "Q"
            Case "0X13"
                Return "R"
            Case "0X1F"
                Return "S"
            Case "0X14"
                Return "T"
            Case "0X16"
                Return "U"
            Case "0X2F"
                Return "V"
            Case "0X11"
                Return "W"
            Case "0X2D"
                Return "X"
            Case "0X15"
                Return "Y"
            Case "0X2C"
                Return "Z"
            Case "0X1"
                Return "Escape"
            Case "0X3B"
                Return "F1"
            Case "0X3C"
                Return "F2"
            Case "0X3D"
                Return "F3"
            Case "0X3E"
                Return "F4"
            Case "0X3F"
                Return "F5"
            Case "0X40"
                Return "F6"
            Case "0X41"
                Return "F7"
            Case "0X42"
                Return "F8"
            Case "0X43"
                Return "F9"
            Case "0X44"
                Return "F10"
            Case "0X57"
                Return "F11"
            Case "0X58"
                Return "F12"
            Case "0XB7"
                Return "PrintScreen"
            Case "0X46"
                Return "ScrollLock"
            Case "0X29"
                Return " ~ "
            Case "0X2"
                Return "1"
            Case "0X3"
                Return "2"
            Case "0X4"
                Return "3"
            Case "0X5"
                Return "4"
            Case "0X6"
                Return "5"
            Case "0X7"
                Return "6"
            Case "0X8"
                Return "7"
            Case "0X9"
                Return "8"
            Case "0XA"
                Return "9"
            Case "0XB"
                Return "0"
            Case "0XC"
                Return " - "
            Case "0XD"
                Return " Return "
            Case "0XE"
                Return "Backspace"
            Case "0XF"
                Return "Tabulator"
            Case "0X1A"
                Return " [ "
            Case "0X1B"
                Return " ] "
            Case "0X2B"
                Return " \ "
            Case "0X3A"
                Return "CapsLock"
            Case "0X27"
                Return " ; "
            Case "0X28"
                Return " ' "
            Case "0X1C"
                Return "Enter"
            Case "0X33"
                Return " , "
            Case "0X34"
                Return " . "
            Case "0X35"
                Return " / "
            Case "0XDB"
                Return "Win(left)"
            Case "0X39"
                Return "Space"
            Case "0XDC"
                Return "Win(right)"
            Case "0XDD"
                Return "Menu"
            Case "0XD2"
                Return "Insert"
            Case "0XD3"
                Return "Delete"
            Case "0XC7"
                Return "Home"
            Case "0XCF"
                Return "End"
            Case "0XC9"
                Return "PageUp"
            Case "0XD1"
                Return "PageDown"
            Case "0XC8"
                Return "CursorUp"
            Case "0XD0"
                Return "CursorDown"
            Case "0XCB"
                Return "CursorLeft"
            Case "0XCD"
                Return "CursorRight"
            Case "0X45"
                Return "NumLock"
            Case "0XB5"
                Return "Num/"
            Case "0X37"
                Return "Num*"
            Case "0X4A"
                Return "Num-"
            Case "0X4E"
                Return "Num+"
            Case "0X52"
                Return "Num0"
            Case "0X4F"
                Return "Num1"
            Case "0X50"
                Return "Num2"
            Case "0X51"
                Return "Num3"
            Case "0X4B"
                Return "Num4"
            Case "0X4C"
                Return "Num5"
            Case "0X4D"
                Return "Num6"
            Case "0X47"
                Return "Num7"
            Case "0X48"
                Return "Num8"
            Case "0X49"
                Return "Num9"
            Case "0X53"
                Return "NumDel"
            Case "0X9C"
                Return "NumEnter"
            Case "0XFFFFFFFF"
                Return " (Not assigned)"

        End Select


    End Function


    Private Sub btnFindKeyFile_Click(sender As Object, e As EventArgs) Handles btnFindKeyFile.Click
        fnkFindKeyStrokeFile()
    End Sub


    Private Sub OnIncomingMessage(ByVal Args As ServerClass.InMessEvArgs) Handles Srv.IncomingMessage

        'Gelen Bilgilerin Sırası

        ' ayirac(0)  Gelen Mesaj
        ' ayirac(1)  vrMsgPort
        ' ayirac(2)  vrImgPort
        ' ayirac(3)  Refresh Rate

        If Args.senderIP = "192.168.1.1" Or Args.senderIP = "192.168.0.1" Then
            Args.senderIP = txtIp.Text
        End If

        If Mid(Args.message, 1, 3) = "Cmd" Then 'Komut Satırı Geldiyse

            Dim ayirac() As String

            ayirac = Split(Args.message, "@")

            'Gelen Komut Mesajını Listeye Ekle
            IncomingMessagesList.Items.Add(Args.senderIP + " --> " + ayirac(0))

            Select Case ayirac(0)
                Case "CmdStartSagMFD"


                    If vrSag(0) <> Args.senderIP Then
                        fnkStarStopSagMFD("Stop")
                    End If


                    vrSag(0) = Args.senderIP
                    vrSag(1) = ayirac(1) ' vrMsgPort
                    vrSag(2) = ayirac(2) ' vrImgPort
                    vrSag(3) = ayirac(3) ' Refresh Rate

                    'SendMessage(Args.senderIP, ServerPort.Value, "Right MFD Reading Started")

                    fnkStarStopSagMFD("Start")

                    Exit Select
                Case "CmdStartSolMFD"

                    If vrSol(0) <> Args.senderIP Then
                        fnkStarStopSolMFD("Stop")
                    End If


                    vrSol(0) = Args.senderIP
                    vrSol(1) = ayirac(1) 'Message Port
                    vrSol(2) = ayirac(2) 'Image Port
                    vrSol(3) = ayirac(3) ' Refresh Rate



                    fnkStarStopSolMFD("Start")

                    'SendMessage(Args.senderIP, ayirac(1), "Left MFD Reading Started")
                    Exit Select
                Case "CmdStopSolMFD"
                    fnkStarStopSolMFD("Stop")

                    'SendMessage(Args.senderIP, ayirac(1), "Left MFD De Selected")
                    Exit Select

                Case "CmdHandShake"
                    SendMessage(Args.senderIP, ayirac(1), "Msg@" + "Info@" + "Connected to Server")
                    Exit Select
                Case "CmdStopSagMFD"

                    fnkStarStopSagMFD("Stop")

                    'SendMessage(Args.senderIP, ayirac(1), "Right MFD De Selected")
                    Exit Select

                Case "CmdStartDED"

                    If vrDED(0) <> Args.senderIP Then
                        fnkStarStopDED("Stop")
                    End If

                    vrDED(0) = Args.senderIP
                    vrDED(1) = ayirac(1) 'Message Port
                    vrDED(2) = ayirac(2) 'ImagePort
                    vrDED(3) = ayirac(3) ' Refresh Rate


                    fnkStarStopDED("Start")

                    'SendMessage(Args.senderIP, ayirac(1), "ICP Selected")
                    Exit Select

                Case "CmdStopDED"

                    fnkStarStopDED("Stop")

                    'SendMessage(Args.senderIP, ayirac(1), "ICP De Selected")
                    Exit Select

                Case "CmdMisSch"

                    'SendMessage(Args.senderIP, ServerPort.Value, "Requestd Mis.Sch")
                    fnkGetScreenShootAndSend(Args.senderIP, ayirac(1), 1)
                    Exit Select

                Case "CmdMisSch1"

                    'SendMessage(Args.senderIP, ServerPort.Value, "Requestd Mis.Sch")
                    fnkGetScreenShootAndSend(Args.senderIP, ayirac(1), 1)
                    Exit Select

                Case "CmdMisSch2"

                    'SendMessage(Args.senderIP, ServerPort.Value, "Requestd Mis.Sch")
                    fnkGetScreenShootAndSend(Args.senderIP, ayirac(1), 2)
                    Exit Select

                Case "CmdMisSch3"

                    'SendMessage(Args.senderIP, ServerPort.Value, "Requestd Mis.Sch")
                    fnkGetScreenShootAndSend(Args.senderIP, ayirac(1), 3)
                    Exit Select

                Case "CmdMisSch4"

                    'SendMessage(Args.senderIP, ServerPort.Value, "Requestd Mis.Sch")
                    fnkGetScreenShootAndSend(Args.senderIP, ayirac(1), 4)
                    Exit Select

                Case "CmdMisSch5"

                    'SendMessage(Args.senderIP, ServerPort.Value, "Requestd Mis.Sch")
                    fnkGetScreenShootAndSend(Args.senderIP, ayirac(1), 5)
                    Exit Select

                Case "CmdStartRWR"

                    If vrRWR(0) = Args.senderIP Then
                        fnkStarStopRWR("Stop")
                    End If

                    vrRWR(0) = Args.senderIP
                    vrRWR(1) = ayirac(1) ' Message Port
                    vrRWR(2) = ayirac(2) ' Image Port
                    vrRWR(3) = ayirac(3) ' Refresh Rate


                    fnkStarStopRWR("Start")

                    'SendMessage(Args.senderIP, ayirac(1), "RWR Selected")
                    Exit Select

                Case "CmdStopRWR"
                    fnkStarStopRWR("Stop")

                    'SendMessage(Args.senderIP, ayirac(1), "RWR De Selected")
                    Exit Select

                Case "CmdStartFlightData"

                    If vrFlightData(0) <> Args.senderIP Then
                        fnkStarStopFlightData("Stop")
                    End If

                    vrFlightData(0) = Args.senderIP
                    vrFlightData(1) = ayirac(1) 'vrMsgPort
                    vrFlightData(2) = ayirac(2) 'vrImgPort
                    vrFlightData(3) = ayirac(3) 'Refresh Rate



                    'SendMessage(Args.senderIP, ayirac(1), "Flight Data Selected")

                    fnkStarStopFlightData("Start")

                    Exit Select

                Case "CmdSetAirCraft"

                    fnkSetAirCraft(ayirac(1))
                    Exit Select

                Case "CmdStopFlightData"

                    fnkStarStopFlightData("Stop")

                    'SendMessage(Args.senderIP, ayirac(1), "Flight Data De Selected")
                    Exit Select

                Case "CmdStartFlightDataLoad"

                    If vrFlightDataLoad(0) <> Args.senderIP Then
                        fnkStarStopFlightDataLoad("Stop")
                    End If

                    vrFlightDataLoad(0) = Args.senderIP
                    vrFlightDataLoad(1) = ayirac(1) 'vrMsgPort
                    vrFlightDataLoad(2) = ayirac(2) 'vrImgPort
                    vrFlightDataLoad(3) = ayirac(3) 'Refresh Rate
                    vrFlightDataLoad(4) = ayirac(3) 'Refresh Rate



                    'SendMessage(Args.senderIP, ayirac(1), "Flight Data Selected")

                    fnkStarStopFlightDataLoad("Start")

                    Exit Select

                Case "CmdStopFlightDataLoad"

                    fnkStarStopFlightDataLoad("Stop")

                    'SendMessage(Args.senderIP, ayirac(1), "Flight Data De Selected")
                    Exit Select

                Case "CmdStartFlightDataNav"

                    If vrFlightDataNav(0) <> Args.senderIP Then
                        fnkStarStopFlightDataNav("Stop")
                    End If

                    vrFlightDataNav(0) = Args.senderIP
                    vrFlightDataNav(1) = ayirac(1) 'vrMsgPort
                    vrFlightDataNav(2) = ayirac(2) 'vrImgPort
                    vrFlightDataNav(3) = ayirac(3) 'Refresh Rate



                    'SendMessage(Args.senderIP, ayirac(1), "Flight Data Selected")

                    fnkStarStopFlightDataNav("Start")

                    Exit Select

                Case "CmdStopFlightDataNav"

                    fnkStarStopFlightDataNav("Stop")

                    'SendMessage(Args.senderIP, ayirac(1), "Flight Data De Selected")
                    Exit Select

                Case "CmdStartFlightDataNavMap"

                    If vrFlightDataNavMap(0) <> Args.senderIP Then
                        fnkStarStopFlightDataNavMap("Stop")
                    End If

                    vrFlightDataNavMap(0) = Args.senderIP
                    vrFlightDataNavMap(1) = ayirac(1) 'vrMsgPort
                    vrFlightDataNavMap(2) = ayirac(2) 'vrImgPort
                    vrFlightDataNavMap(3) = ayirac(3) 'Refresh Rate
                    vrFlightDataNavMap(4) = ayirac(4) 'Harita Adı


                    'SendMessage(Args.senderIP, ayirac(1), "Flight Data Selected")

                    fnkStarStopFlightDataNavMap("Start")


                    If My.Computer.FileSystem.FileExists(vrFalconPath & "\User\Config\" & fnkFalconMFDDataXMLDegeriOku("KeyName", "CallSign", "KeyValue") & ".ini") Then
                        'MsgBox("File found.")    

                        If vrModifiedDataINI <> fnkGetLatestModifiedDate(fnkFalconMFDDataXMLDegeriOku("KeyName", "CallSign", "KeyValuePath")) Then
                            vrModifiedDataINI = fnkGetLatestModifiedDate(fnkFalconMFDDataXMLDegeriOku("KeyName", "CallSign", "KeyValuePath"))

                            txtCallSign.Text = fnkFalconMFDDataXMLDegeriOku("KeyName", "CallSign", "KeyValue")
                            fnkReadCallSign_INI_File(fnkFalconMFDDataXMLDegeriOku("KeyName", "CallSign", "KeyValuePath"))
                        End If

                        'SP ve ThreatPoint bilgilerini otomatik olarak gönderecek

                        fnkSendMapSPThData()

                    Else
                        'MsgBox("File not found.")

                    End If

                    Exit Select

                Case "CmdStopFlightDataNavMap"

                    fnkStarStopFlightDataNavMap("Stop")

                    'SendMessage(Args.senderIP, ayirac(1), "Flight Data De Selected")
                    Exit Select


                Case "CmdResetServer"
                    fnkStarStopDED("Stop")
                    fnkStarStopRWR("Stop")
                    fnkStarStopSagMFD("Stop")
                    fnkStarStopSolMFD("Stop")
                    fnkStarStopTest("Stop")
                    fnkStarStopFlightData("Stop")

                    fnkStarStopFlightDataLoad("Stop")
                    fnkStarStopFlightDataNav("Stop")
                    fnkStarStopFlightDataNavMap("Stop")

                    IncomingMessagesList.Items.Clear()

                    Exit Select

                Case Else
                    'Me.Close()



            End Select


            Exit Sub
        End If


        If Mid(Args.message, 1, 3) <> "Cmd" Then 'Tuş Satırı Geldiyse

            'Me.IncomingMessagesList.Items.Add(Args.senderIP)

            Dim ayirac() As String

            ayirac = Split(Args.message, "@")

            Dim vrGelenTus As String = fnkGelenTusDegerlendir(ayirac(0))

            'MsgBox(Args.message)




            If vrGelenTus <> "Key Does Not Match" Then
                IncomingMessagesList.Items.Add(Args.senderIP + " --> " + vrGelenTus)
                'SendMessage(Args.senderIP, ayirac(1), vrGelenTus)
            Else 'Eğer Key Bulunamadıysa Geriye Hata Mesajı Gönderecek
                IncomingMessagesList.Items.Add(Args.senderIP + " --> " + "(" + ayirac(0) + ") " + vrGelenTus)
                IncomingMessagesListError.Items.Add(Args.senderIP + " --> " + "(" + ayirac(0) + ") " + vrGelenTus)
                vrGelenTus = "Msg@" + "Error@" + vrGelenTus + "(" + ayirac(0) + ")"
                '(Sonra Düzeltilecek)
                'SendMessage(Args.senderIP, ayirac(1), vrGelenTus)
                'SendMessage(txtIp.Text, txtPortMsg.Value, txtMsg.Text)


            End If



        End If

    End Sub



    Public Function fnkGelenTusDondur(prGelenTus As String) As String
        Return prGelenTus
    End Function


    Dim m As Integer
    'Dim vrGelenTus As String = fnkGelenOSB(prGelenTus)
    Dim vrGelenTus As String

    Public Function fnkGelenTusDegerlendir(ByVal prGelenTus As String) As String

        Try
            vrGelenTus = prGelenTus


            'MsgBox(Array.IndexOf(vrKeys, prGelenTus))


            m = Array.IndexOf(vrKeys, prGelenTus)

            If m = -1 Then
                Return "Key Does Not Match"
                Exit Function
            End If


            If grdKeys.Rows(m).Cells("ColID").Value = vrGelenTus Then

                'MsgBox(grdKeys.Rows(m).Cells("CollModifiyelerAcik").Value & " " & grdKeys.Rows(m).Cells("ColButtonsAciklama").Value)
                'MsgBox(grdKeys.Rows(m).Cells("ColModifiers").Value & " " & grdKeys.Rows(m).Cells("ColButtons").Value)


                Select Case grdKeys.Rows(m).Cells("ColModifiers").Value
                    Case 0
                        frmKlavye.fnkTusGonder(Replace(grdKeys.Rows(m).Cells("ColButtons").Value, "0X", "&H"))
                        Exit Select
                    Case 1
                        frmKlavye.fnkTusGonder_Shift(Replace(grdKeys.Rows(m).Cells("ColButtons").Value, "0X", "&H"))
                        Exit Select
                    Case 2
                        frmKlavye.fnkTusGonder_Ctrl(Replace(grdKeys.Rows(m).Cells("ColButtons").Value, "0X", "&H"))
                        Exit Select
                    Case 3
                        frmKlavye.fnkTusGonder_Shift_Ctrl(Replace(grdKeys.Rows(m).Cells("ColButtons").Value, "0X", "&H"))
                        Exit Select
                    Case 4
                        frmKlavye.fnkTusGonder_Alt(Replace(grdKeys.Rows(m).Cells("ColButtons").Value, "0X", "&H"))
                        Exit Select
                    Case 5
                        frmKlavye.fnkTusGonder_Shift_Alt(Replace(grdKeys.Rows(m).Cells("ColButtons").Value, "0X", "&H"))
                        Exit Select
                    Case 6
                        frmKlavye.fnkTusGonder_Ctrl_Alt(Replace(grdKeys.Rows(m).Cells("ColButtons").Value, "0X", "&H"))
                        Exit Select
                    Case 7
                        frmKlavye.fnkTusGonder_Shift_Ctrl_Alt(Replace(grdKeys.Rows(m).Cells("ColButtons").Value, "0X", "&H"))
                        Exit Select
                End Select

                Return "( " & grdKeys.Rows(m).Cells("ColID").Value & " ) " & grdKeys.Rows(m).Cells("CollModifiyelerAcik").Value & " " & grdKeys.Rows(m).Cells("ColButtonsAciklama").Value


            End If
        Catch ex As Exception
            Return "Key Does Not Match"
        End Try


        'frmKlavye.fnkTusGonder(Args.message)
    End Function

    Public Sub fnkGetScreenShootAndSend(prIp As String, prPort As Integer, prIndex As Integer)

        'Ekran Görüntüsünü Alıp Gönderiyor

        Dim s As String = fnkFalconMFDDataXMLDegeriOku("KeyName", "Picture", "KeyValue")

        Dim files() As String = System.IO.Directory.GetFiles(s)
        System.Array.Sort(files, New FileInfoComparer)

        Try

            Dim bmp As New Bitmap(files(files.Length - prIndex))
            SendMessage(prIp, prPort, Convert.ToBase64String(imageToByteArray(bmp)))

        Catch ex As Exception
            Dim bmp As New Bitmap(files(files.Length - 1))
            SendMessage(prIp, prPort, Convert.ToBase64String(imageToByteArray(bmp)))
        End Try

        'frmKlavye.fnkTusGonder("32")

        'MessageBox.Show("Latest file: " & files(files.Length - 1))
        'MessageBox.Show("Oledest file: " & files(0))


    End Sub



    'Public Sub SendMessage(ByVal Ip As String, ByVal Port As Integer, ByVal Message As String)

    '    If Message.Length > 150 Then
    '        'Send TCP
    '        Try
    '            Dim clientX As New System.Net.Sockets.TcpClient
    '            Dim Buffer() As Byte = System.Text.Encoding.Default.GetBytes(Message.ToCharArray)
    '            clientX.Connect(Ip, Port)
    '            clientX.GetStream.Write(Buffer, 0, Buffer.Length)
    '            clientX.Close()
    '        Catch ex As Exception
    '            'MsgBox("Unable to connect to IP Adress")
    '            'FrmServer.fnkStarStopRWR("Stop")
    '        End Try
    '    Else
    '        Try
    '            'Send UDP
    '            Dim GLOIP As IPAddress
    '            Dim GLOINTPORT As Integer
    '            Dim bytCommand As Byte() = New Byte() {}
    '            Dim udpClient As New UdpClient
    '            GLOIP = IPAddress.Parse(Ip)
    '            GLOINTPORT = Port
    '            udpClient.Connect(GLOIP, GLOINTPORT)
    '            bytCommand = Encoding.ASCII.GetBytes(Message)
    '            udpClient.Send(bytCommand, bytCommand.Length)
    '        Catch ex As Exception

    '        End Try

    '    End If


    'End Sub



    Public Sub SendMessageThread(ByVal prData As ClsSendMessage)
        'Send TCP
        Try
            'Dim vrSendData As ClsSendMessage
            'vrSendData.Ip = prData.Ip
            'vrSendData.Message = prData.Message
            'vrSendData.Port = prData.Port

            Dim client As New System.Net.Sockets.TcpClient
            Dim Buffer() As Byte = System.Text.Encoding.Default.GetBytes(prData.Message.ToCharArray)
            client.Connect(prData.Ip, prData.Port)
            client.GetStream.Write(Buffer, 0, Buffer.Length)
            client.Close()
        Catch ex As Exception
            'MsgBox("Unable to connect to IP Adress")
            'FrmServer.fnkStarStopRWR("Stop")
        End Try
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
            MsgBox("Unable to connect to IP Adress")
            'FrmServer.fnkStarStopRWR("Stop")
        End Try
    End Sub

    Private Sub btnSendTestIMG_Click(sender As Object, e As EventArgs) Handles btnSendTestIMG.Click
        Dim bmp As New Bitmap(picMFD.Image)

        Dim ms As New MemoryStream()
        ' Save to memory using the Jpeg format
        bmp.Save(ms, System.Drawing.Imaging.ImageFormat.Png)



        Dim ms1 As New MemoryStream(Convert.FromBase64String(Convert.ToBase64String(ms.ToArray())))
        Dim bmp1 As Bitmap = Bitmap.FromStream(ms1)

        picIMGAll.Image = bmp1

        If txtIp.Text = "" Then
            MsgBox("Please Enter IP of Android Tablet", vbCritical)
            Exit Sub
        End If

        SendMessage(txtIp.Text, txtPortLMFD.Value, Convert.ToBase64String(ms.ToArray()))
        SendMessage(txtIp.Text, txtPortRMFD.Value, Convert.ToBase64String(ms.ToArray()))

        ms.Close()
    End Sub

    Private Sub btnReadDED_RWR_Click(sender As Object, e As EventArgs) Handles btnReadDED_RWR.Click

        If txtIp.Text = "" Then
            MsgBox("Please Enter IP of Android Tablet", vbCritical)
            Exit Sub
        End If

        vrRWR(0) = txtIp.Text
        vrRWR(1) = txtPortMsg.Value  'vrMsgPort
        vrRWR(2) = txtPortRWR.Value  'vrImgPort
        vrRWR(3) = txtRefreshTime.Value  ' Refresh Rate

        vrDED(0) = txtIp.Text
        vrDED(1) = txtPortMsg.Value  'vrMsgPort
        vrDED(2) = txtPortDED.Value  'vrImgPort
        vrDED(3) = txtRefreshTime.Value  ' Refresh Rate

        fnkStarStopRWR("Start")

        fnkStarStopDED("Start")

    End Sub

    Private Sub btnMustDeclare_Click(sender As Object, e As EventArgs) Handles btnMustDeclare.Click
        fnkXMLOlusturMustDeclare()
    End Sub


    Private Sub btnSolMFD_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnSolMFD.Click
        btnSagMFD.Enabled = True
        btnSolMFD.Enabled = False
        btnSagMFD.Focus()
    End Sub

    Private Sub btnSagMFD_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnSagMFD.Click
        btnSagMFD.Enabled = False
        btnSolMFD.Enabled = True
        btnSolMFD.Focus()
    End Sub



    Private Sub btn1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn1.Click, btn2.Click, btn10.Click, btn11.Click, btn3.Click, btn4.Click, btn5.Click, btn6.Click, btn7.Click, btn8.Click, btn9.Click, btn12.Click, btn13.Click, btn14.Click, btn15.Click, btn16.Click, btn17.Click, btn18.Click, btn19.Click, btn20.Click, btn21.Click, btn22.Click, btn23.Click, btn24.Click, btn25.Click, btn26.Click, btn27.Click, btn28.Click

        fnkSecilenBtn(sender)
    End Sub


    Public Sub fnkSecilenBtn(ByVal btn As Button)

        Dim vrSolSag As Integer = 0

        If btnSolMFD.Enabled = False Then
            vrSolSag = 0
        Else
            vrSolSag = 28
        End If



        Dim vrBtnName As String = Mid(btn.Name, 4, 2)

        'MsgBox(fnkGelenOSB(vrBtnName + vrSolSag))

        Dim m As Integer
        Dim vrGelenTus As String = fnkGelenOSB(vrBtnName + vrSolSag)

        grdKeys.ClearSelection()

        For m = 0 To grdKeys.RowCount - 1
            If grdKeys.Rows(m).Cells("ColID").Value = vrGelenTus Then

                grdKeys.Rows(m).Selected = True

                grdKeys.FirstDisplayedScrollingRowIndex = m
                Exit For
            End If
        Next
    End Sub

    Public Function fnkGelenOSB(ByVal prOSB As Integer) As String


        Select Case prOSB
            Case 28
                Return "SimCBEOSB_BRTDOWN_L"
            Case 27
                Return "SimCBEOSB_BRTUP_L"
            Case 1
                Return "SimCBEOSB_1L"
            Case 2
                Return "SimCBEOSB_2L"
            Case 3
                Return "SimCBEOSB_3L"
            Case 4
                Return "SimCBEOSB_4L"
            Case 5
                Return "SimCBEOSB_5L"
            Case 6
                Return "SimCBEOSB_6L"
            Case 7
                Return "SimCBEOSB_7L"
            Case 8
                Return "SimCBEOSB_8L"
            Case 9
                Return "SimCBEOSB_9L"
            Case 10
                Return "SimCBEOSB_10L"
            Case 11
                Return "SimCBEOSB_11L"
            Case 12
                Return "SimCBEOSB_12L"
            Case 13
                Return "SimCBEOSB_13L"
            Case 14
                Return "SimCBEOSB_14L"
            Case 15
                Return "SimCBEOSB_15L"
            Case 16
                Return "SimCBEOSB_16L"
            Case 17
                Return "SimCBEOSB_17L"
            Case 18
                Return "SimCBEOSB_18L"
            Case 19
                Return "SimCBEOSB_19L"
            Case 20
                Return "SimCBEOSB_20L"
            Case 55
                Return "SimCBEOSB_BRTDOWN_R"
            Case 56
                Return "SimCBEOSB_BRTUP_R"
            Case 29
                Return "SimCBEOSB_1R"
            Case 30
                Return "SimCBEOSB_2R"
            Case 31
                Return "SimCBEOSB_3R"
            Case 32
                Return "SimCBEOSB_4R"
            Case 33
                Return "SimCBEOSB_5R"
            Case 34
                Return "SimCBEOSB_6R"
            Case 35
                Return "SimCBEOSB_7R"
            Case 36
                Return "SimCBEOSB_8R"
            Case 37
                Return "SimCBEOSB_9R"
            Case 38
                Return "SimCBEOSB_10R"
            Case 39
                Return "SimCBEOSB_11R"
            Case 40
                Return "SimCBEOSB_12R"
            Case 41
                Return "SimCBEOSB_13R"
            Case 42
                Return "SimCBEOSB_14R"
            Case 43
                Return "SimCBEOSB_15R"
            Case 44
                Return "SimCBEOSB_16R"
            Case 45
                Return "SimCBEOSB_17R"
            Case 46
                Return "SimCBEOSB_18R"
            Case 47
                Return "SimCBEOSB_19R"
            Case 48
                Return "SimCBEOSB_20R"

        End Select

    End Function

    Public Sub fnkSetAirCraft(prAirCraft As String)

        If prAirCraft = "Others" Or prAirCraft = "F16" Then ' F16 - A10 - AV 8A/B - F15 - Tornado - F4 - Mig29 - JAS 37

            ''ICP/ded
            'vrICPop(0, 0) = 575
            'vrICPop(0, 1) = 140
            'vrICPop(0, 2) = 400
            'vrICPop(0, 3) = 150

            ''SolMFD
            'vrSolMFD(0, 0) = 753
            'vrSolMFD(0, 1) = 753
            'vrSolMFD(0, 2) = 443
            'vrSolMFD(0, 3) = 443

            ''SağMFD
            'vrSagMFD(0, 0) = 753
            'vrSagMFD(0, 1) = 293
            'vrSagMFD(0, 2) = 443
            'vrSagMFD(0, 3) = 443

            ''RWR
            'vrRWRoP(0, 0) = 960
            'vrRWRoP(0, 1) = 0
            'vrRWRoP(0, 2) = 240
            'vrRWRoP(0, 3) = 240

            'ICP/ded
            vrICPop(0, 0) = 291
            vrICPop(0, 1) = 75
            vrICPop(0, 2) = 480 - vrICPop(0, 0)
            vrICPop(0, 3) = 140 - vrICPop(0, 1)

            'SolMFD
            vrSolMFD(0, 0) = 377
            vrSolMFD(0, 1) = 377
            vrSolMFD(0, 2) = 597 - vrSolMFD(0, 0)
            vrSolMFD(0, 3) = 597 - vrSolMFD(0, 1)

            'SağMFD
            vrSagMFD(0, 0) = 377
            vrSagMFD(0, 1) = 147
            vrSagMFD(0, 2) = 597 - vrSagMFD(0, 0)
            vrSagMFD(0, 3) = 367 - vrSagMFD(0, 1)

            'RWR
            vrRWRoP(0, 0) = 482
            vrRWRoP(0, 1) = 0
            vrRWRoP(0, 2) = 598 - vrRWRoP(0, 0)
            vrRWRoP(0, 3) = 116 - vrRWRoP(0, 1)

        End If

        If prAirCraft = "F18" Then

            'ICP/ded
            'WinXP / 7
            vrICPop(0, 0) = 630
            vrICPop(0, 1) = 10
            vrICPop(0, 2) = 600
            vrICPop(0, 3) = 153

            'SolMFD
            'WinXP / 7
            vrSolMFD(0, 0) = 3
            vrSolMFD(0, 1) = 179
            vrSolMFD(0, 2) = 443
            vrSolMFD(0, 3) = 443

            'SağMFD
            'WinXP / 7
            vrSagMFD(0, 0) = 459
            vrSagMFD(0, 1) = 179
            vrSagMFD(0, 2) = 443
            vrSagMFD(0, 3) = 443

            'RWR
            'WinXP / 7
            vrRWRoP(0, 0) = 958
            vrRWRoP(0, 1) = 380
            vrRWRoP(0, 2) = 190
            vrRWRoP(0, 3) = 190


        End If

        If prAirCraft = "Mirage" Then

            'ICP/ded
            'WinXP / 7
            vrICPop(0, 0) = 25
            vrICPop(0, 1) = 545
            vrICPop(0, 2) = 305
            vrICPop(0, 3) = 115

            'SolMFD
            'WinXP / 7
            vrSolMFD(0, 0) = 533
            vrSolMFD(0, 1) = 13
            vrSolMFD(0, 2) = 343
            vrSolMFD(0, 3) = 343

            'SağMFD
            'WinXP / 7
            vrSagMFD(0, 0) = 533
            vrSagMFD(0, 1) = 373
            vrSagMFD(0, 2) = 343
            vrSagMFD(0, 3) = 343

            'RWR
            'WinXP / 7
            vrRWRoP(0, 0) = 0
            vrRWRoP(0, 1) = 830
            vrRWRoP(0, 2) = 178
            vrRWRoP(0, 3) = 178

        End If

        If prAirCraft = "F14" Then

            'ICP/ded
            vrICPop(0, 0) = 20
            vrICPop(0, 1) = 612
            vrICPop(0, 2) = 370
            vrICPop(0, 3) = 100

            'SolMFD
            vrSolMFD(0, 0) = 532
            vrSolMFD(0, 1) = 12
            vrSolMFD(0, 2) = 295
            vrSolMFD(0, 3) = 295

            'SağMFD
            vrSagMFD(0, 0) = 532
            vrSagMFD(0, 1) = 532
            vrSagMFD(0, 2) = 295
            vrSagMFD(0, 3) = 295

            'RWR
            vrRWRoP(0, 0) = 0
            vrRWRoP(0, 1) = 832
            vrRWRoP(0, 2) = 178
            vrRWRoP(0, 3) = 178
        End If

    End Sub



    Private Sub Button4_Click(sender As Object, e As EventArgs) Handles Button4.Click
        pnlResim.Hide()
    End Sub


    Public Sub fnkResimDosyasiniGoster()

        Try

            If fnkFalconMFDDataXMLDegeriOku("KeyName", "Picture", "KeyValue") = "" Then ' Değer Tanımlı değilse
                If vrFalconPath <> "" Then

                    fnkFalconMFDDataXMLGuncelle("KeyName", "Picture", "KeyValue", vrFalconPath & "\User\Pictures")

                Else
                    'folder.Tag = "Select Screen Shoots Folder For Falcon"
                    folder.Description = "Please Select Screen Shoot Folder" & vbCrLf & "...\Falcon BMS 4.32\User\Pictures\"
                    folder.ShowDialog()

                    If folder.SelectedPath = "" Then
                        MsgBox("Please Select Screen Shoot Folder" & vbCrLf & "...\Falcon BMS 4.32\User\Pictures\" & vbCrLf & vbCrLf & "Falcon Server will be closed", vbCritical)
                        Close()
                        Exit Sub
                    End If

                    fnkFalconMFDDataXMLGuncelle("KeyName", "Picture", "KeyValue", folder.SelectedPath)


                End If

            End If

        Catch ex As Exception

        End Try



    End Sub



    Private Sub btnReadFlightData_Click(sender As Object, e As EventArgs) Handles btnReadFlightData.Click
        'fnkStarStopFlightDataNav("Start")
        fnkStarStopFlightDataNavMap("Start")
    End Sub

    Private Sub chkShowError_CheckedChanged(sender As Object, e As EventArgs) Handles chkShowError.CheckedChanged
        If chkShowError.Checked Then
            IncomingMessagesList.Visible = False
            IncomingMessagesListError.Visible = True
        Else
            IncomingMessagesList.Visible = True
            IncomingMessagesListError.Visible = False
        End If
    End Sub

    Private Sub grdKeys_Sorted(sender As Object, e As EventArgs) Handles grdKeys.Sorted
        ReDim vrKeys(grdKeys.RowCount)

        Dim m As Integer
        For m = 0 To grdKeys.RowCount - 1
            vrKeys(m) = grdKeys.Rows(m).Cells("ColID").Value
        Next
    End Sub



    Private Sub btnSendMSGCallSgn_Click(sender As Object, e As EventArgs) Handles btnSendMSGCallSgn.Click
        If txtIp.Text = "" Then
            MsgBox("Please Enter IP of Android Tablet", vbCritical)
            Exit Sub
        End If


        'Dim ThSendMessage = New Thread(AddressOf SendMessageThread)
        'Dim vrParameters As New ClsSendMessage

        'vrParameters.Ip = txtIp.Text
        'vrParameters.Message = txtMsg.Text
        'vrParameters.Port = txtPortMsg.Value

        'ThSendMessage.Start(vrParameters)


        'Gerekli Variable lar
        vrFlightDataNavMap(0) = txtIp.Text
        vrFlightDataNavMap(1) = txtPortMsg.Value 'vrMsgPort
        'vrFlightDataNavMap(2) = ayirac(2) 'vrImgPort
        'vrFlightDataNavMap(3) = ayirac(3) 'Refresh Rate
        vrFlightDataNavMap(4) = "KOREA" 'Harita Adı

        fnkSendMapSPThData()

    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="prINIPath"> "..\" </param>
    ''' <param name="prCallSign">"KOMURCU.ini"</param>
    ''' <remarks></remarks>
    Public Sub fnkReadCallSign_INI_File(prINIPath As String)
        Dim cnt As Integer = 0

        grdCallSignData.RowCount = 0
        grdCallSignData.Rows.Clear()


        Try
            vrINISource = New IniConfigSource(prINIPath)
            Dim vrX() As String

            '******************************    Steer Points     ********************************************

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_0"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_0"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)



            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_1"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_1"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_2"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_2"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_3"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_3"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_4"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_4"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_5"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_5"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_6"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_6"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_7"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_7"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_8"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_8"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_9"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_9"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_10"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_10"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_11"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_11"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_12"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_12"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_13"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_13"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_14"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_14"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_15"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_15"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_16"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_16"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_17"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_17"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_18"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_18"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_19"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_19"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_20"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_20"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_21"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_21"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_22"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_22"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target_23"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("target_23"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            '******************************    Targets     ********************************************

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "ppt_0"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("ppt_0"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "ppt_1"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("ppt_1"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1


            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "ppt_2"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("ppt_2"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "ppt_3"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("ppt_3"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "ppt_4"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("ppt_4"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "ppt_5"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("ppt_5"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "ppt_6"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("ppt_6"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "ppt_7"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("ppt_7"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "ppt_8"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("ppt_8"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "ppt_9"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("ppt_9"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "ppt_10"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("ppt_10"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "ppt_11"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("ppt_11"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "ppt_12"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("ppt_12"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "ppt_13"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("ppt_13"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1

            grdCallSignData.Rows.Add()
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "ppt_14"

            vrX = Split(grdCallSignData.Rows(cnt).Cells("ColKeyType").Value, "_")
            grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = vrX(0)
            grdCallSignData.Rows(cnt).Cells("ColKeyName").Value = vrX(1)

            vrX = Split(vrINISource.Configs("STPT").Get("ppt_14"), "   ")
            grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = vrX(0)

            cnt += 1



            'If InStr(cmbBMSVerion.Text, "BMS 4.33") Then
            '    For cnt = 0 To grdCallSignData.RowCount - 1
            '        grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = Replace(grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value, ", Not set", "")
            '    Next
            'End If

            'Bilgileri Göndermek için Hazırlayacak
            Dim vrStr() As String

            For cnt = grdCallSignData.RowCount - 1 To 0 Step -1
                grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = Replace(grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value, ",", "@")
                grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = Replace(grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value, " ", "")

                grdCallSignData.Rows(cnt).Cells("ColKeyName").Value += 1

                vrStr = Split(grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value.ToString, "@")

                If grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target" Then
                    grdCallSignData.Rows(cnt).Cells("ColSPTypeVal").Value = vrStr(3)
                    grdCallSignData.Rows(cnt).Cells("ColSPTypeName").Value = fnkSPTypeName(vrStr(3))
                End If


                'If CDec(vrStr(0)) = 0 Then
                '    grdCallSignData.Rows.RemoveAt(cnt)
                'End If

            Next

            '            GoTo cikis

            '            For cnt = 0 To grdCallSignData.RowCount - 1

            '                If cnt < grdCallSignData.RowCount - 1 Then

            '                    If grdCallSignData.Rows(cnt + 1).Cells("ColKeyType").Value = "target" And grdCallSignData.Rows(cnt + 1).Cells("ColSPTypeVal").Value <> -1 Then

            '                        grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value & "@" & grdCallSignData.Rows(cnt + 1).Cells("ColKeyValue").Value
            '                        'Else
            '                        '    grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = ""

            '                    End If
            '                    'Else
            '                    '    If grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target" Then
            '                    '        grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value = ""
            '                    '    End If

            '                End If

            '            Next

            'cikis:

            For cnt = 0 To grdCallSignData.RowCount - 1

                'If grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value.ToString = "" Then
                '    grdCallSignData.Rows.RemoveAt(cnt)
                'End If

                fnkHataKayit("fnkReadCallSign_INI_File Log", grdCallSignData.Rows(cnt).Cells("ColKeyType").Value.ToString & vbTab & grdCallSignData.Rows(cnt).Cells("ColKeyName").Value.ToString & vbTab & grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value.ToString)

            Next


        Catch ex As Exception
            fnkHataKayit("fnkReadCallSign_INI_File", ex.ToString)
            fnkFalconMFDDataXMLOlustur()
        End Try

    End Sub



    Public Function fnkSPTypeName(prSPTypeVal As String) As String
        Select Case prSPTypeVal
            Case "-1" 'Target
                Return "Target"
            Case "0" 'Nav 
                ' TO & Land 
                'Return "Nav - TO & Land"
                Return "Nav"
            Case "1" 'takeoff (fallthrogh)
                Return "Takeoff"
            Case "7" 'Landing (fallthrogh)
                Return "Landing"
                ' Package 
            Case "2" 'Package RNDZ
                Return "Push"
            Case "3" 'Package break
                Return "Splite"
                ' Enrout tasks
            Case "4" 'refuel
                Return "Refuel"
            Case "5" 'Rearm
                Return "Rearm"
            Case "8" 'Timing
                Return "Timing"
            Case "9" 'Contact
                Return "Contact"
                ' F-16 Action point
            Case "10" 'Escort
                Return "Escort"
            Case "11" 'sweep
                Return "Sweep"
            Case "12" 'CAP
                Return "CAP"
            Case "13" 'Intercept
                Return "Intercept"
            Case "14" 'Attack
                Return "Attack"
            Case "15" 'Attack
                Return "Attack"
            Case "16" 'S&D
                Return "S&D"
            Case "17" 'Strike
                Return "Strike"
            Case "18" 'Bomb
                Return "Bomb"
            Case "19" 'SEAD
                Return "SEAD"
                ' Other tasks
            Case "6" 'Pickup     
                Return "Pickup"
            Case "20" 'ELINT
                Return "ELINT"
            Case "21" 'Reacon
                Return "Reacon"
            Case "22" 'Rescue
                Return "Rescue"
            Case "23" 'ASW
                Return "ASW"
            Case "24" 'Fuel
                Return "Fuel"
            Case "25" 'Airdrop
                Return "Airdrop"
            Case "26" 'Jam
                Return "Jam"




        End Select
    End Function



    Public Sub fnkSendMapSPThData()

        'Gerekli Variable lar
        'vrFlightDataNavMap(0) = Args.senderIP
        'vrFlightDataNavMap(1) = ayirac(1) 'vrMsgPort
        'vrFlightDataNavMap(2) = ayirac(2) 'vrImgPort
        'vrFlightDataNavMap(3) = ayirac(3) 'Refresh Rate
        'vrFlightDataNavMap(4) = ayirac(4) 'Harita Adı
        Dim vrStr() As String
        Dim vrStrNext() As String
        Dim cnt As Integer

        Dim vrMapSpLine As Boolean = True
        Try
            For cnt = 0 To grdCallSignData.RowCount - 1
                If grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "target" Then 'SP

                    vrStr = Split(grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value, "@")

                    Dim ThSendMessage = New Thread(AddressOf SendMessageThread)
                    Dim vrParameters As New ClsSendMessage

                    vrParameters.Ip = vrFlightDataNavMap(0)
                    'vrParameters.Message = "MapSP" & "@" & grdCallSignData.Rows(cnt).Cells("ColKeyName").Value _
                    '                               & "@" & fnkHaritaCoordinatHesaplaX(vrStr(1), vrFlightDataNavMap(4)) _
                    '                               & "@" & fnkHaritaCoordinatHesaplaY(vrStr(0), vrFlightDataNavMap(4))

                    vrParameters.Message = "MapSP" & "@" & Replace(vrStr(4), "Notset", grdCallSignData.Rows(cnt).Cells("ColKeyName").Value) _
                                                   & "@" & fnkHaritaCoordinatHesaplaX(vrStr(1), vrFlightDataNavMap(4)) _
                                                   & "@" & fnkHaritaCoordinatHesaplaY(vrStr(0), vrFlightDataNavMap(4))
                    vrParameters.Port = vrFlightDataNavMap(1)

                    'Console.WriteLine(vrParameters.Message)
                    'fnkHataKayit("fnkSendMapSPThData Log", vrParameters.Message)

                    ThSendMessage.Start(vrParameters)
                    'Thread.Sleep(100)

                    Try

                        vrStr = Split(grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value, "@")
                        vrStrNext = Split(grdCallSignData.Rows(cnt + 1).Cells("ColKeyValue").Value, "@")

                        If CDec(vrStr(0)) = 0 Or CDec(vrStrNext(0)) = 0 Then
                            vrMapSpLine = False
                        End If

                        If vrMapSpLine = True Then
                            Dim ThSendMessageLine = New Thread(AddressOf SendMessageThread)
                            Dim vrParametersLine As New ClsSendMessage

                            vrParametersLine.Ip = vrFlightDataNavMap(0)
                            vrParametersLine.Message = "MapSPLine" & "@" & grdCallSignData.Rows(cnt).Cells("ColKeyName").Value _
                                                               & "@" & fnkHaritaCoordinatHesaplaX(vrStr(1), vrFlightDataNavMap(4)) _
                                                               & "@" & fnkHaritaCoordinatHesaplaY(vrStr(0), vrFlightDataNavMap(4)) _
                                                               & "@" & fnkHaritaCoordinatHesaplaX(vrStrNext(1), vrFlightDataNavMap(4)) _
                                                               & "@" & fnkHaritaCoordinatHesaplaY(vrStrNext(0), vrFlightDataNavMap(4))
                            vrParametersLine.Port = vrFlightDataNavMap(1)

                            'Console.WriteLine(vrParametersLine.Message)
                            'fnkHataKayit("fnkSendMapSPThData Log", vrParametersLine.Message)

                            ThSendMessageLine.Start(vrParametersLine)
                            'Thread.Sleep(100)
                        End If

                    Catch SPLine As Exception
                        fnkHataKayit("fnkSendMapSPThData SPLine (Normal Sayılır)", SPLine.ToString)
                    End Try


                End If


                If grdCallSignData.Rows(cnt).Cells("ColKeyType").Value = "ppt" Then 'SP

                    vrStr = Split(grdCallSignData.Rows(cnt).Cells("ColKeyValue").Value, "@")

                    Dim ThSendMessage = New Thread(AddressOf SendMessageThread)
                    Dim vrParameters As New ClsSendMessage

                    vrParameters.Ip = vrFlightDataNavMap(0)
                    vrParameters.Message = "MapTR" & "@" & vrStr(4) & "@" & fnkHaritaCoordinatHesaplaX(vrStr(1), vrFlightDataNavMap(4)) & "@" & fnkHaritaCoordinatHesaplaY(vrStr(0), vrFlightDataNavMap(4)) & "@" & CInt(fnkHaritaCoordinatHesaplaX(vrStr(3), vrFlightDataNavMap(4)))

                    vrParameters.Port = vrFlightDataNavMap(1)

                    'Console.WriteLine(vrParameters.Message)
                    'fnkHataKayit("fnkSendMapSPThData Log", vrParameters.Message)


                    ThSendMessage.Start(vrParameters)
                    'Thread.Sleep(100)

                End If

            Next
        Catch ex As Exception
            fnkHataKayit("fnkSendMapSPThData ex", ex.ToString)
        End Try




    End Sub

    Public Function fnkHaritaCoordinatHesaplaX(prCoor As String, prHarita As String) As String
        fnkHaritaCoordinatHesaplaX = 0

        Dim vrStr() As String = Split(prCoor, ".")

        prCoor = vrStr(0)

        'Hesaplama

        '------------------------ XCoordinate ---------------------------
        If prHarita = "EMF" Then
            fnkHaritaCoordinatHesaplaX = Math.Round((prCoor * vrWidthHeight / XMaxEMF), 0)
        Else
            fnkHaritaCoordinatHesaplaX = Math.Round((vrWidthHeight) / (XMax - XMin) * (prCoor - XMin), 0)
        End If

        Return fnkHaritaCoordinatHesaplaX
    End Function

    Public Function fnkHaritaCoordinatHesaplaY(prCoor As String, prHarita As String) As String
        fnkHaritaCoordinatHesaplaY = 0

        Dim vrStr() As String = Split(prCoor, ".")

        prCoor = vrStr(0)

        'Hesaplama

        '------------------------ YCoordinate ---------------------------
        If prHarita = "EMF" Then
            fnkHaritaCoordinatHesaplaY = Math.Round(vrWidthHeight - (prCoor * vrWidthHeight / YMaxEMF), 0)
        Else
            fnkHaritaCoordinatHesaplaY = Math.Round(vrWidthHeight - (prCoor - YMin) / ((YMax - YMin) / vrWidthHeight), 0)
        End If

        Return fnkHaritaCoordinatHesaplaY
    End Function


    Private Sub btnChangeCallSign_Click(sender As Object, e As EventArgs) Handles btnChangeCallSign.Click
        fnkCallSignSave()
    End Sub

    Public Sub fnkCallSignSave()
        Dim vrTmp As String = InputBox("Enter Call Sign", "Falcon MFD", "Viper")
        If vrTmp <> "" Then

            'Picture Resim Güncelleme
            fnkFalconMFDDataXMLGuncelle("KeyName", "Picture", "KeyValuePath", vrFalconPath & "\User\Pictures\")
            'CallSign Data Güncelleme
            fnkFalconMFDDataXMLGuncelle("KeyName", "CallSign", "KeyValue", vrTmp)
            fnkFalconMFDDataXMLGuncelle("KeyName", "CallSign", "KeyValuePath", vrFalconPath & "\User\Config\" & vrTmp & ".ini")

            txtCallSign.Text = vrTmp

            If My.Computer.FileSystem.FileExists(vrFalconPath & "\User\Config\" & vrTmp & ".ini") Then
                'MsgBox("File found.")    

                If vrModifiedDataINI <> fnkGetLatestModifiedDate(fnkFalconMFDDataXMLDegeriOku("KeyName", "CallSign", "KeyValuePath")) Then
                    vrModifiedDataINI = fnkGetLatestModifiedDate(fnkFalconMFDDataXMLDegeriOku("KeyName", "CallSign", "KeyValuePath"))

                    txtCallSign.Text = fnkFalconMFDDataXMLDegeriOku("KeyName", "CallSign", "KeyValue")
                    fnkReadCallSign_INI_File(fnkFalconMFDDataXMLDegeriOku("KeyName", "CallSign", "KeyValuePath"))
                End If

            Else
                'MsgBox("File not found.")
                MsgBox(vrFalconPath & "\User\Config\" & vrTmp & ".ini" & vbCrLf & "Does Not Exist" & vbCrLf & "Falcon MFD cannot read Steerpoint", vbCritical)
            End If

        End If
    End Sub





    Public Sub fnkHataKayit(prfnk As String, prHata As String, Optional prAppend As Boolean = True)


        Dim file As System.IO.StreamWriter
        file = My.Computer.FileSystem.OpenTextFileWriter("FalconBMS.log", prAppend)
        file.WriteLine(prfnk & vbTab & vbTab & System.DateTime.Now.ToString & vbTab & vbTab & prHata)
        file.Close()

    End Sub


    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        'Dim bmp As New Bitmap("map_bos_sp.png")

        'Dim gr As Graphics = Graphics.FromImage(bmp)

        'Dim p1 As New Pen(Color.Blue)
        'Dim x1, y1, x2, y2 As Integer

        'Dim b As Brush = New SolidBrush(Color.Red)
        'Dim f As Font = New Font("Arial", 10.0F, FontStyle.Bold)

        'x1 = 0
        'y1 = 0
        'x2 = 828
        'y2 = 828

        'gr.DrawLine(p1, x1, y1, x2, y2)
        'gr.DrawString("SA2", f, b, 200, 150)

        'picIMGAll.Image = bmp

        'Dim ms As New MemoryStream()
        '' Save to memory using the Jpeg format
        'bmp.Save(ms, System.Drawing.Imaging.ImageFormat.Png)



        'Dim ms1 As New MemoryStream(Convert.FromBase64String(Convert.ToBase64String(ms.ToArray())))
        'Dim bmp1 As Bitmap = Bitmap.FromStream(ms1)

        'picIMGAll.Image = bmp1

        'If txtIp.Text = "" Then
        '    MsgBox("Please Enter IP of Android Tablet", vbCritical)
        '    Exit Sub
        'End If

        'SendMessage(txtIp.Text, txtPortMisSch.Value, Convert.ToBase64String(ms.ToArray()))
        'ms.Close()


        'Dim bmp As New Bitmap(picMFD.Image)

        'Dim ms As New MemoryStream()
        '' Save to memory using the Jpeg format
        'bmp.Save(ms, System.Drawing.Imaging.ImageFormat.Png)



        'Dim ms1 As New MemoryStream(Convert.FromBase64String(Convert.ToBase64String(ms.ToArray())))
        'Dim bmp1 As Bitmap = Bitmap.FromStream(ms1)

        'picIMGAll.Image = bmp1

        'If txtIp.Text = "" Then
        '    MsgBox("Please Enter IP of Android Tablet", vbCritical)
        '    Exit Sub
        'End If

        'SendMessage(txtIp.Text, txtPortMisSch.Value, Convert.ToBase64String(ms.ToArray()))

        'ms.Close()

        fnkGetScreenShootAndSend(txtIp.Text, txtPortMisSch.Value, 1)

    End Sub

    Private Sub cmbBMSVerion_SelectedIndexChanged(sender As Object, e As EventArgs) Handles cmbBMSVerion.SelectedIndexChanged
        If vrAcildi = True Then
            fnkGetFalconInstalledPath(cmbBMSVerion.Text)
            fnkFalconMFDDataXMLOlustur()
            fnkReadCallSign_INI_File(fnkFalconMFDDataXMLDegeriOku("KeyName", "CallSign", "KeyValuePath"))
        End If

    End Sub

    Private Sub cmbAirCraft_SelectedIndexChanged(sender As Object, e As EventArgs) Handles cmbAirCraft.SelectedIndexChanged
        'If vrAcildi = True Then
        fnkSetAirCraft(cmbAirCraft.Text)
        'End If
    End Sub

    Private Sub txtIp_TextChanged(sender As Object, e As EventArgs) Handles txtIp.TextChanged
        Try
            fnkFalconMFDDataXMLGuncelle("KeyName", "AndroidIP", "KeyValue", txtIp.Text)
        Catch ex As Exception

        End Try

    End Sub

    Private Sub Button3_Click(sender As Object, e As EventArgs) Handles Button3.Click
        Dim bt As Bitmap
        bt = picIMGAll.Image
        bt.Save("d:\ekran.png")

    End Sub
End Class



Public Class ClsSendMessage
    Public Property Ip As String
    Public Property Port As Integer
    Public Property Message As String
End Class

Public Class FileInfoComparer
    Implements IComparer

    Public Function Compare(ByVal x As Object, ByVal y As Object) As Integer Implements System.Collections.IComparer.Compare

        Dim firstFile As New System.IO.FileInfo(x.ToString)
        Dim secondFile As New System.IO.FileInfo(y.ToString)

        Return DateTime.Compare(firstFile.LastWriteTime, secondFile.LastWriteTime)

    End Function

End Class
