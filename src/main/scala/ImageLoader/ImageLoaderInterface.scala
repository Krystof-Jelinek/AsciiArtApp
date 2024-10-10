package ImageLoader

import DataModels.Image

trait ImageLoaderInterface {
  def loadImage(path : String) : Image
}
