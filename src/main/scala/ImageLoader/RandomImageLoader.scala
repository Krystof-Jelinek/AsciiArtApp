package ImageLoader
import DataModels.{Image, Pixel, PixelImage}

import scala.util.Random

class RandomImageLoader(seed : String) extends ImageLoaderInterface {
  private var random = new Random()
  if(seed != ""){
    random = new Random(seed.hashCode.toLong)
  }

  override def loadImage(): PixelImage = {
    val width = random.nextInt(500) + 1
    val height = random.nextInt(500) + 1

    val image = new PixelImage(width, height)

    // Fill the image with random RGB values
    for (x <- 0 until width; y <- 0 until height) {
      val r = random.nextInt(256) // Random value between 0 and 255 for red
      val g = random.nextInt(256) // Random value between 0 and 255 for green
      val b = random.nextInt(256) // Random value between 0 and 255 for blue
      image.setVal(x, y, Pixel(r,g,b))
    }
    image
  }
}
