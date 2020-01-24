Namespace Tiny.UDP
    ' Various encoding types for data
    Public Enum EncodingType
        [Default] = 0
        ASCII = 1
        [Unicode] = 2
        UTF7 = 3
        UTF8 = 4
    End Enum

    ' Custom exceptions
    Public Class BadEncodingException
        Inherits System.Exception

        Public Sub New()
            MyBase.New("The encoding type selected is not valid.")
        End Sub

        Public Sub New(ByVal Message As String)
            MyBase.New(Message)
        End Sub
    End Class

    Public Class ProtocolNotSupportedException
        Inherits System.Exception

        Public Sub New()
            MyBase.New("The protocol selected is not supported.")
        End Sub

        Public Sub New(ByVal Message As String)
            MyBase.New(Message)
        End Sub
    End Class

End Namespace