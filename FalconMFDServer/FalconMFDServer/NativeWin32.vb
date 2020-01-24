Imports System
Imports System.Collections.Generic
Imports System.Text
Imports System.Runtime.InteropServices
Public Class NativeWin32

    Public Const WM_SYSCOMMAND As Integer = &H112
    Public Const SC_CLOSE As Integer = &HF060

    ' class name 
    <DllImport("user32.dll")> _
    Public Shared Function FindWindow(ByVal lpClassName As String, ByVal lpWindowName As String) As Integer
        ' window name 
    End Function



    ' handle to destination window 
    ' message 
    ' first message parameter 
    <DllImport("user32.dll")> _
    Public Shared Function SendMessage(ByVal hWnd As Integer, ByVal Msg As UInteger, ByVal wParam As Integer, ByVal lParam As Integer) As Integer
        ' second message parameter 
    End Function



    <DllImport("user32.dll")> _
    Public Shared Function SetForegroundWindow(ByVal hWnd As Integer) As Integer
        ' handle to window
    End Function



    Private Const GWL_EXSTYLE As Integer = (-20)
    Private Const WS_EX_TOOLWINDOW As Integer = &H80
    Private Const WS_EX_APPWINDOW As Integer = &H40000

    Public Const GW_HWNDFIRST As Integer = 0
    Public Const GW_HWNDLAST As Integer = 1
    Public Const GW_HWNDNEXT As Integer = 2
    Public Const GW_HWNDPREV As Integer = 3
    Public Const GW_OWNER As Integer = 4
    Public Const GW_CHILD As Integer = 5

    Public Delegate Function EnumWindowsProcDelegate(ByVal hWnd As Integer, ByVal lParam As Integer) As Integer

    <DllImport("user32")> _
    Public Shared Function EnumWindows(ByVal lpEnumFunc As EnumWindowsProcDelegate, ByVal lParam As Integer) As Integer
    End Function



    <DllImport("User32.Dll")> _
    Public Shared Sub GetWindowText(ByVal h As Integer, ByVal s As StringBuilder, ByVal nMaxCount As Integer)
    End Sub



    <DllImport("user32", EntryPoint:="GetWindowLongA")> _
    Public Shared Function GetWindowLongPtr(ByVal hwnd As Integer, ByVal nIndex As Integer) As Integer
    End Function



    <DllImport("user32")> _
    Public Shared Function GetParent(ByVal hwnd As Integer) As Integer
    End Function



    <DllImport("user32")> _
    Public Shared Function GetWindow(ByVal hwnd As Integer, ByVal wCmd As Integer) As Integer
    End Function



    <DllImport("user32")> _
    Public Shared Function IsWindowVisible(ByVal hwnd As Integer) As Integer
    End Function



    <DllImport("user32")> _
    Public Shared Function GetDesktopWindow() As Integer
    End Function

End Class
