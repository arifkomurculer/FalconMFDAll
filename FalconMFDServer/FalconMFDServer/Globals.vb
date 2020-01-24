Imports Microsoft.Win32


'Kullanılan ClsReadMemoryThread
'clsReadMemoryThreadFlightDataLoad
'clsReadMemoryThreadSagMFD
'clsReadMemoryThreadSolMFD
'clsReadMemoryThreadDED
'clsReadMemoryThreadRWR
'clsReadMemoryThreadFlightDataNavMap



Module Globals

    'Public vrSolWait As Integer = 1000
    'Public vrSagWait As Integer = 1000
    'Public vrICPWait As Integer = 1000
    'Public vrRWRWait As Integer = 1000

    Public vrThreadUyu As Integer = 1


    '8 64 bit için
    '4 32 bit için
    Public Const vr32_64 As Integer = 4


    Public XCoordinate As Double = 0
    Public YCoordinate As Double = 0

    Public XMax As Double = 3360000
    Public XMin As Double = 0

    Public YMax As Double = 3360000
    Public YMin As Double = 0

    Public XMaxEMF As Double = 6720000
    Public XMinEMF As Double = 418620

    Public YMaxEMF As Double = 6720000
    Public YMinEMF As Double = 418620

    Public vrWidthHeight As Integer = 4096 '4096 - 2048



End Module


Public Class fnk32_64
    Dim vr32_64Cls As Integer = 8

    Public Function fnkGetValue() As Integer
        Return vr32_64Cls
    End Function

    Public Sub fnkSetValue(prVal As Integer)
        vr32_64Cls = prVal
    End Sub



End Class