package Handlers

import Commands.SaverCommands.SaveCommand
import DataModels.AsciiImage
import ExceptionHandeler.LogicException
import ImageSaver.ImageSaverInterface

import scala.collection.mutable.ArrayBuffer

//before saving any image we need to know where/what image and so on..
// thats the responsibility of this class
class ImageSaverHandler {
  private var imgSaver : Option[ImageSaverInterface] = None

  def setImgSaverInterface(in : ImageSaverInterface): Unit = {
    imgSaver = Some(in)
  }

  //we need some informations how to save the image thats why here is savecommand array
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
