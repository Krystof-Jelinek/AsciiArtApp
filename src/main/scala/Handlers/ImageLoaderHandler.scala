package Handlers

import Commands.LoaderCommands.LoadCommand
import DataModels.PixelImage
import ExceptionHandeler.LogicException
import ImageLoader.ImageLoaderInterface

//before loading any image we need to know how/what image and so on..
// thats the responsibility of this class
class ImageLoaderHandler {
  private var loader : Option[ImageLoaderInterface] = None

  def setImageLoaderInterface(in : ImageLoaderInterface): Unit = {
    loader = Some(in)
  }

  //we need to know how/where to load thats why this needs argument
  def loadImage(loadCommand : LoadCommand): PixelImage = {

    loadCommand.applyCommand(this)
    if(loader.isDefined){
      return loader.get.loadImage()
    }
    throw LogicException("You did something very wrong or loading of image isnt working properly")
  }
}
