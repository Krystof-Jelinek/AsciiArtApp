package ExceptionHandeler

class StdOutExceptionHandeler extends ExceptionHandeler {
  override def handle(exception: Exception): Unit = {
    println(exception.getMessage)
  }
}
