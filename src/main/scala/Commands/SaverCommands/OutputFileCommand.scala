package Commands.SaverCommands
import ImageSaver.{FileSaver, ImageSaver}

class OutputFileCommand(val filepath: String) extends SaveCommand {
  override def applyCommand(handeler: ImageSaver): Unit = {
    handeler.setImgSaverInterface(new FileSaver(filepath))
  }
}
