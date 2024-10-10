import java.awt.image.BufferedImage
import java.io.{File, FileNotFoundException}
import javax.imageio.ImageIO

class PngLoader extends ImageLoaderInterface {
  override def loadImage(path: String): Image = {
    val file = new File(path)
    if (!file.exists()) {
      throw new FileNotFoundException(s"File not found: $path")
    }

    val bufferedImage: BufferedImage = ImageIO.read(file)

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
        res.setPixel(x,y, Pixel(red, green,blue))
      }
    }

    return res

  }
}
