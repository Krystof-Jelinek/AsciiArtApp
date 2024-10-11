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
    }
  }
}
