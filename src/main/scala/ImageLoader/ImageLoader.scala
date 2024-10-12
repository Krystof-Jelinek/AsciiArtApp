package ImageLoader

import DataModels.{Command, Image}

import java.io.{File, FileNotFoundException}

class ImageLoader {
  def loadImage(loadCommand : Command): Image = {
    if(loadCommand.name == "--image-random"){
      val loader: ImageLoaderInterface = new RandomImageLoader(loadCommand.value)
      return loader.loadImage()
    }

    val path = loadCommand.value
    val file = new File(path)
    if (!file.exists()) {
      throw new FileNotFoundException(s"File not found: $path")
    }

    if(loadCommand.value.endsWith(".png")){
      val loader: ImageLoaderInterface = new PngLoader(path)
      return loader.loadImage()
    }
    else if(loadCommand.value.endsWith(".jpg")){
      val loader: ImageLoaderInterface = new JpgLoader(path)
      return loader.loadImage()
    }
    else if(loadCommand.value.endsWith(".gif")){
      val loader: ImageLoaderInterface = new GifLoader(path)
      return loader.loadImage()
    }
    else{
      throw IllegalArgumentException("Unsupported image format")
    }
  }
}
