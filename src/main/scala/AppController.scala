import CommandLineUI.Parser.CommandParser
import DataModels.AsciiImage
import ExceptionHandeler.{ExceptionHandeler, StdOutExceptionHandeler}
import Handlers.{ImageLoaderHandler, ImageSaverHandler, TransformationHandler}

class AppController {
  def run(args: Seq[String]): Unit = {
    try {
      val parser = new CommandParser
      val cmdHolder = parser.parse(args)

      val imageLoader = new ImageLoaderHandler()
      val image = imageLoader.loadImage(cmdHolder.loadCommand.get)

      val transformationHandeler = new TransformationHandler
      val asciiImage: AsciiImage = transformationHandeler.execute(image, cmdHolder.transformCommands)

      val imageSaver = new ImageSaverHandler()
      imageSaver.saveImage(asciiImage, cmdHolder.saveCommands)
    }
    catch {
      case e: Exception =>
        val exceptionHandeler: ExceptionHandeler = StdOutExceptionHandeler()
        exceptionHandeler.handle(e)
    }
  }
}