package TransformationHandelers.Filters
import DataModels.{Image, Pixel}

class ScaleFilter(val size : Float) extends Filter {
  override def applyFilter(img: Image): Unit = {
    if(size.toInt == 1){
      return
    }
    if (size.toInt == 4) {
      enlarge(img)
    }

  }

  private def enlarge(img: Image): Unit = {
    img.resize(img.height*2, img.width*2)

    //backward indexes:
    var backX = img.width - 1
    var backY = img.height - 1

    var x = img.width/2 - 1
    var y = img.height/2 - 1

    while (y >= 0) {
      while (x >= 0) {
        img.setPixel(backX, backY, img.getPixel(x,y).get)
        img.setPixel(backX, backY - 1, img.getPixel(x,y).get)
        backX -= 1
        img.setPixel(backX, backY, img.getPixel(x,y).get)
        img.setPixel(backX, backY - 1, img.getPixel(x,y).get)
        backX -= 1
        x -= 1
      }
      backX = img.width - 1
      backY -= 2
      x = img.width/2 - 1
      y -= 1

    }
  }

}
