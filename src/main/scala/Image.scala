class Image(val width: Int, val height: Int) {
  require(width > 0 && width <= 2048, "Width must be between 1 and 2048")
  require(height > 0 && height <= 1080, "Height must be between 1 and 1080")

  private val pixels: Array[Array[Pixel]] = Array.fill(width, height)(Pixel(0, 0, 0))

  def getPixel(x: Int, y: Int): Option[Pixel] = {
    if (x >= 0 && x < width && y >= 0 && y < height) {
      return Some(pixels(x)(y))
    } else {
      return None
    }
  }

  def setPixel(x: Int, y: Int, pixel: Pixel): Unit = {
    if (x >= 0 && x < width && y >= 0 && y < height) {
      pixels(x)(y) = pixel
    } else {
      throw new IllegalArgumentException("Coordinates are out of bounds.")
    }
  }
}
