package DataModels

case class Pixel(red: Int, green: Int, blue: Int){
  require(red >= 0 && red <= 255, "Red value must be between 0 and 255")
  require(green >= 0 && green <= 255, "Green value must be between 0 and 255")
  require(blue >= 0 && blue <= 255, "Blue value must be between 0 and 255")
  def greyscale: Int = {
    val rWeight = GreyscaleWeights.redWeight
    val gWeight = GreyscaleWeights.greenWeight
    val bWeight = GreyscaleWeights.blueWeight
    (rWeight * red + gWeight * green + bWeight * blue).toInt
  }
}

//this object makes it possible to set how the grayscale value is calculated possibly even with new command
object GreyscaleWeights {
  var redWeight: Double = 0.3
  var greenWeight: Double = 0.59
  var blueWeight: Double = 0.11

  def setWeights(r: Double, g: Double, b: Double): Unit = {
    require(r >= 0 && g >= 0 && b >= 0, "Weights must be non-negative")
    require(math.abs(r + g + b - 1.0) < 0.001, "Weights must sum to 1")
    redWeight = r
    greenWeight = g
    blueWeight = b
  }
}