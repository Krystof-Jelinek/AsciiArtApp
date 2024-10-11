package ImageSaver

import DataModels.AsciiImage

trait ImageSaverInterface {
  def saveImage(img : AsciiImage) : Unit
}
