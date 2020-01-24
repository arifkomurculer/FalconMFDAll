Imports System.Net.Sockets
Imports System.Net
Imports System.Text
Imports System.Threading
Imports System.ComponentModel

Namespace Tiny.UDP
    Public Class TinyClient
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

        ' Default Property values
        Private _Protocol As ProtocolType = ProtocolType.Udp
        Private _ThreadReceive As Thread
        Private _ClientPort As Integer = 0
        Private _Message As String = ""
        Private _Encode As EncodingType = EncodingType.Default
        Private _UDPClient As UdpClient
        Private _Server As New IPEndPoint(IPAddress.Any, 0)
        Private _BytesReceived As Integer = 0

        <Browsable(True), Category("TinyClient"), _
        Description("This event is fired right before an inbound message is received.")> _
        Public Event BeforeReceive As EventHandler

        <Browsable(True), Category("TinyClient"), _
        Description("This event is fired immediatley after an inbound message is received.")> _
        Public Event AfterReceive As EventHandler

        <Browsable(True), Category("TinyClient"), _
        Description("The encoding to use with received messages.")> _
        Public Property Encode() As EncodingType
            Get
                Return (_Encode)
            End Get
            Set(ByVal Value As EncodingType)
                _Encode = Value
            End Set
        End Property

        <Browsable(True), Category("TinyClient"), _
        Description("The number of bytes received by the client in the most recent message.")> _
        Public ReadOnly Property BytesReceived() As Integer
            Get
                Return (_BytesReceived)
            End Get
        End Property

        <Browsable(True), Category("TinyClient"), _
        Description("Read Only.  The message received by the client.")> _
        Public ReadOnly Property Message() As String
            Get
                Return (_Message)
            End Get
        End Property

        <Browsable(True), Category("TinyClient"), _
        Description("The server IPEndPoint.")> _
        Public Property Server() As IPEndPoint
            Get
                Return _Server
            End Get
            Set(ByVal Value As IPEndPoint)
                _Server = Value
            End Set
        End Property

        <Browsable(True), Category("TinyClient"), _
        Description("The client port to check for inbound data.")> _
        Public Property ClientPort() As Integer
            Get
                Return (_ClientPort)
            End Get
            Set(ByVal Value As Integer)
                _ClientPort = Value
            End Set
        End Property

        <Browsable(True), Category("TinyClient"), _
        Description("The client protocol to use. Currently only UDP is supported.")> _
        Public Property Protocol() As ProtocolType
            Get
                Return (_Protocol)
            End Get
            Set(ByVal Value As ProtocolType)
                _Protocol = Value
            End Set
        End Property

        Public Sub Receive()
            ' Clear the Message property
            _Message = ""
            ' Raise the BeforeReceive event
            RaiseEvent BeforeReceive(Me, New EventArgs)
            ' Receive our UDP data
            Dim _data As Byte()
            Try
                Select Case Protocol
                    Case ProtocolType.Udp
                        _data = _UDPClient.Receive(_Server)
                        _BytesReceived = _data.Length
                    Case Else
                        Throw New ProtocolNotSupportedException
                End Select
            Catch ex As Exception
                Throw ex
            Finally
                ' The thread finished blocking, and ended, so we start again
                InitializeThread()
            End Try
            ' Encode the data per the Encode property
            Dim _strdata As String
            Select Case Encode
                Case EncodingType.Default
                    _strdata = System.Text.Encoding.Default.GetString(_data)
                Case EncodingType.ASCII
                    _strdata = System.Text.Encoding.ASCII.GetString(_data)
                Case EncodingType.Unicode
                    _strdata = System.Text.Encoding.Unicode.GetString(_data)
                Case EncodingType.UTF7
                    _strdata = System.Text.Encoding.UTF7.GetString(_data)
                Case EncodingType.UTF8
                    _strdata = System.Text.Encoding.UTF8.GetString(_data)
                Case Else
                    Throw New BadEncodingException
            End Select
            ' Set the message
            _Message = _strdata
            ' Raise the AfterReceive event
            RaiseEvent AfterReceive(Me, New EventArgs)
        End Sub

        Private Sub InitializeClient()
            ' Configure a UDPClient
            Select Case Protocol
                Case ProtocolType.Udp
                    If (_UDPClient Is Nothing) Then
                        _UDPClient = New UdpClient(ClientPort)
                    End If
                Case Else
                    Throw New ProtocolNotSupportedException
            End Select
        End Sub

        Private Sub InitializeThread()
            ' Start a worker thread
            Try
                _ThreadReceive = New Thread(AddressOf Receive)
                _ThreadReceive.Start()
            Catch ex As Exception
                Throw ex
            End Try
        End Sub

        Public Sub Start()
            ' Initialize the Client and the Thread
            InitializeClient()
            InitializeThread()
        End Sub

        Public Sub [Stop]()
            ' Close the UDPClient and stop the worker thread
            Try
                ' Suspend the thread and then abort it.  Keeps it from
                ' continuing to try to process anything further while
                ' it winds down.
                _ThreadReceive.Suspend()
                _ThreadReceive.Abort()
                If Not (_UDPClient Is Nothing) Then
                    ' Close the UDPClient and then force it to Nothing
                    _UDPClient.Close()
                    _UDPClient = Nothing
                End If
            Catch ex As Exception
                Throw ex
            End Try
        End Sub

    End Class
End Namespace