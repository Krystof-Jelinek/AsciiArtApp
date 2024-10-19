package ExceptionHandeler

class StdOutExceptionHandeler extends ExceptionHandeler {
  override def handle(exception: Exception): Unit = {
    println()
    println("***********************************************************************")
    println("Something went wrong: \n")
    println(exception.getMessage)
    println()
    println("***********************************************************************")
    println()
  }
}
