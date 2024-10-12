package ImageLoader

import DataModels.{Command, Image}

import java.io.{File, FileNotFoundException}

class ImageLoader {
  def loadImage(loadCommand : Command): Image = {
    val path = loadCommand.value
    val file = new File(path)
    if (!file.exists()) {
      throw new FileNotFoundException(s"File not found: $path")
    }

    if(loadCommand.value.endsWith(".png")){
      val loader: ImageLoaderInterface = new PngLoader()
      return loader.loadImage(loadCommand.value)
    }
    else if(loadCommand.value.endsWith(".jpg")){
      val loader: ImageLoaderInterface = new JpgLoader()
      return loader.loadImage(loadCommand.value)
    }
    else if(loadCommand.value.endsWith(".gif")){
      val loader: ImageLoaderInterface = new GifLoader()
      return loader.loadImage(loadCommand.value)
    }
    else{
      throw IllegalArgumentException("Unsupported image format")
    }
  }
}
