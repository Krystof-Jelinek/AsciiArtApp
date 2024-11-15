package Commands.SaverCommands

import Handlers.ImageSaverHandler
import ImageSaver.PrintSaver

class OutputConsoleCommand extends SaveCommand {
  override def applyCommand(handeler: ImageSaverHandler): Unit = {
    handeler.setImgSaverInterface(new PrintSaver)
  }
}
