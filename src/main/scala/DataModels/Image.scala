package DataModels

import DataModels.Pixel

import scala.collection.mutable.ArrayBuffer

class Image(var width: Int, var height: Int) {
  require(width > 0 && width <= 2048, "Width must be between 1 and 2048")
  require(height > 0 && height <= 1080, "Height must be between 1 and 1080")

  val pixels: ArrayBuffer[ArrayBuffer[Pixel]] = ArrayBuffer.fill(width, height)(Pixel(0, 0, 0))

  def getPixel(x: Int, y: Int): Option[Pixel] = {
    if (x >= 0 && x < width && y >= 0 && y < height) {
      return Some(pixels(x)(y))
    } else {
      return None
    }
  }

  def setPixel(x: Int, y: Int, pixel: Pixel): Unit = {
    if (x >= 0 && x < width && y >= 0 && y < height) {
      pixels(x)(y) = Pixel(pixel.red, pixel.green, pixel.blue)
    } else {
      throw new IllegalArgumentException("Coordinates are out of bounds.")
    }
  }

  //deletes the above pixels or adds white new ones
  def resize(x : Int, y : Int) : Unit = {
    require(x > 0 && x <= 2048, "Width must be between 1 and 2048")
    require(y > 0 && y <= 1080, "Height must be between 1 and 1080")
    width = x
    height = y
    //shrinking if needed
    pixels.dropRightInPlace(pixels.length - width)
    for(column <- pixels){
      column.dropRightInPlace(column.length - height)
    }
    //expanding if needed
    while(height - pixels(0).length > 0){
      for(column <- pixels){
        column.addOne(Pixel(0,0,0))
      }
    }
    while(width - pixels.length > 0){
      pixels.addOne(ArrayBuffer.fill(height)(Pixel(0,0,0)))
    }
  }
}
