Imports Tiny.UDP
Imports System.Net
Imports System.Net.Sockets

Public Class TestClient
    Inherits System.Windows.Forms.Form

#Region " Windows Form Designer generated code "

    Public Sub New()
        MyBase.New()

        'This call is required by the Windows Form Designer.
        InitializeComponent()

        'Add any initialization after the InitializeComponent() call

    End Sub

    'Form overrides dispose to clean up the component list.
    Protected Overloads Overrides Sub Dispose(ByVal disposing As Boolean)
        If disposing Then
            If Not (components Is Nothing) Then
                components.Dispose()
            End If
        End If
        MyBase.Dispose(disposing)
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    Friend WithEvents btnStop As System.Windows.Forms.Button
    Friend WithEvents btnStart As System.Windows.Forms.Button
    Friend WithEvents txtMessage As System.Windows.Forms.TextBox
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.btnStop = New System.Windows.Forms.Button
        Me.btnStart = New System.Windows.Forms.Button
        Me.txtMessage = New System.Windows.Forms.TextBox
        Me.SuspendLayout()
        '
        'btnStop
        '
        Me.btnStop.Enabled = False
        Me.btnStop.Location = New System.Drawing.Point(192, 8)
        Me.btnStop.Name = "btnStop"
        Me.btnStop.Size = New System.Drawing.Size(88, 40)
        Me.btnStop.TabIndex = 0
        Me.btnStop.Text = "Stop"
        '
        'btnStart
        '
        Me.btnStart.Location = New System.Drawing.Point(96, 8)
        Me.btnStart.Name = "btnStart"
        Me.btnStart.Size = New System.Drawing.Size(88, 40)
        Me.btnStart.TabIndex = 1
        Me.btnStart.Text = "Start"
        '
        'txtMessage
        '
        Me.txtMessage.Location = New System.Drawing.Point(8, 56)
        Me.txtMessage.Multiline = True
        Me.txtMessage.Name = "txtMessage"
        Me.txtMessage.Size = New System.Drawing.Size(272, 200)
        Me.txtMessage.TabIndex = 2
        Me.txtMessage.Text = ""
        '
        'TestClient
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(288, 266)
        Me.Controls.Add(Me.txtMessage)
        Me.Controls.Add(Me.btnStart)
        Me.Controls.Add(Me.btnStop)
        Me.Location = New System.Drawing.Point(50, 100)
        Me.Name = "TestClient"
        Me.Text = "TestClient"
        Me.ResumeLayout(False)

    End Sub

#End Region

    ' Defint a TinyClient object
    Dim WithEvents client As TinyClient

    Private Sub TestClient_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        ' Create a client object
        client = New TinyClient
        ' Set the inbound client port
        client.ClientPort = 8088
        ' Set the encoding type and protocol
        client.Encode = EncodingType.ASCII
        client.Protocol = ProtocolType.Udp
    End Sub

    Private Sub btnStart_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnStart.Click
        ' Start the client
        client.Start()
        btnStop.Enabled = True
        btnStart.Enabled = False
    End Sub

    Private Sub btnStop_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnStop.Click
        ' Stop the client
        client.Stop()
        btnStop.Enabled = False
        btnStart.Enabled = True
    End Sub

    Private Delegate Sub _DisplayMessage()

    Private Sub DisplayMessage()
        txtMessage.Text += client.Message & " " & client.Server.Address.ToString & ControlChars.CrLf
    End Sub

    Private Sub client_AfterReceive(ByVal sender As Object, ByVal e As System.EventArgs) Handles client.AfterReceive
        ' This event might have been thrown from the worker thread
        ' Check InvokeRequired before we update the UI!!!
        If Me.InvokeRequired Then
            Me.Invoke(New _DisplayMessage(AddressOf DisplayMessage))
        Else
            DisplayMessage()
        End If
    End Sub
End Class
