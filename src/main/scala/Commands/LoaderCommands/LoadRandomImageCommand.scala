package Commands.LoaderCommands

import Handlers.ImageLoaderHandler
import ImageLoader.RandomImageLoader

class LoadRandomImageCommand(val seed : String) extends LoadCommand{
  override def applyCommand(handeler: ImageLoaderHandler): Unit = {
    handeler.setImageLoaderInterface(new RandomImageLoader(seed))
  }
}
