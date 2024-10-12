package TransformationHandelers.Filters
import DataModels.{Image, Pixel}

class BrightnessFilter(val intensity : Int) extends Filter {
  override def applyFilter(img: Image): Unit = {
    var x = 0
    var y = 0

    while (y < img.height) {
      while (x < img.width) {
        val pxl: Pixel = img.getPixel(x, y).get
        val newBrightnes = getBrightness(intensity, pxl)
        // this technically invalids the color but thats not a problem since we dont care about colors
        img.setPixel(x, y, Pixel(newBrightnes, newBrightnes, newBrightnes))
        x += 1
      }
      x = 0
      y += 1
    }
  }

  private def getBrightness(intensity : Int, pixel: Pixel): Int = {
    var res = pixel.greyscale + intensity
    if(res < 0){
      res = 0
    }
    if(res > 255){
      res = 255
    }
    return res
  }
}
