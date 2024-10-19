package ImageLoader

import Commands.LoaderCommands.LoadCommand
import DataModels.PixelImage
import ExceptionHandeler.LogicException

class ImageLoader {
  private var loader : Option[ImageLoaderInterface] = None

  def setImageLoaderInterface(in : ImageLoaderInterface): Unit = {
    loader = Some(in)
  }

  def loadImage(loadCommand : LoadCommand): PixelImage = {

    loadCommand.applyCommand(this)
    if(loader.isDefined){
      return loader.get.loadImage()
    }
    throw LogicException("You did something very wrong or loading of image isnt working properly")
  }
}
