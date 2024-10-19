package Commands.LoaderCommands

import ImageLoader.{ImageLoader, JpgLoader}

class LoadJpgImageCommand(val path: String) extends LoadCommand {
  override def applyCommand(handeler: ImageLoader): Unit = {
    validatePathOrThrow(path)
    handeler.setImageLoaderInterface(new JpgLoader(path))
  }
}
