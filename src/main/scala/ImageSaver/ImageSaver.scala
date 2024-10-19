package ImageSaver

import DataModels.AsciiImage
import DataModels.Command

import scala.collection.mutable.ArrayBuffer

class ImageSaver {
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
