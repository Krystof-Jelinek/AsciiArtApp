class AppController {
  def run(args: Seq[String]): Unit = {
    val parser = new CommandParser
    val cmdHolder = parser.parse(args)

    println("WaitHere")
  }
}
