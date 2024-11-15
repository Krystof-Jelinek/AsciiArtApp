package Commands.LoaderCommands

import Handlers.ImageLoaderHandler
import ImageLoader.PngLoader

class LoadPngImageCommand(val path : String) extends LoadCommand {
  override def applyCommand(handeler: ImageLoaderHandler): Unit = {
    validatePathOrThrow(path)
    handeler.setImageLoaderInterface(new PngLoader(path))
  }
}
