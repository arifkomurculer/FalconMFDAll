Imports System
Imports System.Windows.Forms
Imports System.Runtime.InteropServices
Imports System.Threading
Imports System.Threading.Thread

Imports System.Collections.Generic
Imports System.Text


Public Class frmKlavye

    <DllImport("user32.dll")> _
    Private Shared Sub keybd_event(ByVal bVk As Byte, ByVal bScan As Byte, ByVal dwFlags As UInteger, ByVal dwExtraInfo As Integer)
    End Sub


    Private Sub btn1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)


        'fnkTusGonder(&



    End Sub


    Private Sub GetTaskWindows()
        ' Get the desktopwindow handle
        Dim nDeshWndHandle As Integer = NativeWin32.GetDesktopWindow()
        ' Get the first child window
        Dim nChildHandle As Integer = NativeWin32.GetWindow(nDeshWndHandle, NativeWin32.GW_CHILD)

        While nChildHandle <> 0
            'If the child window is this (SendKeys) application then ignore it.
            If nChildHandle = Me.Handle.ToInt32() Then
                nChildHandle = NativeWin32.GetWindow(nChildHandle, NativeWin32.GW_HWNDNEXT)
            End If

            ' Get only visible windows
            If NativeWin32.IsWindowVisible(nChildHandle) <> 0 Then
                Dim sbTitle As New StringBuilder(1024)
                ' Read the Title bar text on the windows to put in combobox
                NativeWin32.GetWindowText(nChildHandle, sbTitle, sbTitle.Capacity)
                Dim sWinTitle As [String] = sbTitle.ToString()
                If True Then
                    If sWinTitle.Length > 0 Then
                        cboWindows.Items.Add(sWinTitle)
                    End If
                End If
            End If
            ' Look for the next child.
            nChildHandle = NativeWin32.GetWindow(nChildHandle, NativeWin32.GW_HWNDNEXT)
        End While
    End Sub



    Public Sub fnkTusGonder(ByVal prScanCodes As UShort)
        'Sleep(60000)

        KeyAndMouseUtils.SendKeyInputToFalcon(prScanCodes, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(prScanCodes, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)


    End Sub



    Public Sub fnkTusGonder_Shift(ByVal prScanCodes As UShort)
        'Sleep(5000)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LShift, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(prScanCodes, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(prScanCodes, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LShift, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)


    End Sub

    Public Sub fnkTusGonder_Ctrl(ByVal prScanCodes As UShort)
        'Sleep(5000)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LControl, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(prScanCodes, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(prScanCodes, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LControl, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)


    End Sub

    Public Sub fnkTusGonder_Shift_Ctrl(ByVal prScanCodes As UShort)
        'Sleep(5000)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LShift, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LControl, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(prScanCodes, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(prScanCodes, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LControl, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LShift, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)


    End Sub

    Public Sub fnkTusGonder_Alt(ByVal prScanCodes As UShort)
        'Sleep(5000)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LMenu, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(prScanCodes, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(prScanCodes, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LMenu, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)


    End Sub

    Public Sub fnkTusGonder_Shift_Alt(ByVal prScanCodes As UShort)
        'Sleep(5000)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LShift, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LMenu, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(prScanCodes, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(prScanCodes, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LMenu, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LShift, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)


    End Sub

    Public Sub fnkTusGonder_Ctrl_Alt(ByVal prScanCodes As UShort)
        'Sleep(5000)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LControl, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LMenu, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(prScanCodes, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(prScanCodes, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LMenu, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LControl, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)


    End Sub


    Public Sub fnkTusGonder_Shift_Ctrl_Alt(ByVal prScanCodes As UShort)
        'Sleep(5000)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LShift, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LControl, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LMenu, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(prScanCodes, True, False)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(prScanCodes, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LMenu, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LControl, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)

        KeyAndMouseUtils.SendKeyInputToFalcon(ScanCodes.LShift, False, True)
        System.Threading.Thread.Sleep(vrThreadUyu)


    End Sub



    Private Sub frmKlavye_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        GetTaskWindows()
    End Sub

    Private Sub btnRefresh_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnRefresh.Click
        cboWindows.Items.Clear()
        GetTaskWindows()
    End Sub



    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button4.Click
        btnBasilan1.Visible = False
        btnBasilan2.Visible = False
        btnBasilan3.Visible = False
        btnBasilan4.Visible = False
    End Sub




    Public Sub fnkBasilanTus(ByVal prBtn As Button)

        Sleep(50)


        If prBtn.Text = "" Then 'Image Button

            If btnBasilan1.Visible = False Then
                btnBasilan1.Visible = True
                btnBasilan1.Image = prBtn.Image
            ElseIf btnBasilan2.Visible = False Then
                btnBasilan2.Visible = True
                btnBasilan2.Image = prBtn.Image
            ElseIf btnBasilan3.Visible = False Then
                btnBasilan3.Visible = True
                btnBasilan3.Image = prBtn.Image
            ElseIf btnBasilan4.Visible = False Then
                btnBasilan4.Visible = True
                btnBasilan4.Image = prBtn.Image
            End If

        Else


            If btnBasilan1.Visible = False Then
                btnBasilan1.Visible = True
                btnBasilan1.Text = prBtn.Text
            ElseIf btnBasilan2.Visible = False Then
                btnBasilan2.Visible = True
                btnBasilan2.Text = prBtn.Text
            ElseIf btnBasilan3.Visible = False Then
                btnBasilan3.Visible = True
                btnBasilan3.Text = prBtn.Text
            ElseIf btnBasilan4.Visible = False Then
                btnBasilan4.Visible = True
                btnBasilan4.Text = prBtn.Text
            End If


        End If




    End Sub





    Private Sub btnb_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles txtbilgi.Click, btnz.Click, btnyukari.Click, btny.Click, btnx.Click, btnw.Click, btnvirgul.Click, btnv.Click, btnu.Click, btntab.Click, btnt.Click, btnspace.Click, btnsolwindows.Click, btnsolshift.Click, btnsolctrl.Click, btnsolalt.Click, btnsol.Click, btnslk.Click, btnsagwindows.Click, btnsagtus.Click, btnsagshift.Click, btnsagctrl.Click, btnsagaltgr.Click, btnsag.Click, btns.Click, btnr.Click, btnq.Click, btnpup.Click, btnpsc.Click, btnpdn.Click, btnp.Click, btno.Click, btnnumlocknokta.Click, btnnumlockenter.Click, btnnumlockeksi.Click, btnnumlockcarpi.Click, btnnumlockbolu.Click, btnnumlockarti.Click, btnnumlock9.Click, btnnumlock8.Click, btnnumlock7.Click, btnnumlock6.Click, btnnumlock5.Click, btnnumlock4.Click, btnnumlock3.Click, btnnumlock2.Click, btnnumlock1.Click, btnnumlock0.Click, btnnumlock.Click, btnnokta.Click, btnn.Click, btnm.Click, btnlock.Click, btnl.Click, btnk.Click, btnj.Click, btnins.Click, btni.Click, btnhm.Click, btnh.Click, btng.Click, btnf9.Click, btnf8.Click, btnf7.Click, btnf6.Click, btnf5.Click, btnf4.Click, btnf3.Click, btnf2.Click, btnf12.Click, btnf11.Click, btnf10.Click, btnf1.Click, btnf.Click, btnesc.Click, btnenter.Click, btnend.Click, btneksi.Click, btne.Click, btndel.Click, btnd.Click, btncifttirnak.Click, btncarpi.Click, btnc.Click, btnbrk.Click, btnbackspace.Click, btnb.Click, btnasagi.Click, btna.Click, btn9.Click, btn8.Click, btn7.Click, btn6.Click, btn5.Click, btn4.Click, btn3.Click, btn2.Click, btn1.Click, btn0.Click, btn_u.Click, btn_s.Click, btn_o.Click, btn_i.Click, btn_g.Click, btn_c.Click


        fnkBasilanTus(sender)



    End Sub
End Class


Public Enum ScanCodes As Integer
    NotAssigned = -1
    Escape = &H1
    One = &H2
    Two = &H3
    Three = &H4
    Four = &H5
    Five = &H6
    Six = &H7
    Seven = &H8
    Eight = &H9
    Nine = &HA
    Zero = &HB
    Minus = &HC
    Equals = &HD
    Backspace = &HE
    Tab = &HF
    Q = &H10
    W = &H11
    E = &H12
    R = &H13
    T = &H14
    Y = &H15
    U = &H16
    I = &H17
    O = &H18
    P = &H19
    LBracket = &H1A
    RBracket = &H1B
    [Return] = &H1C
    LControl = &H1D
    A = &H1E
    S = &H1F
    D = &H20
    F = &H21
    G = &H22
    H = &H23
    J = &H24
    K = &H25
    L = &H26
    Semicolon = &H27
    Apostrophe = &H28
    Grave = &H29
    LShift = &H2A
    Backslash = &H2B
    Z = &H2C
    X = &H2D
    C = &H2E
    V = &H2F
    B = &H30
    N = &H31
    M = &H32
    Comma = &H33
    Period = &H34
    Slash = &H35
    RShift = &H36
    Multiply = &H37
    LMenu = &H38
    Space = &H39
    CapsLock = &H3A
    F1 = &H3B
    F2 = &H3C
    F3 = &H3D
    F4 = &H3E
    F5 = &H3F
    F6 = &H40
    F7 = &H41
    F8 = &H42
    F9 = &H43
    F10 = &H44
    NumLock = &H45
    ScrollLock = &H46
    NumPad7 = &H47
    NumPad8 = &H48
    NumPad9 = &H49
    Subtract = &H4A
    NumPad4 = &H4B
    NumPad5 = &H4C
    NumPad6 = &H4D
    Add = &H4E
    NumPad1 = &H4F
    NumPad2 = &H50
    NumPad3 = &H51
    NumPad0 = &H52
    [Decimal] = &H53
    F11 = &H57
    F12 = &H58
    F13 = &H64
    F14 = &H65
    F15 = &H66
    Kana = &H70
    Convert = &H79
    NoConvert = &H7B
    Yen = &H7D
    NumPadEquals = &H8D
    Circumflex = &H90
    At = &H91
    Colon = &H92
    Underline = &H93
    Kanji = &H94
    [Stop] = &H95
    Ax = &H96
    Unlabeled = &H97
    NumPadEnter = &H9C
    RControl = &H9D
    NumPadComma = &HB3
    Divide = &HB5
    SysRq = &HB7
    RMenu = &HB8
    Home = &HC7
    Up = &HC8
    Prior = &HC9
    Left = &HCB
    Right = &HCD
    [End] = &HCF
    Down = &HD0
    [Next] = &HD1
    Insert = &HD2
    Delete = &HD3
    LWin = &HDB
    RWin = &HDC
    Apps = &HDD
End Enum

Public Class KeyAndMouseUtils
    Friend Enum INPUT_TYPE As UInteger
        INPUT_MOUSE = 0
        INPUT_KEYBOARD = 1
        INPUT_HARDWARE = 2
    End Enum

    <Flags()> _
    Friend Enum KEYEVENTF As UInteger
        EXTENDEDKEY = &H1
        KEYUP = &H2
        UNICODE = &H4
        SCANCODE = &H8
    End Enum
    Friend Enum MAPVK_MAPTYPES As UInteger
        MAPVK_VK_TO_VSC = &H0
        MAPVK_VSC_TO_VK = &H1
        MAPVK_VK_TO_CHAR = &H2
        MAPVK_VSC_TO_VK_EX = &H3
        MAPVK_VK_TO_VSC_EX = &H4
    End Enum
    <Flags()> _
    Friend Enum MOUSEEVENTF As UInteger
        MOVE = &H1
        ' mouse move 
        LEFTDOWN = &H2
        ' left button down
        LEFTUP = &H4
        ' left button up
        RIGHTDOWN = &H8
        ' right button down
        RIGHTUP = &H10
        ' right button up
        MIDDLEDOWN = &H20
        ' middle button down
        MIDDLEUP = &H40
        ' middle button up
        XDOWN = &H80
        ' x button down 
        XUP = &H100
        ' x button down
        WHEEL = &H800
        ' wheel button rolled
        VIRTUALDESK = &H4000
        ' map to entire virtual desktop
        ABSOLUTE = &H8000
        ' absolute move
    End Enum

    <StructLayout(LayoutKind.Sequential)> _
    Friend Structure MOUSEINPUT
        Public dx As Integer
        Public dy As Integer
        Public mouseData As UInteger
        Public dwFlags As MOUSEEVENTF
        Public time As UInteger
        Public dwExtraInfo As UIntPtr
    End Structure
    <StructLayout(LayoutKind.Sequential)> _
    Friend Structure HARDWAREINPUT
        Public uMsg As UInteger
        Public wParamL As UShort
        Public wParamH As UShort
    End Structure
    <StructLayout(LayoutKind.Sequential)> _
    Friend Structure KEYBDINPUT
        Public wVk As UShort
        Public wScan As UShort
        Public dwFlags As KEYEVENTF
        Public time As UInteger
        Public dwExtraInfo As IntPtr
    End Structure




    '32 bit için
    <StructLayout(LayoutKind.Explicit)> _
    Friend Structure INPUT
        <FieldOffset(0)> _
        Public type As INPUT_TYPE
        <FieldOffset(vr32_64)> _
        Public mi As MOUSEINPUT
        <FieldOffset(vr32_64)> _
        Public ki As KEYBDINPUT
        <FieldOffset(vr32_64)> _
        Public hi As HARDWAREINPUT
    End Structure


    '64 bit için
    '<StructLayout(LayoutKind.Explicit)> _
    'Friend Structure INPUT
    '    <FieldOffset(0)> _
    '    Public type As INPUT_TYPE
    '    <FieldOffset(4)> _
    '    Public mi As MOUSEINPUT
    '    <FieldOffset(4)> _
    '    Public ki As KEYBDINPUT
    '    <FieldOffset(4)> _
    '    Public hi As HARDWAREINPUT
    'End Structure

    <DllImport("user32.dll", CallingConvention:=CallingConvention.StdCall, CharSet:=CharSet.Unicode, EntryPoint:="MapVirtualKey", SetLastError:=False)> _
    Friend Shared Function MapVirtualKey(ByVal uCode As UInteger, ByVal uMapType As MAPVK_MAPTYPES) As UInteger
    End Function



    <DllImport("user32.dll")> _
    Friend Shared Function GetMessageExtraInfo() As IntPtr
    End Function



    <DllImport("user32.dll", SetLastError:=True)> _
    Friend Shared Function SendInput(ByVal nInputs As UInteger, ByVal pInputs As INPUT(), ByVal cbSize As Integer) As UInteger
    End Function





    <DllImportAttribute("User32.dll", SetLastError:=True)> _
    Friend Shared Function FindWindow(ByVal ClassName As [String], ByVal WindowName As [String]) As IntPtr
    End Function



    <DllImportAttribute("User32.dll", SetLastError:=True)> _
    Friend Shared Function SetForegroundWindow(ByVal hWnd As IntPtr) As Boolean
    End Function



    Friend Shared Sub SendMouseInput(ByVal dwFlags As MOUSEEVENTF, ByVal dx As UInteger, ByVal dy As UInteger, ByVal dwData As UInteger, ByVal dwExtraInfo As UIntPtr)
        Dim input As New INPUT()
        input.mi = New MOUSEINPUT()
        input.type = INPUT_TYPE.INPUT_MOUSE
        input.mi.dwFlags = dwFlags
        input.mi.dx = CType(dx, Integer)
        input.mi.dy = CType(dy, Integer)
        input.mi.mouseData = dwData
        input.mi.time = 0
        input.mi.dwExtraInfo = dwExtraInfo
        SendInput(1, New INPUT() {input}, Marshal.SizeOf(GetType(INPUT)))
    End Sub



    Friend Shared Sub SendKeyInput(ByVal scanCode As UShort, ByVal press As Boolean, ByVal release As Boolean)
        If Not press AndAlso Not release Then
            Return
        End If

        Dim numInputs As Integer = 0
        If press AndAlso release Then
            numInputs = 2
        Else
            numInputs = 1
        End If
        Dim inputs As INPUT() = New INPUT(numInputs) {}
        Dim curInput As Integer = 0


        If press Then
            Dim input As New INPUT()
            input.ki = New KEYBDINPUT()
            input.ki.wScan = scanCode
            input.ki.time = 0
            input.ki.dwFlags = KEYEVENTF.SCANCODE

            'MsgBox(scanCode)

            If (scanCode And &H80) > 0 Then
                input.ki.dwFlags = input.ki.dwFlags Or KEYEVENTF.EXTENDEDKEY
            End If
            System.Diagnostics.Debug.WriteLine(input.ki.wScan)
            input.ki.dwExtraInfo = GetMessageExtraInfo()
            input.type = INPUT_TYPE.INPUT_KEYBOARD
            inputs(curInput) = input
            System.Math.Max(System.Threading.Interlocked.Increment(curInput), curInput - 1)
        End If


        If release Then
            Dim input As New INPUT()
            input.ki = New KEYBDINPUT()
            input.ki.wScan = scanCode
            input.ki.time = 0
            input.ki.dwFlags = (KEYEVENTF.KEYUP Or KEYEVENTF.SCANCODE)

            'MsgBox(scanCode)

            If (scanCode And &H80) > 0 Then
                input.ki.dwFlags = input.ki.dwFlags Or KEYEVENTF.EXTENDEDKEY
            End If
            input.ki.dwExtraInfo = GetMessageExtraInfo()
            input.type = INPUT_TYPE.INPUT_KEYBOARD
            inputs(curInput) = input
        End If

        SendInput(CType(numInputs, UInteger), inputs, Marshal.SizeOf(GetType(INPUT)))

    End Sub



    Public Shared Sub SendKeyInputToFalcon(ByVal scanCode As UShort, ByVal press As Boolean, ByVal release As Boolean)

        Dim falconWindow As IntPtr = FindWindow(frmKlavye.cboWindows.Text, Nothing)
        SetForegroundWindow(falconWindow)

        'If FindWindow("FalconDisplay", Nothing) Then
        '    'MsgBox("ok")
        '    'FrmServer.IncomingMessagesList.Items.Add("Ok")

        '    SendKeyInput(scanCode, press, release)

        'Else
        '    'MsgBox("No")
        '    'FrmServer.IncomingMessagesList.Items.Add("No")
        'End If



        SendKeyInput(scanCode, press, release)




    End Sub



    ''' <summary>
    '''Moves the mouse to the given relative (x,y) coordinates.
    ''' </summary>
    Public Shared Sub MouseMoveRelative(ByVal x As Integer, ByVal y As Integer)
        Dim cur_x As Integer = Cursor.Position.X
        Dim cur_y As Integer = Cursor.Position.Y

        Dim new_x As Integer = cur_x + x
        Dim new_y As Integer = cur_y + y
        MouseMoveAbsolute(new_x, new_y)
    End Sub



    ''' <summary>
    '''Moves the mouse to the given absolute (x,y) coordinates.
    ''' </summary>
    Public Shared Sub MouseMoveAbsolute(ByVal x As Integer, ByVal y As Integer)
        x = x * 65535 / Screen.PrimaryScreen.Bounds.Width
        y = y * 65535 / Screen.PrimaryScreen.Bounds.Height
        SendMouseInput((MOUSEEVENTF.ABSOLUTE Or MOUSEEVENTF.MOVE), CType(x, UInteger), CType(y, UInteger), CType(0, UInteger), UIntPtr.Zero)
    End Sub


    ''' <summary>
    '''Moves the mouse wheel by given amount.
    ''' </summary>
    Public Shared Sub MouseWheelMove(ByVal amount As Integer)
        SendMouseInput(MOUSEEVENTF.WHEEL, CType(0, UInteger), CType(0, UInteger), CType(amount, UInteger), UIntPtr.Zero)
    End Sub




    ''' <summary>
    '''Sends a left mouse button up event at the current cursor position.
    ''' </summary>
    Public Shared Sub LeftUp()
        SendMouseInput(MOUSEEVENTF.LEFTUP, 0, 0, 0, UIntPtr.Zero)
    End Sub



    ''' <summary>
    '''Sends a right mouse button up event at the current cursor position.
    ''' </summary>
    Public Shared Sub RightUp()
        SendMouseInput(MOUSEEVENTF.RIGHTUP, 0, 0, 0, UIntPtr.Zero)
    End Sub




    ''' <summary>
    '''Sends a middle mouse button up event at the current cursor position.
    ''' </summary>
    Public Shared Sub MiddleUp()
        SendMouseInput(MOUSEEVENTF.MIDDLEUP, 0, 0, 0, UIntPtr.Zero)
    End Sub




    ''' <summary>
    '''Sends a middle mouse button down event at the current cursor position.
    ''' </summary>
    Public Shared Sub MiddleDown()
        SendMouseInput(MOUSEEVENTF.MIDDLEDOWN, 0, 0, 0, UIntPtr.Zero)
    End Sub



    ''' <summary>
    '''Sends a right mouse button down event at the current cursor position.
    ''' </summary>
    Public Shared Sub RightDown()
        SendMouseInput(MOUSEEVENTF.RIGHTDOWN, 0, 0, 0, UIntPtr.Zero)
    End Sub



    ''' <summary>
    '''Sends a left mouse button down event at the current cursor position.
    ''' </summary>
    Public Shared Sub LeftDown()
        SendMouseInput(MOUSEEVENTF.LEFTDOWN, 0, 0, 0, UIntPtr.Zero)
    End Sub




    ''' <summary>
    '''Sends a middle mouse button double click at the current cursor position.
    ''' </summary>
    Public Shared Sub MiddleDoubleClick()
        SendMouseInput(MOUSEEVENTF.MIDDLEDOWN, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
        SendMouseInput(MOUSEEVENTF.MIDDLEUP, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
        SendMouseInput(MOUSEEVENTF.MIDDLEDOWN, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
        SendMouseInput(MOUSEEVENTF.MIDDLEUP, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
    End Sub




    ''' <summary>
    '''Sends a right mouse button double click at the current cursor position.
    ''' </summary>
    Public Shared Sub RightDoubleClick()
        SendMouseInput(MOUSEEVENTF.RIGHTDOWN, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
        SendMouseInput(MOUSEEVENTF.RIGHTUP, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
        SendMouseInput(MOUSEEVENTF.RIGHTDOWN, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
        SendMouseInput(MOUSEEVENTF.RIGHTUP, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
    End Sub




    ''' <summary>
    '''Sends a left mouse button double click at the current cursor position.
    ''' </summary>
    Public Shared Sub LeftDoubleClick()
        SendMouseInput(MOUSEEVENTF.LEFTDOWN, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
        SendMouseInput(MOUSEEVENTF.LEFTUP, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
        SendMouseInput(MOUSEEVENTF.LEFTDOWN, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
        SendMouseInput(MOUSEEVENTF.LEFTUP, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
    End Sub




    ''' <summary>
    '''Sends a right mouse button click at the current cursor position.
    ''' </summary>
    Public Shared Sub RightClick()
        SendMouseInput(MOUSEEVENTF.RIGHTDOWN, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
        SendMouseInput(MOUSEEVENTF.RIGHTUP, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
    End Sub




    ''' <summary>
    '''Sends a left mouse button click at the current cursor position.
    ''' </summary>
    Public Shared Sub LeftClick()
        SendMouseInput(MOUSEEVENTF.LEFTDOWN, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
        SendMouseInput(MOUSEEVENTF.LEFTUP, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
    End Sub



    ''' <summary>
    '''Sends a middle mouse button click at the current cursor position.
    ''' </summary>
    Public Shared Sub MiddleClick()
        SendMouseInput(MOUSEEVENTF.MIDDLEDOWN, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
        SendMouseInput(MOUSEEVENTF.MIDDLEUP, 0, 0, 0, UIntPtr.Zero)
        Thread.Sleep(100)
    End Sub



End Class





