Imports System.Threading
Imports F4TexSharedMem

Public Class clsReadMemoryThread


    'Public rectHud As Rectangle = New System.Drawing.Rectangle(0, 0, 565, 565)
    'Public rectDED As Rectangle = New System.Drawing.Rectangle(575, 140, 380, 160)
    'Public rectRWR As Rectangle = New System.Drawing.Rectangle(960, 0, 240, 240)
    'Public rectSagMFD As Rectangle = New System.Drawing.Rectangle(748, 295, 452, 452)
    'Public rectSolMFD As Rectangle = New System.Drawing.Rectangle(748, 747, 452, 452)

    'Public rectAll As Rectangle = New System.Drawing.Rectangle(0, 1, 0, 1)


    'Public vrSolWait As Integer = 1000
    'Public vrSagWait As Integer = 1000

    Private ReadOnly _reader As New Reader()
    Private vrAirCraft As String

    'This variable will hold the Thread Index that are passed from the main window, when we created it
    Private m_ThreadIndex As Integer

    'This will hold the ref to the main window,
    Private m_MainWindow As Form

    Private vrRect As Rectangle

    Private Delegate Sub NotifyMainWindow(ByVal ThreadIndex As Integer, ByVal Counter As Bitmap)
    'We need an object of this deletegate
    Private m_NotifyMainWindow As NotifyMainWindow

    Private m_Args(1) As Object


    Public Sub New(ByVal ThreadIndex As Integer, ByRef MainWindow As frmServer, prAirCraft As String)
        m_ThreadIndex = ThreadIndex
        m_MainWindow = MainWindow

        'We need to point our delegate to the Method, which we want to call from this thread
        m_NotifyMainWindow = AddressOf MainWindow.ReceiveThreadMessage
        vrAirCraft = prAirCraft

    End Sub

    Public Sub StartThreadFull()

        Dim vrImg As Bitmap

        While True
            If _reader.IsDataAvailable Then

                'MessageBox.Show("olmadı");

                Application.DoEvents()

                vrImg = _reader.FullImage

                fnkSaveAsImage(vrImg, vrAirCraft)

                ReDim m_Args(1)
                m_Args(0) = m_ThreadIndex
                m_Args(1) = vrImg

                'Call the notificaiton method on the main window by the delegate
                m_MainWindow.Invoke(m_NotifyMainWindow, m_Args)
                Thread.Sleep(1000)

            Else ' Falcon Kapalıysa
                Thread.Sleep(2000)

            End If


        End While


    End Sub

    Protected Overrides Sub Finalize()
        _reader.Dispose()
        MyBase.Finalize()
    End Sub

    Public Sub fnkSaveAsImage(prImg As Bitmap, prName As String)
        Dim file_name As String = Application.StartupPath()
        file_name = file_name & "\" & prName & "."

        ' Get a Bitmap.
        Dim bm As Bitmap = prImg

        ' Save the picture as a bitmap, JPEG, and GIF.

        If My.Computer.FileSystem.FileExists(file_name & "bmp") = False Then
            bm.Save(file_name & "bmp",
System.Drawing.Imaging.ImageFormat.Bmp)
        End If

        If My.Computer.FileSystem.FileExists(file_name & "jpg") = False Then
            bm.Save(file_name & "jpg",
System.Drawing.Imaging.ImageFormat.Jpeg)
        End If

        If My.Computer.FileSystem.FileExists(file_name & "gif") = False Then
            bm.Save(file_name & "gif",
System.Drawing.Imaging.ImageFormat.Gif)
        End If


    End Sub


End Class
