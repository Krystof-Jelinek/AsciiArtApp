package ImageLoader

import DataModels.{Image, Pixel, PixelImage}

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class StandartFormatImageLoader(path : String) extends ImageLoaderInterface {
  override def loadImage(): PixelImage = {

    val bufferedImage: BufferedImage = ImageIO.read(new File(path))

    val width = bufferedImage.getWidth
    val height = bufferedImage.getHeight

    val res = new PixelImage(width, height)

    // Extract pixel data from the BufferedImage
    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val rgb = bufferedImage.getRGB(x, y)
        val red = (rgb >> 16) & 0xFF
        val green = (rgb >> 8) & 0xFF
        val blue = rgb & 0xFF
        res.setVal(x, y, Pixel(red, green, blue))
      }
    }

    return res

  }
}
