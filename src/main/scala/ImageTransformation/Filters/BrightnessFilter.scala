package ImageTransformation.Filters
import DataModels.{Pixel, PixelImage}

class BrightnessFilter(val intensity : Int) extends Filter {
  override def applyFilter(img: PixelImage): Unit = {
    var x = 0
    var y = 0

    while (y < img.height) {
      while (x < img.width) {
        val pxl: Pixel = img.getVal(x, y).get
        val newBrightnes = getBrightness(intensity, pxl)
        // this technically invalids the colors of the picture but thats not a problem since we dont care about colors
        // the greyscale value will be calculated corectly since the indexes add up to 1
        //example pixel(255,0,50) --> has greyscale value == 82
        //and so does pixel(82,82,82) --> greyscale value == 82
        img.setVal(x, y, Pixel(newBrightnes, newBrightnes, newBrightnes))
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
