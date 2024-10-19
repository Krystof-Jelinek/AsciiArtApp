package Commands.SaverCommands
import ImageSaver.{ImageSaver, PrintSaver}

class OutputConsoleCommand extends SaveCommand {
  override def applyCommand(handeler: ImageSaver): Unit = {
    handeler.setImgSaverInterface(new PrintSaver)
  }
}
