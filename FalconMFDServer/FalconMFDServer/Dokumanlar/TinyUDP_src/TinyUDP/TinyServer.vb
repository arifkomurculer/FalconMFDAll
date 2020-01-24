Imports System.Net.Sockets
Imports System.Net
Imports System.Text
Imports System.ComponentModel

Namespace Tiny.UDP
    Public Class TinyServer
        Inherits System.ComponentModel.Component

#Region " Component Designer generated code "

        Public Sub New(ByVal Container As System.ComponentModel.IContainer)
            MyClass.New()

            'Required for Windows.Forms Class Composition Designer support
            Container.Add(Me)
        End Sub

        Public Sub New()
            MyBase.New()

            'This call is required by the Component Designer.
            InitializeComponent()

            'Add any initialization after the InitializeComponent() call

        End Sub

        'Component overrides dispose to clean up the component list.
        Protected Overloads Overrides Sub Dispose(ByVal disposing As Boolean)
            If disposing Then
                If Not (components Is Nothing) Then
                    components.Dispose()
                End If
            End If
            MyBase.Dispose(disposing)
        End Sub

        'Required by the Component Designer
        Private components As System.ComponentModel.IContainer

        'NOTE: The following procedure is required by the Component Designer
        'It can be modified using the Component Designer.
        'Do not modify it using the code editor.
        <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
            components = New System.ComponentModel.Container
        End Sub

#End Region

        ' Default property values
        Private _Protocol As ProtocolType = ProtocolType.Udp
        Private _ClientAddress As IPAddress = IPAddress.Any
        Private _ClientPort As Integer = 0
        Private _data As Byte() = New Byte() {}
        Private _Encode As EncodingType

        <Browsable(True), Category("TinyServer"), _
        Description("The server protocol to use. Currently only UDP is supported.")> _
        Public Property Protocol() As ProtocolType
            Get
                Return (_Protocol)
            End Get
            Set(ByVal Value As ProtocolType)
                If (Value = ProtocolType.Udp) Then
                    _Protocol = Value
                Else
                    Throw New ProtocolNotSupportedException
                End If
            End Set
        End Property

        <Browsable(True), Category("TinyServer"), _
        Description("The client IPEndPoint.")> _
        Public Property Client() As IPEndPoint
            Get
                Return New IPEndPoint(_ClientAddress, _ClientPort)
            End Get
            Set(ByVal Value As IPEndPoint)
                _ClientAddress = Value.Address
                _ClientPort = Value.Port
            End Set
        End Property

        <Browsable(True), Category("TinyServer"), _
        Description("The client IP Address.")> _
        Public Property ClientAddress() As IPAddress
            Get
                Return _ClientAddress
            End Get
            Set(ByVal Value As IPAddress)
                _ClientAddress = Value
            End Set
        End Property

        <Browsable(True), Category("TinyServer"), _
        Description("The client port number.")> _
        Public Property ClientPort() As Integer
            Get
                Return _ClientPort
            End Get
            Set(ByVal Value As Integer)
                _ClientPort = Value
            End Set
        End Property

        <Browsable(True), Category("TinyServer"), _
        Description("Text encoding to use for sent messages.")> _
        Public Property Encode() As EncodingType
            Get
                Return (_Encode)
            End Get
            Set(ByVal Value As EncodingType)
                _Encode = Value
            End Set
        End Property

        Public Sub SendMessage(ByVal message As String)
            ' Encode message per settings
            Select Case Encode
                Case EncodingType.Default
                    _data = Encoding.Default.GetBytes(message)
                Case EncodingType.ASCII
                    _data = Encoding.ASCII.GetBytes(message)
                Case EncodingType.Unicode
                    _data = Encoding.Unicode.GetBytes(message)
                Case EncodingType.UTF7
                    _data = Encoding.UTF7.GetBytes(message)
                Case EncodingType.UTF8
                    _data = Encoding.UTF8.GetBytes(message)
                Case Else
                    Throw New BadEncodingException
            End Select
            ' Send the message
            Try
                Select Case Protocol
                    Case ProtocolType.Udp
                        SendUDPMessage(_data)
                End Select
            Catch ex As Exception
                Throw ex
            End Try
        End Sub

        Private Function SendUDPMessage(ByVal _data As Byte())
            ' Create a UDP Server and send the message, then clean up
            Dim _UDPServer As UdpClient
            Dim ReturnCode As Integer
            Try
                _UDPServer = New UdpClient
                ReturnCode = 0
                _UDPServer.Connect(Client)
                ReturnCode = _UDPServer.Send(_data, _data.Length)
            Catch ex As Exception
                Throw ex
            Finally
                If Not (_UDPServer Is Nothing) Then
                    _UDPServer.Close()
                End If
            End Try
            Return ReturnCode
        End Function

    End Class

End Namespace