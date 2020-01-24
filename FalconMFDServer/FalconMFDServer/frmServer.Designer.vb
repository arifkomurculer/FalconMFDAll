<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class frmServer
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()>
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()>
    Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Dim DataGridViewCellStyle1 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim DataGridViewCellStyle2 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(frmServer))
        Me.Panel1 = New System.Windows.Forms.Panel()
        Me.btnFlightDataLoad = New System.Windows.Forms.Button()
        Me.btnFlightDataNav = New System.Windows.Forms.Button()
        Me.btnFlightDataNavMap = New System.Windows.Forms.Button()
        Me.Label7 = New System.Windows.Forms.Label()
        Me.btnFlightData = New System.Windows.Forms.Button()
        Me.btnRWR = New System.Windows.Forms.Button()
        Me.btnICP = New System.Windows.Forms.Button()
        Me.btnRightMFD = New System.Windows.Forms.Button()
        Me.btnLeftMfd = New System.Windows.Forms.Button()
        Me.ofDB = New System.Windows.Forms.OpenFileDialog()
        Me.pnlSol = New System.Windows.Forms.Panel()
        Me.txtKeyFile = New System.Windows.Forms.RichTextBox()
        Me.btnOpenClose = New System.Windows.Forms.Button()
        Me.btnFindKeyFile = New System.Windows.Forms.Button()
        Me.pnlMFD = New System.Windows.Forms.Panel()
        Me.Button2 = New System.Windows.Forms.Button()
        Me.btnSagMFD = New System.Windows.Forms.Button()
        Me.btnSolMFD = New System.Windows.Forms.Button()
        Me.btn28 = New System.Windows.Forms.Button()
        Me.btn27 = New System.Windows.Forms.Button()
        Me.btn26 = New System.Windows.Forms.Button()
        Me.btn25 = New System.Windows.Forms.Button()
        Me.btn24 = New System.Windows.Forms.Button()
        Me.btn23 = New System.Windows.Forms.Button()
        Me.btn22 = New System.Windows.Forms.Button()
        Me.btn21 = New System.Windows.Forms.Button()
        Me.btn16 = New System.Windows.Forms.Button()
        Me.btn17 = New System.Windows.Forms.Button()
        Me.btn18 = New System.Windows.Forms.Button()
        Me.btn19 = New System.Windows.Forms.Button()
        Me.btn20 = New System.Windows.Forms.Button()
        Me.btn11 = New System.Windows.Forms.Button()
        Me.btn12 = New System.Windows.Forms.Button()
        Me.btn13 = New System.Windows.Forms.Button()
        Me.btn14 = New System.Windows.Forms.Button()
        Me.btn15 = New System.Windows.Forms.Button()
        Me.btn10 = New System.Windows.Forms.Button()
        Me.btn9 = New System.Windows.Forms.Button()
        Me.btn8 = New System.Windows.Forms.Button()
        Me.btn7 = New System.Windows.Forms.Button()
        Me.btn6 = New System.Windows.Forms.Button()
        Me.btn5 = New System.Windows.Forms.Button()
        Me.btn4 = New System.Windows.Forms.Button()
        Me.btn3 = New System.Windows.Forms.Button()
        Me.btn2 = New System.Windows.Forms.Button()
        Me.btn1 = New System.Windows.Forms.Button()
        Me.picMFD = New System.Windows.Forms.PictureBox()
        Me.GroupBox2 = New System.Windows.Forms.GroupBox()
        Me.grdIPList = New System.Windows.Forms.DataGridView()
        Me.colIPList = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.cmbBMSVerion = New System.Windows.Forms.ComboBox()
        Me.btnChangeCallSign = New System.Windows.Forms.Button()
        Me.txtCallSign = New System.Windows.Forms.TextBox()
        Me.Label11 = New System.Windows.Forms.Label()
        Me.Label8 = New System.Windows.Forms.Label()
        Me.chkShowError = New System.Windows.Forms.CheckBox()
        Me.IncomingMessagesListError = New System.Windows.Forms.ListBox()
        Me.Label10 = New System.Windows.Forms.Label()
        Me.IncomingMessagesList = New System.Windows.Forms.ListBox()
        Me.ServerPort = New System.Windows.Forms.NumericUpDown()
        Me.Label9 = New System.Windows.Forms.Label()
        Me.LocalIPLabel = New System.Windows.Forms.Label()
        Me.StartStopButton = New System.Windows.Forms.Button()
        Me.btnMustDeclare = New System.Windows.Forms.Button()
        Me.pnlSag = New System.Windows.Forms.Panel()
        Me.grdKeys = New System.Windows.Forms.DataGridView()
        Me.ColID = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.ColButtons = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.ColModifiers = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.ColDescription = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.ColButtonsAciklama = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.CollModifiyelerAcik = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.txtMustDeclare = New System.Windows.Forms.RichTextBox()
        Me.gbTest = New System.Windows.Forms.GroupBox()
        Me.Panel3 = New System.Windows.Forms.Panel()
        Me.gbCallSign = New System.Windows.Forms.GroupBox()
        Me.grdCallSignData = New System.Windows.Forms.DataGridView()
        Me.ColKeyType = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.ColKeyName = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.ColKeyValue = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.ColSPTypeVal = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.ColSPTypeName = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Panel2 = New System.Windows.Forms.Panel()
        Me.cmbAirCraft = New System.Windows.Forms.ComboBox()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.Label17 = New System.Windows.Forms.Label()
        Me.btnCloseTest = New System.Windows.Forms.Button()
        Me.btnSendMSGCallSgn = New System.Windows.Forms.Button()
        Me.txtMsg = New System.Windows.Forms.TextBox()
        Me.Button1 = New System.Windows.Forms.Button()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.btnReadFlightData = New System.Windows.Forms.Button()
        Me.txtPortLMFD = New System.Windows.Forms.NumericUpDown()
        Me.txtPortMisSch = New System.Windows.Forms.NumericUpDown()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label6 = New System.Windows.Forms.Label()
        Me.btnReadSendMFD = New System.Windows.Forms.Button()
        Me.txtPortRWR = New System.Windows.Forms.NumericUpDown()
        Me.txtIp = New System.Windows.Forms.TextBox()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.btnSendTestIMG = New System.Windows.Forms.Button()
        Me.txtPortDED = New System.Windows.Forms.NumericUpDown()
        Me.picIMGAll = New System.Windows.Forms.PictureBox()
        Me.Label5 = New System.Windows.Forms.Label()
        Me.btnReadMem = New System.Windows.Forms.Button()
        Me.btnReadDED_RWR = New System.Windows.Forms.Button()
        Me.Label14 = New System.Windows.Forms.Label()
        Me.txtRefreshTime = New System.Windows.Forms.NumericUpDown()
        Me.txtPortRMFD = New System.Windows.Forms.NumericUpDown()
        Me.lblRefreshTime = New System.Windows.Forms.Label()
        Me.Label16 = New System.Windows.Forms.Label()
        Me.txtPortMsg = New System.Windows.Forms.NumericUpDown()
        Me.btnSendMSG = New System.Windows.Forms.Button()
        Me.pnlResim = New System.Windows.Forms.Panel()
        Me.Button4 = New System.Windows.Forms.Button()
        Me.PictureBox1 = New System.Windows.Forms.PictureBox()
        Me.folder = New System.Windows.Forms.FolderBrowserDialog()
        Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
        Me.Button3 = New System.Windows.Forms.Button()
        Me.Panel1.SuspendLayout()
        Me.pnlSol.SuspendLayout()
        Me.pnlMFD.SuspendLayout()
        CType(Me.picMFD, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.GroupBox2.SuspendLayout()
        CType(Me.grdIPList, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.ServerPort, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.pnlSag.SuspendLayout()
        CType(Me.grdKeys, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.gbTest.SuspendLayout()
        Me.Panel3.SuspendLayout()
        Me.gbCallSign.SuspendLayout()
        CType(Me.grdCallSignData, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.Panel2.SuspendLayout()
        CType(Me.txtPortLMFD, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.txtPortMisSch, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.txtPortRWR, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.txtPortDED, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.picIMGAll, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.txtRefreshTime, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.txtPortRMFD, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.txtPortMsg, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.pnlResim.SuspendLayout()
        CType(Me.PictureBox1, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'Panel1
        '
        Me.Panel1.Controls.Add(Me.btnFlightDataLoad)
        Me.Panel1.Controls.Add(Me.btnFlightDataNav)
        Me.Panel1.Controls.Add(Me.btnFlightDataNavMap)
        Me.Panel1.Controls.Add(Me.Label7)
        Me.Panel1.Controls.Add(Me.btnFlightData)
        Me.Panel1.Controls.Add(Me.btnRWR)
        Me.Panel1.Controls.Add(Me.btnICP)
        Me.Panel1.Controls.Add(Me.btnRightMFD)
        Me.Panel1.Controls.Add(Me.btnLeftMfd)
        Me.Panel1.Dock = System.Windows.Forms.DockStyle.Bottom
        Me.Panel1.Location = New System.Drawing.Point(0, 619)
        Me.Panel1.Name = "Panel1"
        Me.Panel1.Size = New System.Drawing.Size(1046, 94)
        Me.Panel1.TabIndex = 35
        '
        'btnFlightDataLoad
        '
        Me.btnFlightDataLoad.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnFlightDataLoad.Location = New System.Drawing.Point(255, 66)
        Me.btnFlightDataLoad.Name = "btnFlightDataLoad"
        Me.btnFlightDataLoad.Size = New System.Drawing.Size(115, 23)
        Me.btnFlightDataLoad.TabIndex = 62
        Me.btnFlightDataLoad.Text = "Load De Active"
        Me.btnFlightDataLoad.UseVisualStyleBackColor = True
        '
        'btnFlightDataNav
        '
        Me.btnFlightDataNav.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnFlightDataNav.Location = New System.Drawing.Point(133, 66)
        Me.btnFlightDataNav.Name = "btnFlightDataNav"
        Me.btnFlightDataNav.Size = New System.Drawing.Size(115, 23)
        Me.btnFlightDataNav.TabIndex = 61
        Me.btnFlightDataNav.Text = "Nav De Active"
        Me.btnFlightDataNav.UseVisualStyleBackColor = True
        '
        'btnFlightDataNavMap
        '
        Me.btnFlightDataNavMap.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnFlightDataNavMap.Location = New System.Drawing.Point(375, 66)
        Me.btnFlightDataNavMap.Name = "btnFlightDataNavMap"
        Me.btnFlightDataNavMap.Size = New System.Drawing.Size(115, 23)
        Me.btnFlightDataNavMap.TabIndex = 60
        Me.btnFlightDataNavMap.Text = "Nav Map De Active"
        Me.btnFlightDataNavMap.UseVisualStyleBackColor = True
        '
        'Label7
        '
        Me.Label7.AutoSize = True
        Me.Label7.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.Label7.ForeColor = System.Drawing.Color.Red
        Me.Label7.Location = New System.Drawing.Point(5, 12)
        Me.Label7.Name = "Label7"
        Me.Label7.Size = New System.Drawing.Size(337, 13)
        Me.Label7.TabIndex = 59
        Me.Label7.Text = "Don't Press Buttons. Just Use Tablet To Activate Reading"
        '
        'btnFlightData
        '
        Me.btnFlightData.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnFlightData.Location = New System.Drawing.Point(255, 39)
        Me.btnFlightData.Name = "btnFlightData"
        Me.btnFlightData.Size = New System.Drawing.Size(115, 23)
        Me.btnFlightData.TabIndex = 58
        Me.btnFlightData.Text = "Flgh.Dat. De Active"
        Me.btnFlightData.UseVisualStyleBackColor = True
        '
        'btnRWR
        '
        Me.btnRWR.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnRWR.Location = New System.Drawing.Point(375, 29)
        Me.btnRWR.Name = "btnRWR"
        Me.btnRWR.Size = New System.Drawing.Size(115, 23)
        Me.btnRWR.TabIndex = 57
        Me.btnRWR.Text = "RWR De Active"
        Me.btnRWR.UseVisualStyleBackColor = True
        '
        'btnICP
        '
        Me.btnICP.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnICP.Location = New System.Drawing.Point(133, 29)
        Me.btnICP.Name = "btnICP"
        Me.btnICP.Size = New System.Drawing.Size(115, 23)
        Me.btnICP.TabIndex = 56
        Me.btnICP.Text = "ICP De Active"
        Me.btnICP.UseVisualStyleBackColor = True
        '
        'btnRightMFD
        '
        Me.btnRightMFD.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnRightMFD.Location = New System.Drawing.Point(496, 29)
        Me.btnRightMFD.Name = "btnRightMFD"
        Me.btnRightMFD.Size = New System.Drawing.Size(115, 23)
        Me.btnRightMFD.TabIndex = 55
        Me.btnRightMFD.Text = "Rg. MFD De Active"
        Me.btnRightMFD.UseVisualStyleBackColor = True
        '
        'btnLeftMfd
        '
        Me.btnLeftMfd.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnLeftMfd.Location = New System.Drawing.Point(8, 28)
        Me.btnLeftMfd.Name = "btnLeftMfd"
        Me.btnLeftMfd.Size = New System.Drawing.Size(115, 23)
        Me.btnLeftMfd.TabIndex = 54
        Me.btnLeftMfd.Text = "Lf. MFD De Active"
        Me.btnLeftMfd.UseVisualStyleBackColor = True
        '
        'ofDB
        '
        Me.ofDB.Filter = "Key Files (*.key)|*.key"
        '
        'pnlSol
        '
        Me.pnlSol.Controls.Add(Me.txtKeyFile)
        Me.pnlSol.Controls.Add(Me.btnOpenClose)
        Me.pnlSol.Controls.Add(Me.btnFindKeyFile)
        Me.pnlSol.Controls.Add(Me.pnlMFD)
        Me.pnlSol.Controls.Add(Me.GroupBox2)
        Me.pnlSol.Dock = System.Windows.Forms.DockStyle.Left
        Me.pnlSol.Location = New System.Drawing.Point(0, 0)
        Me.pnlSol.Name = "pnlSol"
        Me.pnlSol.Size = New System.Drawing.Size(375, 619)
        Me.pnlSol.TabIndex = 36
        '
        'txtKeyFile
        '
        Me.txtKeyFile.Location = New System.Drawing.Point(322, 237)
        Me.txtKeyFile.Name = "txtKeyFile"
        Me.txtKeyFile.Size = New System.Drawing.Size(38, 26)
        Me.txtKeyFile.TabIndex = 38
        Me.txtKeyFile.Text = ""
        Me.txtKeyFile.Visible = False
        '
        'btnOpenClose
        '
        Me.btnOpenClose.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnOpenClose.Location = New System.Drawing.Point(238, 240)
        Me.btnOpenClose.Name = "btnOpenClose"
        Me.btnOpenClose.Size = New System.Drawing.Size(119, 23)
        Me.btnOpenClose.TabIndex = 61
        Me.btnOpenClose.Text = "Tesitng Part"
        Me.btnOpenClose.UseVisualStyleBackColor = True
        '
        'btnFindKeyFile
        '
        Me.btnFindKeyFile.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnFindKeyFile.Location = New System.Drawing.Point(15, 240)
        Me.btnFindKeyFile.Name = "btnFindKeyFile"
        Me.btnFindKeyFile.Size = New System.Drawing.Size(109, 22)
        Me.btnFindKeyFile.TabIndex = 37
        Me.btnFindKeyFile.Text = "Find Key File"
        '
        'pnlMFD
        '
        Me.pnlMFD.Controls.Add(Me.Button2)
        Me.pnlMFD.Controls.Add(Me.btnSagMFD)
        Me.pnlMFD.Controls.Add(Me.btnSolMFD)
        Me.pnlMFD.Controls.Add(Me.btn28)
        Me.pnlMFD.Controls.Add(Me.btn27)
        Me.pnlMFD.Controls.Add(Me.btn26)
        Me.pnlMFD.Controls.Add(Me.btn25)
        Me.pnlMFD.Controls.Add(Me.btn24)
        Me.pnlMFD.Controls.Add(Me.btn23)
        Me.pnlMFD.Controls.Add(Me.btn22)
        Me.pnlMFD.Controls.Add(Me.btn21)
        Me.pnlMFD.Controls.Add(Me.btn16)
        Me.pnlMFD.Controls.Add(Me.btn17)
        Me.pnlMFD.Controls.Add(Me.btn18)
        Me.pnlMFD.Controls.Add(Me.btn19)
        Me.pnlMFD.Controls.Add(Me.btn20)
        Me.pnlMFD.Controls.Add(Me.btn11)
        Me.pnlMFD.Controls.Add(Me.btn12)
        Me.pnlMFD.Controls.Add(Me.btn13)
        Me.pnlMFD.Controls.Add(Me.btn14)
        Me.pnlMFD.Controls.Add(Me.btn15)
        Me.pnlMFD.Controls.Add(Me.btn10)
        Me.pnlMFD.Controls.Add(Me.btn9)
        Me.pnlMFD.Controls.Add(Me.btn8)
        Me.pnlMFD.Controls.Add(Me.btn7)
        Me.pnlMFD.Controls.Add(Me.btn6)
        Me.pnlMFD.Controls.Add(Me.btn5)
        Me.pnlMFD.Controls.Add(Me.btn4)
        Me.pnlMFD.Controls.Add(Me.btn3)
        Me.pnlMFD.Controls.Add(Me.btn2)
        Me.pnlMFD.Controls.Add(Me.btn1)
        Me.pnlMFD.Controls.Add(Me.picMFD)
        Me.pnlMFD.Location = New System.Drawing.Point(3, 268)
        Me.pnlMFD.Name = "pnlMFD"
        Me.pnlMFD.Size = New System.Drawing.Size(368, 368)
        Me.pnlMFD.TabIndex = 36
        '
        'Button2
        '
        Me.Button2.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.Button2.Location = New System.Drawing.Point(153, 231)
        Me.Button2.Name = "Button2"
        Me.Button2.Size = New System.Drawing.Size(56, 49)
        Me.Button2.TabIndex = 56
        Me.Button2.UseVisualStyleBackColor = True
        Me.Button2.Visible = False
        '
        'btnSagMFD
        '
        Me.btnSagMFD.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnSagMFD.Location = New System.Drawing.Point(285, 9)
        Me.btnSagMFD.Name = "btnSagMFD"
        Me.btnSagMFD.Size = New System.Drawing.Size(75, 23)
        Me.btnSagMFD.TabIndex = 54
        Me.btnSagMFD.Text = "Right MFD"
        Me.btnSagMFD.UseVisualStyleBackColor = True
        '
        'btnSolMFD
        '
        Me.btnSolMFD.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnSolMFD.Enabled = False
        Me.btnSolMFD.Location = New System.Drawing.Point(9, 9)
        Me.btnSolMFD.Name = "btnSolMFD"
        Me.btnSolMFD.Size = New System.Drawing.Size(75, 23)
        Me.btnSolMFD.TabIndex = 53
        Me.btnSolMFD.Text = "Left MFD"
        Me.btnSolMFD.UseVisualStyleBackColor = True
        '
        'btn28
        '
        Me.btn28.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn28
        Me.btn28.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn28.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn28.Location = New System.Drawing.Point(3, 80)
        Me.btn28.Name = "btn28"
        Me.btn28.Size = New System.Drawing.Size(28, 29)
        Me.btn28.TabIndex = 52
        Me.btn28.UseVisualStyleBackColor = True
        '
        'btn27
        '
        Me.btn27.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn27
        Me.btn27.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn27.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn27.Location = New System.Drawing.Point(3, 48)
        Me.btn27.Name = "btn27"
        Me.btn27.Size = New System.Drawing.Size(28, 29)
        Me.btn27.TabIndex = 51
        Me.btn27.UseVisualStyleBackColor = True
        '
        'btn26
        '
        Me.btn26.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn26
        Me.btn26.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn26.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn26.Location = New System.Drawing.Point(2, 290)
        Me.btn26.Name = "btn26"
        Me.btn26.Size = New System.Drawing.Size(28, 29)
        Me.btn26.TabIndex = 50
        Me.btn26.UseVisualStyleBackColor = True
        '
        'btn25
        '
        Me.btn25.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn25
        Me.btn25.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn25.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn25.Location = New System.Drawing.Point(2, 258)
        Me.btn25.Name = "btn25"
        Me.btn25.Size = New System.Drawing.Size(28, 29)
        Me.btn25.TabIndex = 49
        Me.btn25.UseVisualStyleBackColor = True
        '
        'btn24
        '
        Me.btn24.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn24
        Me.btn24.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn24.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn24.Location = New System.Drawing.Point(337, 290)
        Me.btn24.Name = "btn24"
        Me.btn24.Size = New System.Drawing.Size(28, 29)
        Me.btn24.TabIndex = 48
        Me.btn24.UseVisualStyleBackColor = True
        '
        'btn23
        '
        Me.btn23.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn23
        Me.btn23.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn23.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn23.Location = New System.Drawing.Point(337, 258)
        Me.btn23.Name = "btn23"
        Me.btn23.Size = New System.Drawing.Size(28, 29)
        Me.btn23.TabIndex = 47
        Me.btn23.UseVisualStyleBackColor = True
        '
        'btn22
        '
        Me.btn22.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn22
        Me.btn22.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn22.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn22.Location = New System.Drawing.Point(337, 79)
        Me.btn22.Name = "btn22"
        Me.btn22.Size = New System.Drawing.Size(28, 29)
        Me.btn22.TabIndex = 46
        Me.btn22.UseVisualStyleBackColor = True
        '
        'btn21
        '
        Me.btn21.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn21
        Me.btn21.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn21.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn21.Location = New System.Drawing.Point(337, 48)
        Me.btn21.Name = "btn21"
        Me.btn21.Size = New System.Drawing.Size(28, 29)
        Me.btn21.TabIndex = 45
        Me.btn21.UseVisualStyleBackColor = True
        '
        'btn16
        '
        Me.btn16.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn16
        Me.btn16.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn16.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn16.Location = New System.Drawing.Point(5, 228)
        Me.btn16.Name = "btn16"
        Me.btn16.Size = New System.Drawing.Size(28, 29)
        Me.btn16.TabIndex = 40
        Me.btn16.UseVisualStyleBackColor = True
        '
        'btn17
        '
        Me.btn17.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn17
        Me.btn17.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn17.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn17.Location = New System.Drawing.Point(5, 198)
        Me.btn17.Name = "btn17"
        Me.btn17.Size = New System.Drawing.Size(28, 29)
        Me.btn17.TabIndex = 41
        Me.btn17.UseVisualStyleBackColor = True
        '
        'btn18
        '
        Me.btn18.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn18
        Me.btn18.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn18.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn18.Location = New System.Drawing.Point(5, 170)
        Me.btn18.Name = "btn18"
        Me.btn18.Size = New System.Drawing.Size(28, 29)
        Me.btn18.TabIndex = 42
        Me.btn18.UseVisualStyleBackColor = True
        '
        'btn19
        '
        Me.btn19.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn19
        Me.btn19.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn19.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn19.Location = New System.Drawing.Point(5, 140)
        Me.btn19.Name = "btn19"
        Me.btn19.Size = New System.Drawing.Size(28, 29)
        Me.btn19.TabIndex = 43
        Me.btn19.UseVisualStyleBackColor = True
        '
        'btn20
        '
        Me.btn20.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn20
        Me.btn20.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn20.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn20.Location = New System.Drawing.Point(5, 110)
        Me.btn20.Name = "btn20"
        Me.btn20.Size = New System.Drawing.Size(28, 29)
        Me.btn20.TabIndex = 44
        Me.btn20.UseVisualStyleBackColor = True
        '
        'btn11
        '
        Me.btn11.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn11
        Me.btn11.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn11.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn11.Location = New System.Drawing.Point(235, 333)
        Me.btn11.Name = "btn11"
        Me.btn11.Size = New System.Drawing.Size(28, 29)
        Me.btn11.TabIndex = 35
        Me.btn11.UseVisualStyleBackColor = True
        '
        'btn12
        '
        Me.btn12.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn12
        Me.btn12.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn12.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn12.Location = New System.Drawing.Point(202, 333)
        Me.btn12.Name = "btn12"
        Me.btn12.Size = New System.Drawing.Size(28, 29)
        Me.btn12.TabIndex = 36
        Me.btn12.UseVisualStyleBackColor = True
        '
        'btn13
        '
        Me.btn13.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn13
        Me.btn13.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn13.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn13.Location = New System.Drawing.Point(169, 333)
        Me.btn13.Name = "btn13"
        Me.btn13.Size = New System.Drawing.Size(28, 29)
        Me.btn13.TabIndex = 37
        Me.btn13.UseVisualStyleBackColor = True
        '
        'btn14
        '
        Me.btn14.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn14
        Me.btn14.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn14.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn14.Location = New System.Drawing.Point(136, 333)
        Me.btn14.Name = "btn14"
        Me.btn14.Size = New System.Drawing.Size(28, 29)
        Me.btn14.TabIndex = 38
        Me.btn14.UseVisualStyleBackColor = True
        '
        'btn15
        '
        Me.btn15.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn15
        Me.btn15.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn15.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn15.Location = New System.Drawing.Point(102, 333)
        Me.btn15.Name = "btn15"
        Me.btn15.Size = New System.Drawing.Size(28, 29)
        Me.btn15.TabIndex = 39
        Me.btn15.UseVisualStyleBackColor = True
        '
        'btn10
        '
        Me.btn10.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn10
        Me.btn10.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn10.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn10.Location = New System.Drawing.Point(334, 227)
        Me.btn10.Name = "btn10"
        Me.btn10.Size = New System.Drawing.Size(28, 29)
        Me.btn10.TabIndex = 34
        Me.btn10.UseVisualStyleBackColor = True
        '
        'btn9
        '
        Me.btn9.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn9
        Me.btn9.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn9.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn9.Location = New System.Drawing.Point(334, 197)
        Me.btn9.Name = "btn9"
        Me.btn9.Size = New System.Drawing.Size(28, 29)
        Me.btn9.TabIndex = 33
        Me.btn9.UseVisualStyleBackColor = True
        '
        'btn8
        '
        Me.btn8.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn8
        Me.btn8.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn8.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn8.Location = New System.Drawing.Point(334, 169)
        Me.btn8.Name = "btn8"
        Me.btn8.Size = New System.Drawing.Size(28, 29)
        Me.btn8.TabIndex = 32
        Me.btn8.UseVisualStyleBackColor = True
        '
        'btn7
        '
        Me.btn7.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn7
        Me.btn7.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn7.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn7.Location = New System.Drawing.Point(334, 139)
        Me.btn7.Name = "btn7"
        Me.btn7.Size = New System.Drawing.Size(28, 29)
        Me.btn7.TabIndex = 31
        Me.btn7.UseVisualStyleBackColor = True
        '
        'btn6
        '
        Me.btn6.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn6
        Me.btn6.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn6.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn6.Location = New System.Drawing.Point(334, 109)
        Me.btn6.Name = "btn6"
        Me.btn6.Size = New System.Drawing.Size(28, 29)
        Me.btn6.TabIndex = 30
        Me.btn6.UseVisualStyleBackColor = True
        '
        'btn5
        '
        Me.btn5.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn5
        Me.btn5.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn5.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn5.Location = New System.Drawing.Point(235, 6)
        Me.btn5.Name = "btn5"
        Me.btn5.Size = New System.Drawing.Size(28, 29)
        Me.btn5.TabIndex = 29
        Me.btn5.UseVisualStyleBackColor = True
        '
        'btn4
        '
        Me.btn4.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn4
        Me.btn4.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn4.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn4.Location = New System.Drawing.Point(202, 6)
        Me.btn4.Name = "btn4"
        Me.btn4.Size = New System.Drawing.Size(28, 29)
        Me.btn4.TabIndex = 28
        Me.btn4.UseVisualStyleBackColor = True
        '
        'btn3
        '
        Me.btn3.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn3
        Me.btn3.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn3.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn3.Location = New System.Drawing.Point(169, 6)
        Me.btn3.Name = "btn3"
        Me.btn3.Size = New System.Drawing.Size(28, 29)
        Me.btn3.TabIndex = 27
        Me.btn3.UseVisualStyleBackColor = True
        '
        'btn2
        '
        Me.btn2.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn2
        Me.btn2.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn2.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn2.Location = New System.Drawing.Point(136, 6)
        Me.btn2.Name = "btn2"
        Me.btn2.Size = New System.Drawing.Size(28, 29)
        Me.btn2.TabIndex = 26
        Me.btn2.UseVisualStyleBackColor = True
        '
        'btn1
        '
        Me.btn1.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources.btn1
        Me.btn1.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.btn1.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btn1.Location = New System.Drawing.Point(102, 6)
        Me.btn1.Name = "btn1"
        Me.btn1.Size = New System.Drawing.Size(28, 29)
        Me.btn1.TabIndex = 25
        Me.btn1.UseVisualStyleBackColor = True
        '
        'picMFD
        '
        Me.picMFD.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom
        Me.picMFD.Image = Global.FalconMFDServer.My.Resources.Resources.Untitled_1
        Me.picMFD.Location = New System.Drawing.Point(0, 0)
        Me.picMFD.Name = "picMFD"
        Me.picMFD.Size = New System.Drawing.Size(368, 368)
        Me.picMFD.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage
        Me.picMFD.TabIndex = 24
        Me.picMFD.TabStop = False
        '
        'GroupBox2
        '
        Me.GroupBox2.Controls.Add(Me.grdIPList)
        Me.GroupBox2.Controls.Add(Me.cmbBMSVerion)
        Me.GroupBox2.Controls.Add(Me.btnChangeCallSign)
        Me.GroupBox2.Controls.Add(Me.txtCallSign)
        Me.GroupBox2.Controls.Add(Me.Label11)
        Me.GroupBox2.Controls.Add(Me.Label8)
        Me.GroupBox2.Controls.Add(Me.chkShowError)
        Me.GroupBox2.Controls.Add(Me.IncomingMessagesListError)
        Me.GroupBox2.Controls.Add(Me.Label10)
        Me.GroupBox2.Controls.Add(Me.IncomingMessagesList)
        Me.GroupBox2.Controls.Add(Me.ServerPort)
        Me.GroupBox2.Controls.Add(Me.Label9)
        Me.GroupBox2.Controls.Add(Me.LocalIPLabel)
        Me.GroupBox2.Controls.Add(Me.StartStopButton)
        Me.GroupBox2.Location = New System.Drawing.Point(3, 3)
        Me.GroupBox2.Name = "GroupBox2"
        Me.GroupBox2.Size = New System.Drawing.Size(368, 233)
        Me.GroupBox2.TabIndex = 35
        Me.GroupBox2.TabStop = False
        Me.GroupBox2.Text = "Falcon Server Information"
        '
        'grdIPList
        '
        Me.grdIPList.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me.grdIPList.Columns.AddRange(New System.Windows.Forms.DataGridViewColumn() {Me.colIPList})
        Me.grdIPList.Location = New System.Drawing.Point(211, 9)
        Me.grdIPList.Name = "grdIPList"
        Me.grdIPList.ReadOnly = True
        Me.grdIPList.RowHeadersWidth = 5
        Me.grdIPList.Size = New System.Drawing.Size(156, 88)
        Me.grdIPList.TabIndex = 69
        '
        'colIPList
        '
        Me.colIPList.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.Fill
        DataGridViewCellStyle1.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.colIPList.DefaultCellStyle = DataGridViewCellStyle1
        Me.colIPList.HeaderText = "IP List"
        Me.colIPList.Name = "colIPList"
        Me.colIPList.ReadOnly = True
        '
        'cmbBMSVerion
        '
        Me.cmbBMSVerion.Cursor = System.Windows.Forms.Cursors.Hand
        Me.cmbBMSVerion.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmbBMSVerion.FormattingEnabled = True
        Me.cmbBMSVerion.Items.AddRange(New Object() {"BMS 4.33 U1", "BMS 4.33", "BMS 4.32"})
        Me.cmbBMSVerion.Location = New System.Drawing.Point(77, 65)
        Me.cmbBMSVerion.Name = "cmbBMSVerion"
        Me.cmbBMSVerion.Size = New System.Drawing.Size(128, 21)
        Me.cmbBMSVerion.TabIndex = 68
        '
        'btnChangeCallSign
        '
        Me.btnChangeCallSign.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnChangeCallSign.Location = New System.Drawing.Point(180, 40)
        Me.btnChangeCallSign.Name = "btnChangeCallSign"
        Me.btnChangeCallSign.Size = New System.Drawing.Size(25, 23)
        Me.btnChangeCallSign.TabIndex = 67
        Me.btnChangeCallSign.Text = "..."
        Me.ToolTip1.SetToolTip(Me.btnChangeCallSign, "Change Call Sign")
        Me.btnChangeCallSign.UseVisualStyleBackColor = True
        '
        'txtCallSign
        '
        Me.txtCallSign.Enabled = False
        Me.txtCallSign.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.txtCallSign.ForeColor = System.Drawing.Color.Blue
        Me.txtCallSign.Location = New System.Drawing.Point(77, 42)
        Me.txtCallSign.Name = "txtCallSign"
        Me.txtCallSign.Size = New System.Drawing.Size(97, 20)
        Me.txtCallSign.TabIndex = 66
        '
        'Label11
        '
        Me.Label11.AutoSize = True
        Me.Label11.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.Label11.Location = New System.Drawing.Point(192, 113)
        Me.Label11.Name = "Label11"
        Me.Label11.Size = New System.Drawing.Size(74, 13)
        Me.Label11.TabIndex = 65
        Me.Label11.Text = "Local IP   : "
        Me.Label11.Visible = False
        '
        'Label8
        '
        Me.Label8.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.Label8.Location = New System.Drawing.Point(9, 43)
        Me.Label8.Name = "Label8"
        Me.Label8.Size = New System.Drawing.Size(78, 16)
        Me.Label8.TabIndex = 64
        Me.Label8.Text = "Call Sign"
        Me.Label8.TextAlign = System.Drawing.ContentAlignment.MiddleLeft
        '
        'chkShowError
        '
        Me.chkShowError.AutoSize = True
        Me.chkShowError.Cursor = System.Windows.Forms.Cursors.Hand
        Me.chkShowError.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.chkShowError.Location = New System.Drawing.Point(236, 98)
        Me.chkShowError.Name = "chkShowError"
        Me.chkShowError.Size = New System.Drawing.Size(123, 17)
        Me.chkShowError.TabIndex = 63
        Me.chkShowError.Text = "Show Only Errors"
        Me.chkShowError.UseVisualStyleBackColor = True
        '
        'IncomingMessagesListError
        '
        Me.IncomingMessagesListError.Location = New System.Drawing.Point(7, 130)
        Me.IncomingMessagesListError.Name = "IncomingMessagesListError"
        Me.IncomingMessagesListError.Size = New System.Drawing.Size(352, 95)
        Me.IncomingMessagesListError.TabIndex = 62
        Me.IncomingMessagesListError.Visible = False
        '
        'Label10
        '
        Me.Label10.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.Label10.Location = New System.Drawing.Point(6, 111)
        Me.Label10.Name = "Label10"
        Me.Label10.Size = New System.Drawing.Size(187, 16)
        Me.Label10.TabIndex = 13
        Me.Label10.Text = "Incomming Msg. List"
        Me.Label10.TextAlign = System.Drawing.ContentAlignment.MiddleLeft
        '
        'IncomingMessagesList
        '
        Me.IncomingMessagesList.Location = New System.Drawing.Point(7, 132)
        Me.IncomingMessagesList.Name = "IncomingMessagesList"
        Me.IncomingMessagesList.Size = New System.Drawing.Size(352, 95)
        Me.IncomingMessagesList.TabIndex = 12
        '
        'ServerPort
        '
        Me.ServerPort.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.ServerPort.Location = New System.Drawing.Point(77, 19)
        Me.ServerPort.Maximum = New Decimal(New Integer() {65000, 0, 0, 0})
        Me.ServerPort.Minimum = New Decimal(New Integer() {1024, 0, 0, 0})
        Me.ServerPort.Name = "ServerPort"
        Me.ServerPort.Size = New System.Drawing.Size(65, 20)
        Me.ServerPort.TabIndex = 11
        Me.ServerPort.Value = New Decimal(New Integer() {21111, 0, 0, 0})
        '
        'Label9
        '
        Me.Label9.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.Label9.Location = New System.Drawing.Point(6, 22)
        Me.Label9.Name = "Label9"
        Me.Label9.Size = New System.Drawing.Size(64, 16)
        Me.Label9.TabIndex = 10
        Me.Label9.Text = "Server Port"
        '
        'LocalIPLabel
        '
        Me.LocalIPLabel.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.LocalIPLabel.Location = New System.Drawing.Point(250, 110)
        Me.LocalIPLabel.Name = "LocalIPLabel"
        Me.LocalIPLabel.Size = New System.Drawing.Size(104, 18)
        Me.LocalIPLabel.TabIndex = 9
        Me.LocalIPLabel.Text = "192.168.0.31"
        Me.LocalIPLabel.TextAlign = System.Drawing.ContentAlignment.MiddleRight
        Me.LocalIPLabel.Visible = False
        '
        'StartStopButton
        '
        Me.StartStopButton.Cursor = System.Windows.Forms.Cursors.Hand
        Me.StartStopButton.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.StartStopButton.Location = New System.Drawing.Point(148, 18)
        Me.StartStopButton.Name = "StartStopButton"
        Me.StartStopButton.Size = New System.Drawing.Size(57, 22)
        Me.StartStopButton.TabIndex = 8
        Me.StartStopButton.Text = "Start"
        '
        'btnMustDeclare
        '
        Me.btnMustDeclare.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnMustDeclare.Location = New System.Drawing.Point(484, 554)
        Me.btnMustDeclare.Name = "btnMustDeclare"
        Me.btnMustDeclare.Size = New System.Drawing.Size(109, 22)
        Me.btnMustDeclare.TabIndex = 39
        Me.btnMustDeclare.Text = "Must Declare List"
        Me.btnMustDeclare.Visible = False
        '
        'pnlSag
        '
        Me.pnlSag.Controls.Add(Me.grdKeys)
        Me.pnlSag.Controls.Add(Me.txtMustDeclare)
        Me.pnlSag.Controls.Add(Me.btnMustDeclare)
        Me.pnlSag.Dock = System.Windows.Forms.DockStyle.Fill
        Me.pnlSag.Location = New System.Drawing.Point(375, 0)
        Me.pnlSag.Name = "pnlSag"
        Me.pnlSag.Size = New System.Drawing.Size(671, 619)
        Me.pnlSag.TabIndex = 37
        '
        'grdKeys
        '
        Me.grdKeys.AllowUserToAddRows = False
        Me.grdKeys.AllowUserToDeleteRows = False
        Me.grdKeys.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me.grdKeys.Columns.AddRange(New System.Windows.Forms.DataGridViewColumn() {Me.ColID, Me.ColButtons, Me.ColModifiers, Me.ColDescription, Me.ColButtonsAciklama, Me.CollModifiyelerAcik})
        Me.grdKeys.Dock = System.Windows.Forms.DockStyle.Fill
        Me.grdKeys.Location = New System.Drawing.Point(0, 0)
        Me.grdKeys.Name = "grdKeys"
        Me.grdKeys.ReadOnly = True
        Me.grdKeys.RowHeadersWidth = 5
        Me.grdKeys.Size = New System.Drawing.Size(671, 619)
        Me.grdKeys.TabIndex = 27
        '
        'ColID
        '
        DataGridViewCellStyle2.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.ColID.DefaultCellStyle = DataGridViewCellStyle2
        Me.ColID.HeaderText = "Simulation Cod"
        Me.ColID.Name = "ColID"
        Me.ColID.ReadOnly = True
        Me.ColID.Width = 175
        '
        'ColButtons
        '
        Me.ColButtons.HeaderText = "Keys"
        Me.ColButtons.Name = "ColButtons"
        Me.ColButtons.ReadOnly = True
        '
        'ColModifiers
        '
        Me.ColModifiers.HeaderText = "Modifiers"
        Me.ColModifiers.Name = "ColModifiers"
        Me.ColModifiers.ReadOnly = True
        '
        'ColDescription
        '
        Me.ColDescription.HeaderText = "Explanations"
        Me.ColDescription.Name = "ColDescription"
        Me.ColDescription.ReadOnly = True
        Me.ColDescription.Width = 220
        '
        'ColButtonsAciklama
        '
        Me.ColButtonsAciklama.HeaderText = "Key Name"
        Me.ColButtonsAciklama.Name = "ColButtonsAciklama"
        Me.ColButtonsAciklama.ReadOnly = True
        Me.ColButtonsAciklama.Width = 85
        '
        'CollModifiyelerAcik
        '
        Me.CollModifiyelerAcik.HeaderText = "Mod. Keys"
        Me.CollModifiyelerAcik.Name = "CollModifiyelerAcik"
        Me.CollModifiyelerAcik.ReadOnly = True
        '
        'txtMustDeclare
        '
        Me.txtMustDeclare.Location = New System.Drawing.Point(399, 23)
        Me.txtMustDeclare.Name = "txtMustDeclare"
        Me.txtMustDeclare.Size = New System.Drawing.Size(269, 525)
        Me.txtMustDeclare.TabIndex = 40
        Me.txtMustDeclare.Text = resources.GetString("txtMustDeclare.Text")
        Me.txtMustDeclare.Visible = False
        '
        'gbTest
        '
        Me.gbTest.Controls.Add(Me.Panel3)
        Me.gbTest.Controls.Add(Me.Panel2)
        Me.gbTest.Dock = System.Windows.Forms.DockStyle.Fill
        Me.gbTest.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.gbTest.Location = New System.Drawing.Point(375, 0)
        Me.gbTest.Name = "gbTest"
        Me.gbTest.Size = New System.Drawing.Size(671, 619)
        Me.gbTest.TabIndex = 38
        Me.gbTest.TabStop = False
        Me.gbTest.Visible = False
        '
        'Panel3
        '
        Me.Panel3.Controls.Add(Me.gbCallSign)
        Me.Panel3.Dock = System.Windows.Forms.DockStyle.Fill
        Me.Panel3.Location = New System.Drawing.Point(399, 16)
        Me.Panel3.Name = "Panel3"
        Me.Panel3.Size = New System.Drawing.Size(269, 600)
        Me.Panel3.TabIndex = 88
        '
        'gbCallSign
        '
        Me.gbCallSign.Controls.Add(Me.grdCallSignData)
        Me.gbCallSign.Dock = System.Windows.Forms.DockStyle.Fill
        Me.gbCallSign.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.gbCallSign.Location = New System.Drawing.Point(0, 0)
        Me.gbCallSign.Name = "gbCallSign"
        Me.gbCallSign.Size = New System.Drawing.Size(269, 600)
        Me.gbCallSign.TabIndex = 39
        Me.gbCallSign.TabStop = False
        Me.gbCallSign.Text = "Call Sign Data"
        '
        'grdCallSignData
        '
        Me.grdCallSignData.AllowUserToAddRows = False
        Me.grdCallSignData.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me.grdCallSignData.Columns.AddRange(New System.Windows.Forms.DataGridViewColumn() {Me.ColKeyType, Me.ColKeyName, Me.ColKeyValue, Me.ColSPTypeVal, Me.ColSPTypeName})
        Me.grdCallSignData.Dock = System.Windows.Forms.DockStyle.Fill
        Me.grdCallSignData.Location = New System.Drawing.Point(3, 16)
        Me.grdCallSignData.Name = "grdCallSignData"
        Me.grdCallSignData.ReadOnly = True
        Me.grdCallSignData.RowHeadersWidth = 25
        Me.grdCallSignData.Size = New System.Drawing.Size(263, 581)
        Me.grdCallSignData.TabIndex = 0
        '
        'ColKeyType
        '
        Me.ColKeyType.HeaderText = "Key Type"
        Me.ColKeyType.Name = "ColKeyType"
        Me.ColKeyType.ReadOnly = True
        Me.ColKeyType.Width = 90
        '
        'ColKeyName
        '
        Me.ColKeyName.HeaderText = "Key Name"
        Me.ColKeyName.Name = "ColKeyName"
        Me.ColKeyName.ReadOnly = True
        Me.ColKeyName.Width = 90
        '
        'ColKeyValue
        '
        Me.ColKeyValue.HeaderText = "Key Value"
        Me.ColKeyValue.Name = "ColKeyValue"
        Me.ColKeyValue.ReadOnly = True
        Me.ColKeyValue.Width = 500
        '
        'ColSPTypeVal
        '
        Me.ColSPTypeVal.HeaderText = "SP Type Val"
        Me.ColSPTypeVal.Name = "ColSPTypeVal"
        Me.ColSPTypeVal.ReadOnly = True
        '
        'ColSPTypeName
        '
        Me.ColSPTypeName.HeaderText = "SP Type Name"
        Me.ColSPTypeName.Name = "ColSPTypeName"
        Me.ColSPTypeName.ReadOnly = True
        Me.ColSPTypeName.Width = 150
        '
        'Panel2
        '
        Me.Panel2.Controls.Add(Me.Button3)
        Me.Panel2.Controls.Add(Me.cmbAirCraft)
        Me.Panel2.Controls.Add(Me.Label3)
        Me.Panel2.Controls.Add(Me.Label17)
        Me.Panel2.Controls.Add(Me.btnCloseTest)
        Me.Panel2.Controls.Add(Me.btnSendMSGCallSgn)
        Me.Panel2.Controls.Add(Me.txtMsg)
        Me.Panel2.Controls.Add(Me.Button1)
        Me.Panel2.Controls.Add(Me.Label1)
        Me.Panel2.Controls.Add(Me.btnReadFlightData)
        Me.Panel2.Controls.Add(Me.txtPortLMFD)
        Me.Panel2.Controls.Add(Me.txtPortMisSch)
        Me.Panel2.Controls.Add(Me.Label2)
        Me.Panel2.Controls.Add(Me.Label6)
        Me.Panel2.Controls.Add(Me.btnReadSendMFD)
        Me.Panel2.Controls.Add(Me.txtPortRWR)
        Me.Panel2.Controls.Add(Me.txtIp)
        Me.Panel2.Controls.Add(Me.Label4)
        Me.Panel2.Controls.Add(Me.btnSendTestIMG)
        Me.Panel2.Controls.Add(Me.txtPortDED)
        Me.Panel2.Controls.Add(Me.picIMGAll)
        Me.Panel2.Controls.Add(Me.Label5)
        Me.Panel2.Controls.Add(Me.btnReadMem)
        Me.Panel2.Controls.Add(Me.btnReadDED_RWR)
        Me.Panel2.Controls.Add(Me.Label14)
        Me.Panel2.Controls.Add(Me.txtRefreshTime)
        Me.Panel2.Controls.Add(Me.txtPortRMFD)
        Me.Panel2.Controls.Add(Me.lblRefreshTime)
        Me.Panel2.Controls.Add(Me.Label16)
        Me.Panel2.Controls.Add(Me.txtPortMsg)
        Me.Panel2.Controls.Add(Me.btnSendMSG)
        Me.Panel2.Dock = System.Windows.Forms.DockStyle.Left
        Me.Panel2.Location = New System.Drawing.Point(3, 16)
        Me.Panel2.Name = "Panel2"
        Me.Panel2.Size = New System.Drawing.Size(396, 600)
        Me.Panel2.TabIndex = 87
        '
        'cmbAirCraft
        '
        Me.cmbAirCraft.Cursor = System.Windows.Forms.Cursors.Hand
        Me.cmbAirCraft.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmbAirCraft.FormattingEnabled = True
        Me.cmbAirCraft.Items.AddRange(New Object() {"F16", "F18", "F14", "Mirage", "Others"})
        Me.cmbAirCraft.Location = New System.Drawing.Point(293, 203)
        Me.cmbAirCraft.Name = "cmbAirCraft"
        Me.cmbAirCraft.Size = New System.Drawing.Size(86, 21)
        Me.cmbAirCraft.TabIndex = 87
        '
        'Label3
        '
        Me.Label3.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.Label3.Location = New System.Drawing.Point(18, 52)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(96, 17)
        Me.Label3.TabIndex = 15
        Me.Label3.Text = "Mesaj / Key"
        '
        'Label17
        '
        Me.Label17.AutoSize = True
        Me.Label17.Font = New System.Drawing.Font("Microsoft Sans Serif", 16.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.Label17.ForeColor = System.Drawing.Color.Red
        Me.Label17.Location = New System.Drawing.Point(113, 5)
        Me.Label17.Name = "Label17"
        Me.Label17.Size = New System.Drawing.Size(140, 26)
        Me.Label17.TabIndex = 73
        Me.Label17.Text = "Testing Part"
        '
        'btnCloseTest
        '
        Me.btnCloseTest.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources._exit
        Me.btnCloseTest.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch
        Me.btnCloseTest.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnCloseTest.Location = New System.Drawing.Point(8, 5)
        Me.btnCloseTest.Name = "btnCloseTest"
        Me.btnCloseTest.Size = New System.Drawing.Size(40, 40)
        Me.btnCloseTest.TabIndex = 72
        Me.btnCloseTest.UseVisualStyleBackColor = True
        '
        'btnSendMSGCallSgn
        '
        Me.btnSendMSGCallSgn.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnSendMSGCallSgn.Location = New System.Drawing.Point(8, 263)
        Me.btnSendMSGCallSgn.Name = "btnSendMSGCallSgn"
        Me.btnSendMSGCallSgn.Size = New System.Drawing.Size(107, 23)
        Me.btnSendMSGCallSgn.TabIndex = 86
        Me.btnSendMSGCallSgn.Text = "Send Pnt. To And."
        Me.btnSendMSGCallSgn.UseVisualStyleBackColor = True
        '
        'txtMsg
        '
        Me.txtMsg.Location = New System.Drawing.Point(152, 49)
        Me.txtMsg.Name = "txtMsg"
        Me.txtMsg.Size = New System.Drawing.Size(227, 20)
        Me.txtMsg.TabIndex = 17
        Me.txtMsg.Text = "FlightDataLoad@100@200@300@400@280@20@18@23@30@1@1"
        '
        'Button1
        '
        Me.Button1.Cursor = System.Windows.Forms.Cursors.Hand
        Me.Button1.Location = New System.Drawing.Point(131, 263)
        Me.Button1.Name = "Button1"
        Me.Button1.Size = New System.Drawing.Size(107, 23)
        Me.Button1.TabIndex = 85
        Me.Button1.Text = "Img. To Mis.Sch"
        Me.Button1.UseVisualStyleBackColor = True
        '
        'Label1
        '
        Me.Label1.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.Label1.Location = New System.Drawing.Point(18, 144)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(113, 16)
        Me.Label1.TabIndex = 56
        Me.Label1.Text = "LMFD Port"
        '
        'btnReadFlightData
        '
        Me.btnReadFlightData.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnReadFlightData.Location = New System.Drawing.Point(202, 534)
        Me.btnReadFlightData.Name = "btnReadFlightData"
        Me.btnReadFlightData.Size = New System.Drawing.Size(183, 23)
        Me.btnReadFlightData.TabIndex = 84
        Me.btnReadFlightData.Text = "Start Reading Flight Data"
        Me.btnReadFlightData.UseVisualStyleBackColor = True
        '
        'txtPortLMFD
        '
        Me.txtPortLMFD.Location = New System.Drawing.Point(131, 142)
        Me.txtPortLMFD.Maximum = New Decimal(New Integer() {65000, 0, 0, 0})
        Me.txtPortLMFD.Minimum = New Decimal(New Integer() {1024, 0, 0, 0})
        Me.txtPortLMFD.Name = "txtPortLMFD"
        Me.txtPortLMFD.Size = New System.Drawing.Size(56, 20)
        Me.txtPortLMFD.TabIndex = 57
        Me.txtPortLMFD.Value = New Decimal(New Integer() {21112, 0, 0, 0})
        '
        'txtPortMisSch
        '
        Me.txtPortMisSch.Location = New System.Drawing.Point(323, 111)
        Me.txtPortMisSch.Maximum = New Decimal(New Integer() {65000, 0, 0, 0})
        Me.txtPortMisSch.Minimum = New Decimal(New Integer() {1024, 0, 0, 0})
        Me.txtPortMisSch.Name = "txtPortMisSch"
        Me.txtPortMisSch.Size = New System.Drawing.Size(56, 20)
        Me.txtPortMisSch.TabIndex = 83
        Me.txtPortMisSch.Value = New Decimal(New Integer() {21115, 0, 0, 0})
        '
        'Label2
        '
        Me.Label2.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.Label2.Location = New System.Drawing.Point(18, 80)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(96, 16)
        Me.Label2.TabIndex = 58
        Me.Label2.Text = "Android IP"
        '
        'Label6
        '
        Me.Label6.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.Label6.Location = New System.Drawing.Point(199, 113)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(113, 16)
        Me.Label6.TabIndex = 82
        Me.Label6.Text = "MisSch Port"
        '
        'btnReadSendMFD
        '
        Me.btnReadSendMFD.Location = New System.Drawing.Point(254, 235)
        Me.btnReadSendMFD.Name = "btnReadSendMFD"
        Me.btnReadSendMFD.Size = New System.Drawing.Size(125, 23)
        Me.btnReadSendMFD.TabIndex = 26
        Me.btnReadSendMFD.Text = "Start Read MFDs"
        Me.btnReadSendMFD.UseVisualStyleBackColor = True
        '
        'txtPortRWR
        '
        Me.txtPortRWR.Location = New System.Drawing.Point(323, 169)
        Me.txtPortRWR.Maximum = New Decimal(New Integer() {65000, 0, 0, 0})
        Me.txtPortRWR.Minimum = New Decimal(New Integer() {1024, 0, 0, 0})
        Me.txtPortRWR.Name = "txtPortRWR"
        Me.txtPortRWR.Size = New System.Drawing.Size(56, 20)
        Me.txtPortRWR.TabIndex = 81
        Me.txtPortRWR.Value = New Decimal(New Integer() {21116, 0, 0, 0})
        '
        'txtIp
        '
        Me.txtIp.Location = New System.Drawing.Point(152, 77)
        Me.txtIp.Name = "txtIp"
        Me.txtIp.Size = New System.Drawing.Size(227, 20)
        Me.txtIp.TabIndex = 59
        Me.txtIp.Text = "192.168.1.101"
        '
        'Label4
        '
        Me.Label4.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.Label4.Location = New System.Drawing.Point(199, 171)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(93, 16)
        Me.Label4.TabIndex = 80
        Me.Label4.Text = "RWR Port"
        '
        'btnSendTestIMG
        '
        Me.btnSendTestIMG.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnSendTestIMG.Location = New System.Drawing.Point(131, 235)
        Me.btnSendTestIMG.Name = "btnSendTestIMG"
        Me.btnSendTestIMG.Size = New System.Drawing.Size(107, 23)
        Me.btnSendTestIMG.TabIndex = 60
        Me.btnSendTestIMG.Text = "Send Img. To And."
        Me.btnSendTestIMG.UseVisualStyleBackColor = True
        '
        'txtPortDED
        '
        Me.txtPortDED.Location = New System.Drawing.Point(323, 140)
        Me.txtPortDED.Maximum = New Decimal(New Integer() {65000, 0, 0, 0})
        Me.txtPortDED.Minimum = New Decimal(New Integer() {1024, 0, 0, 0})
        Me.txtPortDED.Name = "txtPortDED"
        Me.txtPortDED.Size = New System.Drawing.Size(56, 20)
        Me.txtPortDED.TabIndex = 79
        Me.txtPortDED.Value = New Decimal(New Integer() {21114, 0, 0, 0})
        '
        'picIMGAll
        '
        Me.picIMGAll.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch
        Me.picIMGAll.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D
        Me.picIMGAll.Location = New System.Drawing.Point(12, 288)
        Me.picIMGAll.Name = "picIMGAll"
        Me.picIMGAll.Size = New System.Drawing.Size(375, 240)
        Me.picIMGAll.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage
        Me.picIMGAll.TabIndex = 63
        Me.picIMGAll.TabStop = False
        '
        'Label5
        '
        Me.Label5.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.Label5.Location = New System.Drawing.Point(199, 142)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(93, 16)
        Me.Label5.TabIndex = 78
        Me.Label5.Text = "DED Port"
        '
        'btnReadMem
        '
        Me.btnReadMem.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnReadMem.Location = New System.Drawing.Point(15, 534)
        Me.btnReadMem.Name = "btnReadMem"
        Me.btnReadMem.Size = New System.Drawing.Size(183, 23)
        Me.btnReadMem.TabIndex = 64
        Me.btnReadMem.Text = "Start Reading Falcon MFD"
        Me.btnReadMem.UseVisualStyleBackColor = True
        '
        'btnReadDED_RWR
        '
        Me.btnReadDED_RWR.Location = New System.Drawing.Point(254, 263)
        Me.btnReadDED_RWR.Name = "btnReadDED_RWR"
        Me.btnReadDED_RWR.Size = New System.Drawing.Size(125, 23)
        Me.btnReadDED_RWR.TabIndex = 77
        Me.btnReadDED_RWR.Text = "Start Read DED-RWR"
        Me.btnReadDED_RWR.UseVisualStyleBackColor = True
        '
        'Label14
        '
        Me.Label14.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.Label14.Location = New System.Drawing.Point(18, 173)
        Me.Label14.Name = "Label14"
        Me.Label14.Size = New System.Drawing.Size(113, 16)
        Me.Label14.TabIndex = 65
        Me.Label14.Text = "RMFD Port"
        '
        'txtRefreshTime
        '
        Me.txtRefreshTime.Location = New System.Drawing.Point(131, 206)
        Me.txtRefreshTime.Maximum = New Decimal(New Integer() {1000, 0, 0, 0})
        Me.txtRefreshTime.Minimum = New Decimal(New Integer() {1, 0, 0, 0})
        Me.txtRefreshTime.Name = "txtRefreshTime"
        Me.txtRefreshTime.Size = New System.Drawing.Size(56, 20)
        Me.txtRefreshTime.TabIndex = 76
        Me.txtRefreshTime.Value = New Decimal(New Integer() {100, 0, 0, 0})
        '
        'txtPortRMFD
        '
        Me.txtPortRMFD.Location = New System.Drawing.Point(131, 171)
        Me.txtPortRMFD.Maximum = New Decimal(New Integer() {65000, 0, 0, 0})
        Me.txtPortRMFD.Minimum = New Decimal(New Integer() {1024, 0, 0, 0})
        Me.txtPortRMFD.Name = "txtPortRMFD"
        Me.txtPortRMFD.Size = New System.Drawing.Size(56, 20)
        Me.txtPortRMFD.TabIndex = 66
        Me.txtPortRMFD.Value = New Decimal(New Integer() {21113, 0, 0, 0})
        '
        'lblRefreshTime
        '
        Me.lblRefreshTime.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.lblRefreshTime.Location = New System.Drawing.Point(19, 208)
        Me.lblRefreshTime.Name = "lblRefreshTime"
        Me.lblRefreshTime.Size = New System.Drawing.Size(113, 16)
        Me.lblRefreshTime.TabIndex = 75
        Me.lblRefreshTime.Text = "Refresh Time"
        '
        'Label16
        '
        Me.Label16.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(162, Byte))
        Me.Label16.Location = New System.Drawing.Point(18, 115)
        Me.Label16.Name = "Label16"
        Me.Label16.Size = New System.Drawing.Size(113, 16)
        Me.Label16.TabIndex = 69
        Me.Label16.Text = "Msg Port"
        '
        'txtPortMsg
        '
        Me.txtPortMsg.Location = New System.Drawing.Point(131, 113)
        Me.txtPortMsg.Maximum = New Decimal(New Integer() {65000, 0, 0, 0})
        Me.txtPortMsg.Minimum = New Decimal(New Integer() {1024, 0, 0, 0})
        Me.txtPortMsg.Name = "txtPortMsg"
        Me.txtPortMsg.Size = New System.Drawing.Size(56, 20)
        Me.txtPortMsg.TabIndex = 70
        Me.txtPortMsg.Value = New Decimal(New Integer() {21111, 0, 0, 0})
        '
        'btnSendMSG
        '
        Me.btnSendMSG.Cursor = System.Windows.Forms.Cursors.Hand
        Me.btnSendMSG.Location = New System.Drawing.Point(8, 235)
        Me.btnSendMSG.Name = "btnSendMSG"
        Me.btnSendMSG.Size = New System.Drawing.Size(107, 23)
        Me.btnSendMSG.TabIndex = 71
        Me.btnSendMSG.Text = "Send Msg. To And."
        Me.btnSendMSG.UseVisualStyleBackColor = True
        '
        'pnlResim
        '
        Me.pnlResim.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D
        Me.pnlResim.Controls.Add(Me.Button4)
        Me.pnlResim.Controls.Add(Me.PictureBox1)
        Me.pnlResim.Location = New System.Drawing.Point(230, 2000)
        Me.pnlResim.Name = "pnlResim"
        Me.pnlResim.Size = New System.Drawing.Size(587, 524)
        Me.pnlResim.TabIndex = 38
        '
        'Button4
        '
        Me.Button4.BackgroundImage = Global.FalconMFDServer.My.Resources.Resources._exit
        Me.Button4.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch
        Me.Button4.Cursor = System.Windows.Forms.Cursors.Hand
        Me.Button4.Location = New System.Drawing.Point(540, 2)
        Me.Button4.Name = "Button4"
        Me.Button4.Size = New System.Drawing.Size(43, 43)
        Me.Button4.TabIndex = 1
        Me.Button4.UseVisualStyleBackColor = True
        '
        'PictureBox1
        '
        Me.PictureBox1.Dock = System.Windows.Forms.DockStyle.Fill
        Me.PictureBox1.Image = CType(resources.GetObject("PictureBox1.Image"), System.Drawing.Image)
        Me.PictureBox1.Location = New System.Drawing.Point(0, 0)
        Me.PictureBox1.Name = "PictureBox1"
        Me.PictureBox1.Size = New System.Drawing.Size(583, 520)
        Me.PictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage
        Me.PictureBox1.TabIndex = 0
        Me.PictureBox1.TabStop = False
        '
        'Button3
        '
        Me.Button3.Location = New System.Drawing.Point(152, 564)
        Me.Button3.Name = "Button3"
        Me.Button3.Size = New System.Drawing.Size(75, 23)
        Me.Button3.TabIndex = 88
        Me.Button3.Text = "Button3"
        Me.Button3.UseVisualStyleBackColor = True
        '
        'frmServer
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(1046, 713)
        Me.Controls.Add(Me.gbTest)
        Me.Controls.Add(Me.pnlResim)
        Me.Controls.Add(Me.pnlSag)
        Me.Controls.Add(Me.pnlSol)
        Me.Controls.Add(Me.Panel1)
        Me.Icon = CType(resources.GetObject("$this.Icon"), System.Drawing.Icon)
        Me.Name = "frmServer"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Falcon MFD Server"
        Me.WindowState = System.Windows.Forms.FormWindowState.Maximized
        Me.Panel1.ResumeLayout(False)
        Me.Panel1.PerformLayout()
        Me.pnlSol.ResumeLayout(False)
        Me.pnlMFD.ResumeLayout(False)
        CType(Me.picMFD, System.ComponentModel.ISupportInitialize).EndInit()
        Me.GroupBox2.ResumeLayout(False)
        Me.GroupBox2.PerformLayout()
        CType(Me.grdIPList, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.ServerPort, System.ComponentModel.ISupportInitialize).EndInit()
        Me.pnlSag.ResumeLayout(False)
        CType(Me.grdKeys, System.ComponentModel.ISupportInitialize).EndInit()
        Me.gbTest.ResumeLayout(False)
        Me.Panel3.ResumeLayout(False)
        Me.gbCallSign.ResumeLayout(False)
        CType(Me.grdCallSignData, System.ComponentModel.ISupportInitialize).EndInit()
        Me.Panel2.ResumeLayout(False)
        Me.Panel2.PerformLayout()
        CType(Me.txtPortLMFD, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.txtPortMisSch, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.txtPortRWR, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.txtPortDED, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.picIMGAll, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.txtRefreshTime, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.txtPortRMFD, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.txtPortMsg, System.ComponentModel.ISupportInitialize).EndInit()
        Me.pnlResim.ResumeLayout(False)
        CType(Me.PictureBox1, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents Panel1 As System.Windows.Forms.Panel
    Friend WithEvents btnFlightData As System.Windows.Forms.Button
    Friend WithEvents btnRWR As System.Windows.Forms.Button
    Friend WithEvents btnICP As System.Windows.Forms.Button
    Friend WithEvents btnRightMFD As System.Windows.Forms.Button
    Friend WithEvents btnLeftMfd As System.Windows.Forms.Button
    Friend WithEvents ofDB As System.Windows.Forms.OpenFileDialog
    Friend WithEvents Label7 As System.Windows.Forms.Label
    Friend WithEvents pnlSol As System.Windows.Forms.Panel
    Friend WithEvents txtKeyFile As System.Windows.Forms.RichTextBox
    Friend WithEvents btnFindKeyFile As System.Windows.Forms.Button
    Friend WithEvents pnlMFD As System.Windows.Forms.Panel
    Friend WithEvents Button2 As System.Windows.Forms.Button
    Friend WithEvents btnSagMFD As System.Windows.Forms.Button
    Friend WithEvents btnSolMFD As System.Windows.Forms.Button
    Friend WithEvents btn28 As System.Windows.Forms.Button
    Friend WithEvents btn27 As System.Windows.Forms.Button
    Friend WithEvents btn26 As System.Windows.Forms.Button
    Friend WithEvents btn25 As System.Windows.Forms.Button
    Friend WithEvents btn24 As System.Windows.Forms.Button
    Friend WithEvents btn23 As System.Windows.Forms.Button
    Friend WithEvents btn22 As System.Windows.Forms.Button
    Friend WithEvents btn21 As System.Windows.Forms.Button
    Friend WithEvents btn16 As System.Windows.Forms.Button
    Friend WithEvents btn17 As System.Windows.Forms.Button
    Friend WithEvents btn18 As System.Windows.Forms.Button
    Friend WithEvents btn19 As System.Windows.Forms.Button
    Friend WithEvents btn20 As System.Windows.Forms.Button
    Friend WithEvents btn11 As System.Windows.Forms.Button
    Friend WithEvents btn12 As System.Windows.Forms.Button
    Friend WithEvents btn13 As System.Windows.Forms.Button
    Friend WithEvents btn14 As System.Windows.Forms.Button
    Friend WithEvents btn15 As System.Windows.Forms.Button
    Friend WithEvents btn10 As System.Windows.Forms.Button
    Friend WithEvents btn9 As System.Windows.Forms.Button
    Friend WithEvents btn8 As System.Windows.Forms.Button
    Friend WithEvents btn7 As System.Windows.Forms.Button
    Friend WithEvents btn6 As System.Windows.Forms.Button
    Friend WithEvents btn5 As System.Windows.Forms.Button
    Friend WithEvents btn4 As System.Windows.Forms.Button
    Friend WithEvents btn3 As System.Windows.Forms.Button
    Friend WithEvents btn2 As System.Windows.Forms.Button
    Friend WithEvents btn1 As System.Windows.Forms.Button
    Friend WithEvents picMFD As System.Windows.Forms.PictureBox
    Friend WithEvents GroupBox2 As System.Windows.Forms.GroupBox
    Friend WithEvents Label10 As System.Windows.Forms.Label
    Friend WithEvents IncomingMessagesList As System.Windows.Forms.ListBox
    Friend WithEvents ServerPort As System.Windows.Forms.NumericUpDown
    Friend WithEvents Label9 As System.Windows.Forms.Label
    Friend WithEvents LocalIPLabel As System.Windows.Forms.Label
    Friend WithEvents StartStopButton As System.Windows.Forms.Button
    Friend WithEvents btnOpenClose As System.Windows.Forms.Button
    Friend WithEvents pnlSag As System.Windows.Forms.Panel
    Friend WithEvents grdKeys As System.Windows.Forms.DataGridView
    Friend WithEvents ColID As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents ColButtons As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents ColModifiers As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents ColDescription As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents ColButtonsAciklama As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents CollModifiyelerAcik As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents gbTest As System.Windows.Forms.GroupBox
    Friend WithEvents Label17 As System.Windows.Forms.Label
    Friend WithEvents btnCloseTest As System.Windows.Forms.Button
    Friend WithEvents btnSendMSG As System.Windows.Forms.Button
    Friend WithEvents txtPortMsg As System.Windows.Forms.NumericUpDown
    Friend WithEvents Label16 As System.Windows.Forms.Label
    Friend WithEvents txtPortRMFD As System.Windows.Forms.NumericUpDown
    Friend WithEvents Label14 As System.Windows.Forms.Label
    Friend WithEvents btnReadMem As System.Windows.Forms.Button
    Friend WithEvents picIMGAll As System.Windows.Forms.PictureBox
    Friend WithEvents btnSendTestIMG As System.Windows.Forms.Button
    Friend WithEvents txtIp As System.Windows.Forms.TextBox
    Friend WithEvents btnReadSendMFD As System.Windows.Forms.Button
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents txtPortLMFD As System.Windows.Forms.NumericUpDown
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents txtMsg As System.Windows.Forms.TextBox
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents txtRefreshTime As System.Windows.Forms.NumericUpDown
    Friend WithEvents lblRefreshTime As System.Windows.Forms.Label
    Friend WithEvents btnReadDED_RWR As System.Windows.Forms.Button
    Friend WithEvents txtPortRWR As System.Windows.Forms.NumericUpDown
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents txtPortDED As System.Windows.Forms.NumericUpDown
    Friend WithEvents Label5 As System.Windows.Forms.Label
    Friend WithEvents txtPortMisSch As System.Windows.Forms.NumericUpDown
    Friend WithEvents Label6 As System.Windows.Forms.Label
    Friend WithEvents btnMustDeclare As System.Windows.Forms.Button
    Friend WithEvents txtMustDeclare As System.Windows.Forms.RichTextBox
    Friend WithEvents pnlResim As System.Windows.Forms.Panel
    Friend WithEvents Button4 As System.Windows.Forms.Button
    Friend WithEvents PictureBox1 As System.Windows.Forms.PictureBox
    Friend WithEvents folder As System.Windows.Forms.FolderBrowserDialog
    Friend WithEvents btnFlightDataLoad As System.Windows.Forms.Button
    Friend WithEvents btnFlightDataNav As System.Windows.Forms.Button
    Friend WithEvents btnFlightDataNavMap As System.Windows.Forms.Button
    Friend WithEvents btnReadFlightData As System.Windows.Forms.Button
    Friend WithEvents IncomingMessagesListError As System.Windows.Forms.ListBox
    Friend WithEvents chkShowError As System.Windows.Forms.CheckBox
    Friend WithEvents Button1 As System.Windows.Forms.Button
    Friend WithEvents Label8 As System.Windows.Forms.Label
    Friend WithEvents txtCallSign As System.Windows.Forms.TextBox
    Friend WithEvents Label11 As System.Windows.Forms.Label
    Friend WithEvents gbCallSign As System.Windows.Forms.GroupBox
    Friend WithEvents grdCallSignData As System.Windows.Forms.DataGridView
    Friend WithEvents btnSendMSGCallSgn As System.Windows.Forms.Button
    Friend WithEvents btnChangeCallSign As System.Windows.Forms.Button
    Friend WithEvents ToolTip1 As System.Windows.Forms.ToolTip
    Friend WithEvents ColKeyType As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents ColKeyName As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents ColKeyValue As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents ColSPTypeVal As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents ColSPTypeName As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Panel3 As System.Windows.Forms.Panel
    Friend WithEvents Panel2 As System.Windows.Forms.Panel
    Friend WithEvents cmbBMSVerion As System.Windows.Forms.ComboBox
    Friend WithEvents cmbAirCraft As ComboBox
    Friend WithEvents grdIPList As DataGridView
    Friend WithEvents colIPList As DataGridViewTextBoxColumn
    Friend WithEvents Button3 As Button
End Class
