package ImageLoader

import DataModels.{Command, Image}

class ImageLoader {
  def loadImage(loadCommand : Command): Image = {
    if(loadCommand.value.endsWith(".png")){
      val loader: ImageLoaderInterface = new PngLoader()
      return loader.loadImage(loadCommand.value)
    }
    else{
      throw IllegalArgumentException("Unsupported image format")
    }
  }
}
