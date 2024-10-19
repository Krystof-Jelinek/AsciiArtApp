package Commands.LoaderCommands

import ImageLoader.{GifLoader, ImageLoader}

class LoadGifImageCommand(val path: String) extends LoadCommand {
  override def applyCommand(handeler: ImageLoader): Unit = {
    validatePathOrThrow(path)
    handeler.setImageLoaderInterface(new GifLoader(path))
  }
}
