package ImageLoader

import DataModels.{Image, Pixel}

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class StandartFormatImageLoader extends ImageLoaderInterface {
  override def loadImage(path: String): Image = {

    val bufferedImage: BufferedImage = ImageIO.read(new File(path))

    val width = bufferedImage.getWidth
    val height = bufferedImage.getHeight

    val res = new Image(width, height)

    // Extract pixel data from the BufferedImage
    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val rgb = bufferedImage.getRGB(x, y)
        val red = (rgb >> 16) & 0xFF
        val green = (rgb >> 8) & 0xFF
        val blue = rgb & 0xFF
        res.setPixel(x, y, Pixel(red, green, blue))
      }
    }

    return res

  }
}
