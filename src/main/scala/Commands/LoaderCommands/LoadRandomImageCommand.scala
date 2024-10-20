package Commands.LoaderCommands
import ImageLoader.{ImageLoader, RandomImageLoader}

class LoadRandomImageCommand(val seed : String) extends LoadCommand{
  override def applyCommand(handeler: ImageLoader): Unit = {
    handeler.setImageLoaderInterface(new RandomImageLoader(seed))
  }
}
