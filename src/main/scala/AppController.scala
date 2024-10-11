import DataModels.AsciiImage
import ImageLoader.ImageLoader
import ImageSaver.ImageSaver
import Parser.CommandParser
import TransformationHandelers.TransformationHandeler

class AppController {
  def run(args: Seq[String]): Unit = {
    val parser = new CommandParser
    val cmdHolder = parser.parse(args)

    val imageLoader = new ImageLoader()
    var image = imageLoader.loadImage(cmdHolder.loadCommand)

    val transformationHandeler = new TransformationHandeler
    val asciiImage : AsciiImage = transformationHandeler.execute(image,cmdHolder.transformCommands)

    val imageSaver =  new ImageSaver()
    imageSaver.saveImage(asciiImage, cmdHolder.saveCommands)

  }
}
