package Commands.LoaderCommands

import Handlers.ImageLoaderHandler
import ImageLoader.GifLoader

class LoadGifImageCommand(val path: String) extends LoadCommand {
  override def applyCommand(handeler: ImageLoaderHandler): Unit = {
    validatePathOrThrow(path)
    handeler.setImageLoaderInterface(new GifLoader(path))
  }
}
