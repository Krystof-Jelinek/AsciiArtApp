package ImageSaver

import DataModels.AsciiImage

class PrintSaver extends ImageSaverInterface {
  def saveImage(img : AsciiImage) : Unit = {
    print(img.getString)
  }

}
