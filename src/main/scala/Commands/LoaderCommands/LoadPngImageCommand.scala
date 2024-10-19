package Commands.LoaderCommands
import ImageLoader.{ImageLoader, PngLoader}

class LoadPngImageCommand(val path : String) extends LoadCommand {
  override def applyCommand(handeler: ImageLoader): Unit = {
    validatePathOrThrow(path)
    handeler.setImageLoaderInterface(new PngLoader(path))
  }
}
