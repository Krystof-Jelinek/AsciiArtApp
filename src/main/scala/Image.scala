class Image(val width: Int, val height: Int) {
  private val pixels: Array[Array[Pixel]] = Array.fill(width, height)(Pixel(0, 0, 0))

  // Method to get a pixel at coordinates (x, y)
  def getPixel(x: Int, y: Int): Option[Pixel] = {
    if (x >= 0 && x < width && y >= 0 && y < height) {
      Some(pixels(x)(y))
    } else {
      None // Return None if the coordinates are out of bounds
    }
  }

  // Method to set a pixel at coordinates (x, y)
  def setPixel(x: Int, y: Int, pixel: Pixel): Unit = {
    if (x >= 0 && x < width && y >= 0 && y < height) {
      pixels(x)(y) = pixel
    } else {
      throw new IllegalArgumentException("Coordinates are out of bounds.")
    }
  }
}
