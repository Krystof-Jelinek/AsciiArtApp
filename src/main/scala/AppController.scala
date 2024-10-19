import DataModels.AsciiImage
import ExceptionHandeler.{ExceptionHandeler, StdOutExceptionHandeler}
import ImageLoader.ImageLoader
import ImageSaver.ImageSaver
import Parser.CommandParser
import TransformationHandelers.TransformationHandeler

class AppController {
  def run(args: Seq[String]): Unit = {
    try {
      val parser = new CommandParser
      val cmdHolder = parser.parse(args)

      val imageLoader = new ImageLoader()
      var image = imageLoader.loadImage(cmdHolder.loadCommand)

      val transformationHandeler = new TransformationHandeler
      val asciiImage: AsciiImage = transformationHandeler.execute(image, cmdHolder.transformCommands)

      val imageSaver = new ImageSaver()
      imageSaver.saveImage(asciiImage, cmdHolder.saveCommands)
    }
    catch {
      case e: Exception => {
        val exceptionHandeler: ExceptionHandeler = StdOutExceptionHandeler()
        exceptionHandeler.handle(e)
      }
    }
  }
}