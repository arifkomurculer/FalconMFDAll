Imports System.Threading
Imports F4TexSharedMem
Imports System.IO
Imports System.Net
Imports System.Net.Sockets
Imports System.Text

Public Class clsReadMemoryThreadSolMFD
    Public rectSolMFD As Rectangle
    Private _reader As New Reader()
    Private vrIpPortInfo() As String

    'Sonradan Deneme için Eklendi Başı

    'This variable will hold the Thread Index that are passed from the main window, when we created it
    Private m_ThreadIndex As Integer

    'This will hold the ref to the main window,
    Private m_MainWindow As Form

    Private Delegate Sub NotifyMainWindow(ByVal ThreadIndex As Integer, ByVal vrError As String)
    'We need an object of this deletegate
    Private m_NotifyMainWindow As NotifyMainWindow

    Private m_Args(1) As Object


    'Sonradan Deneme için Eklendi Sonu

    Public Sub New(ByVal ThreadIndex As Integer, ByRef MainWindow As frmServer, prIpPortInfo() As String, prX As Integer, prY As Integer, prWidth As Integer, prHeight As Integer)
        rectSolMFD = New System.Drawing.Rectangle(prX, prY, prWidth, prHeight)

        'Sonradan Deneme için Eklendi Başı
        m_ThreadIndex = ThreadIndex
        m_MainWindow = MainWindow

        'We need to point our delegate to the Method, which we want to call from this thread
        m_NotifyMainWindow = AddressOf MainWindow.ReceiveTextThreadMessage
        'Sonradan Deneme için Eklendi Sonu

        'Yeni
        vrIpPortInfo = prIpPortInfo
    End Sub


    Public Sub StartThreadSolMFD()

        Dim vrImg As Bitmap

        'Sonradan Deneme için Eklendi Başı
        Try
            'Sonradan Deneme için Eklendi Sonu
            While True


                If _reader.IsDataAvailable Then

                    'MesSoleBox.Show("olmadı");

                    Application.DoEvents()

                    vrImg = _reader.GetImage(rectSolMFD)

                    'Yeni Versiyon (Resimi Stringe Dönüştürüp Gönderecek)
                    SendMessage(vrIpPortInfo(0), vrIpPortInfo(2), Convert.ToBase64String(imageToByteArray(vrImg)))


                    Thread.Sleep(vrIpPortInfo(3))

                Else ' Falcon Kapalıysa
                    Thread.Sleep(2000)

                End If


            End While
            'Sonradan Deneme için Eklendi Başı
        Catch ex As Exception
            ReDim m_Args(1)
            m_Args(0) = m_ThreadIndex
            m_Args(1) = ex.Message.ToString

            m_MainWindow.Invoke(m_NotifyMainWindow, m_Args)
        End Try
        'Sonradan Deneme için Eklendi Sonu

    End Sub

    'Public Sub SendMessage(ByVal Ip As String, ByVal Port As Integer, ByVal Message As String)
    '    'If Not checkip(Ip) Then
    '    '    'Throw New Exception("Ip is not in the right format")
    '    '    FrmServer.fnkStarStopRWR("Stop")
    '    '    Exit Sub
    '    'End If

    '    Try
    '        Dim client As New System.Net.Sockets.TcpClient
    '        Dim Buffer() As Byte = System.Text.Encoding.Default.GetBytes(Message.ToCharArray)
    '        client.Connect(Ip, Port)
    '        client.GetStream.Write(Buffer, 0, Buffer.Length)
    '        client.Close()
    '    Catch ex As Exception
    '        'MsgBox("Unable to connect to IP Adress")
    '        'FrmServer.fnkStarStopRWR("Stop")
    '        'Sonradan Deneme için Eklendi Başı

    '        ReDim m_Args(1)
    '        m_Args(0) = m_ThreadIndex
    '        m_Args(1) = ex.Message.ToString

    '        m_MainWindow.Invoke(m_NotifyMainWindow, m_Args)

    '        'Sonradan Deneme için Eklendi Sonu
    '    End Try
    'End Sub

    Public Sub SendMessage(ByVal Ip As String, ByVal Port As Integer, ByVal Message As String)

        If Message.Length > 150 Then
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
        Else
            Try
                'Send UDP
                Dim GLOIP As IPAddress
                Dim GLOINTPORT As Integer
                Dim bytCommand As Byte() = New Byte() {}
                Dim udpClient As New UdpClient
                GLOIP = IPAddress.Parse(Ip)
                GLOINTPORT = Port
                udpClient.Connect(GLOIP, GLOINTPORT)
                bytCommand = Encoding.ASCII.GetBytes(Message)
                udpClient.Send(bytCommand, bytCommand.Length)
            Catch ex As Exception

            End Try

        End If




        'MsgBox(bytCommand.Length)


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
