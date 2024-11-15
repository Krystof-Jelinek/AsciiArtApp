package Commands.LoaderCommands

import Handlers.ImageLoaderHandler
import ImageLoader.JpgLoader

class LoadJpgImageCommand(val path: String) extends LoadCommand {
  override def applyCommand(handeler: ImageLoaderHandler): Unit = {
    validatePathOrThrow(path)
    handeler.setImageLoaderInterface(new JpgLoader(path))
  }
}
