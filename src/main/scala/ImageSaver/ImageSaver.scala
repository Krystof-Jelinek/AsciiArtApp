package ImageSaver

import DataModels.AsciiImage
import DataModels.Command

import scala.collection.mutable.ArrayBuffer

class ImageSaver {
  private var imgSaver : Option[ImageSaverInterface] = None
  private var filePath : Option[String] = None

  def setImgSaverInterface(in : ImageSaverInterface): Unit = {
    imgSaver = Some(in)
  }

  def setFilePath(in: String): Unit = {
    filePath = Some(in)
  }

  def saveImage(img : AsciiImage, commands: ArrayBuffer[Command]) : Unit = {
    for(cmd <- commands){
      if(cmd.name == "--output-console"){
        val imgSaver : ImageSaverInterface = new PrintSaver
        imgSaver.saveImage(img)
      }
      else if (cmd.name == "--output-file") {
        val imgSaver: ImageSaverInterface = new FileSaver(cmd.value)
        imgSaver.saveImage(img)
      }
      else{
        throw IllegalArgumentException("This output command is not supported: " + cmd.name)
      }
    }
  }
}
