package Commands.LoaderCommands
import Handlers.ImageLoaderHandler
import ImageLoader.RandomOnlineImageLoader

class LoadRandomOnlineImageCommand(val seed : String) extends LoadCommand {
  override def applyCommand(handeler: ImageLoaderHandler): Unit = {
    handeler.setImageLoaderInterface(new RandomOnlineImageLoader(seed))
  }
}
