package TransformationHandelers.Filters
import DataModels.{Image, Pixel}

class InvertFilter extends  Filter {
  override def applyFilter(img: Image): Unit = {
    var x = 0
    var y = 0

    while(y < img.height){
      while(x < img.width){
        val pxl : Pixel = img.getPixel(x,y).get
        val inversion = 255 - pxl.greyscale
        // this technically invalids the color but thats not a problem since we dont care about colors
        img.setPixel(x,y,Pixel(inversion, inversion, inversion))
        x += 1
      }
      x = 0
      y += 1
    }
  }
}
