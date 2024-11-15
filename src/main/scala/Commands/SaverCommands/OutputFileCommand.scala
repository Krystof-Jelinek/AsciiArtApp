package Commands.SaverCommands

import Handlers.ImageSaverHandler
import ImageSaver.FileSaver

class OutputFileCommand(val filepath: String) extends SaveCommand {
  override def applyCommand(handeler: ImageSaverHandler): Unit = {
    handeler.setImgSaverInterface(new FileSaver(filepath))
  }
}
