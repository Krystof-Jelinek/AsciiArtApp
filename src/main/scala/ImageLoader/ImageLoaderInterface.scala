package ImageLoader

import DataModels.PixelImage

trait ImageLoaderInterface {
  def loadImage() : PixelImage
}
