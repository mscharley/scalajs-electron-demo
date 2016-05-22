package autowire.electronipc

class IpcException(message: String = null, cause: Throwable = null)
  extends RuntimeException(message, cause)
