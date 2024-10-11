package DataModels

case class Pixel(red: Int, green: Int, blue: Int){
  def greyscale: Int = {
    (0.3 * red + 0.59 * green + 0.11 * blue).toInt
  }
}

