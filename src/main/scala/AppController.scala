import DataModels.AsciiImage
import ImageLoader.ImageLoader
import Parser.CommandParser
import TransformationHandelers.TransformationHandeler

class AppController {
  def run(args: Seq[String]): Unit = {
    val parser = new CommandParser
    val cmdHolder = parser.parse(args)

    val imageLoader = ImageLoader()
    val image = imageLoader.loadImage(cmdHolder.loadCommand)

    val transformationHandeler = new TransformationHandeler
    val asciiImage : AsciiImage = transformationHandeler.execute(image,cmdHolder.transformCommands)

    println(asciiImage.value)

    println("WaitHere")
  }
}
