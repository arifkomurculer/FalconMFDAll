Imports Tiny.UDP
Imports System.Net
Imports System.Net.Sockets

Public Class TestServer
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
    Friend WithEvents txtDestIP As System.Windows.Forms.TextBox
    Friend WithEvents btnSend As System.Windows.Forms.Button
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents txtDestPort As System.Windows.Forms.TextBox
    Friend WithEvents txtMessage As System.Windows.Forms.TextBox
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.txtDestIP = New System.Windows.Forms.TextBox
        Me.btnSend = New System.Windows.Forms.Button
        Me.Label1 = New System.Windows.Forms.Label
        Me.Label2 = New System.Windows.Forms.Label
        Me.txtDestPort = New System.Windows.Forms.TextBox
        Me.txtMessage = New System.Windows.Forms.TextBox
        Me.SuspendLayout()
        '
        'txtDestIP
        '
        Me.txtDestIP.Location = New System.Drawing.Point(96, 16)
        Me.txtDestIP.Name = "txtDestIP"
        Me.txtDestIP.Size = New System.Drawing.Size(88, 20)
        Me.txtDestIP.TabIndex = 0
        Me.txtDestIP.Text = "127.0.0.1"
        '
        'btnSend
        '
        Me.btnSend.Location = New System.Drawing.Point(168, 192)
        Me.btnSend.Name = "btnSend"
        Me.btnSend.Size = New System.Drawing.Size(64, 24)
        Me.btnSend.TabIndex = 1
        Me.btnSend.Text = "Send"
        '
        'Label1
        '
        Me.Label1.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft
        Me.Label1.Location = New System.Drawing.Point(8, 16)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(88, 20)
        Me.Label1.TabIndex = 2
        Me.Label1.Text = "Destination IP:"
        Me.Label1.TextAlign = System.Drawing.ContentAlignment.MiddleLeft
        '
        'Label2
        '
        Me.Label2.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft
        Me.Label2.Location = New System.Drawing.Point(8, 40)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(88, 20)
        Me.Label2.TabIndex = 4
        Me.Label2.Text = "Destination Port:"
        Me.Label2.TextAlign = System.Drawing.ContentAlignment.MiddleLeft
        '
        'txtDestPort
        '
        Me.txtDestPort.Location = New System.Drawing.Point(96, 40)
        Me.txtDestPort.Name = "txtDestPort"
        Me.txtDestPort.Size = New System.Drawing.Size(48, 20)
        Me.txtDestPort.TabIndex = 3
        Me.txtDestPort.Text = "8088"
        '
        'txtMessage
        '
        Me.txtMessage.Font = New System.Drawing.Font("Courier New", 8.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.txtMessage.Location = New System.Drawing.Point(8, 72)
        Me.txtMessage.Multiline = True
        Me.txtMessage.Name = "txtMessage"
        Me.txtMessage.Size = New System.Drawing.Size(224, 112)
        Me.txtMessage.TabIndex = 5
        Me.txtMessage.Text = ""
        '
        'TestServer
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(240, 222)
        Me.Controls.Add(Me.txtMessage)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.txtDestPort)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.btnSend)
        Me.Controls.Add(Me.txtDestIP)
        Me.Location = New System.Drawing.Point(300, 100)
        Me.Name = "TestServer"
        Me.Text = "Test Server"
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub btnSend_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnSend.Click
        ' Initialize a TinyServer Object
        Dim server As New TinyServer
        ' Set it for UDP and assign the destination end point
        server.Protocol = ProtocolType.Udp
        server.ClientAddress = IPAddress.Parse(txtDestIP.Text)
        server.ClientPort = CType(txtDestPort.Text, Integer)
        ' Set the Encoding Type and Send the Message
        server.Encode = EncodingType.ASCII
        server.SendMessage(txtMessage.Text)
    End Sub

End Class
