package DataModels

case class Pixel(red: Int, green: Int, blue: Int){
  require(red >= 0 && red <= 255, "Red value must be between 0 and 255")
  require(green >= 0 && green <= 255, "Green value must be between 0 and 255")
  require(blue >= 0 && blue <= 255, "Blue value must be between 0 and 255")
  def greyscale: Int = {
    (0.3 * red + 0.59 * green + 0.11 * blue).toInt
  }
}

