class AppController {
  def run(args: Seq[String]): Unit = {
    val parser = new CommandParser
    val cmdHolder = parser.parse(args)

    val imageLoader = ImageLoader()
    val image = imageLoader.loadImage(cmdHolder.loadCommand)

    println("WaitHere")
  }
}
