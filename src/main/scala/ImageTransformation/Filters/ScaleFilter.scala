package ImageTransformation.Filters
import DataModels.{Image, PixelImage}

class ScaleFilter(val size : Float) extends Filter {
  override def applyFilter(img: PixelImage): Unit = {
    if(size.toInt == 1){
      return
    }
    else if (size.toInt == 4) {
      enlarge(img)
    }
    else if(size == 0.25){
      shrink(img)
    }
    else{
      throw IllegalArgumentException("Invalid argument for scale filter")
    }
  }

  private def enlarge(img: PixelImage): Unit = {
    img.resize(img.width*2, img.height*2)

    //backward indexes:
    var backX = img.width - 1
    var backY = img.height - 1

    var x = img.width/2 - 1
    var y = img.height/2 - 1

    while (y >= 0) {
      while (x >= 0) {
        img.setVal(backX, backY, img.getVal(x,y).get)
        img.setVal(backX, backY - 1, img.getVal(x,y).get)
        backX -= 1
        img.setVal(backX, backY, img.getVal(x,y).get)
        img.setVal(backX, backY - 1, img.getVal(x,y).get)
        backX -= 1
        x -= 1
      }
      backX = img.width - 1
      backY -= 2
      x = img.width/2 - 1
      y -= 1

    }
  }

  private def shrink(img: PixelImage) : Unit = {
    if(img.width == 1 || img.height == 1){
      throw IllegalArgumentException("This image cant be shrinked anymore its dimensions are too small already")
    }

    //if the dimensions of the image are not even we make them even (adding one line of black pixels that will change the image a bit but customer wanned it that way
    //how convinient
    if(img.width % 2 == 1){
      img.resize(img. width + 1, img.height)
    }

    if (img.height % 2 == 1) {
      img.resize(img.width, img.height + 1)
    }

    //get indexes:
    var getX = 0
    var getY = 0

    //write indexes
    var writeX = 0
    var writeY = 0

    while (getY < img.height) {
      while (getX < img.width) {
        img.setVal(writeX, writeY, img.getVal(getX, getY).get)
        writeX += 1
        getX += 2
      }
      writeX = 0
      getX = 0
      getY += 2
      writeY += 1
    }
    img.resize(img.width/2, img.height/2)
  }
}
