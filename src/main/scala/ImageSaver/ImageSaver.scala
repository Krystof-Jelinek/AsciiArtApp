package ImageSaver

import Commands.SaverCommands.SaveCommand
import DataModels.AsciiImage
import ExceptionHandeler.LogicException

import scala.collection.mutable.ArrayBuffer

class ImageSaver {
  private var imgSaver : Option[ImageSaverInterface] = None

  def setImgSaverInterface(in : ImageSaverInterface): Unit = {
    imgSaver = Some(in)
  }

  def saveImage(img : AsciiImage, commands: ArrayBuffer[SaveCommand]) : Unit = {
    for(cmd <- commands){
      cmd.applyCommand(this)
      if(imgSaver.isDefined){
        imgSaver.get.saveImage(img)
      }
      else{
        throw LogicException("Error while saving images this should be logically impossible contact your god")
      }
    }
  }
}
