package DataModels

import scala.collection.mutable.ArrayBuffer

class AsciiImage(var width: Int, var height: Int) {
  require(width > 0 && width <= 2048, "Width must be between 1 and 2048")
  require(height > 0 && height <= 1080, "Height must be between 1 and 1080")

  private val chars: ArrayBuffer[ArrayBuffer[Char]] = ArrayBuffer.fill(width, height)(' ')

  def getChar(x: Int, y: Int): Option[Char] = {
    if (x >= 0 && x < width && y >= 0 && y < height) {
      return Some(chars(x)(y))
    } else {
      return None
    }
  }

  def setChar(x: Int, y: Int, c: Char): Unit = {
    if (x >= 0 && x < width && y >= 0 && y < height) {
      chars(x)(y) = c
    } else {
      throw new IllegalArgumentException("Coordinates are out of bounds.")
    }
  }

  //deletes the above pixels or adds white new ones
  def resize(x: Int, y: Int): Unit = {
    require(x > 0 && x <= 2048, "Width must be between 1 and 2048")
    require(y > 0 && y <= 1080, "Height must be between 1 and 1080")
    width = x
    height = y
    //shrinking if needed
    chars.dropRightInPlace(chars.length - width)
    for (column <- chars) {
      column.dropRightInPlace(column.length - height)
    }
    //expanding if needed
    while (height - chars(0).length > 0) {
      for (column <- chars) {
        column.addOne(' ')
      }
    }
    while (width - chars.length > 0) {
      chars.addOne(ArrayBuffer.fill(height)(' '))
    }
  }

  def getString : String = {
    var res = ""
    var x = 0
    var y = 0

    while (y < height) {
      while (x < width) {
        val char = getChar(x, y).get
        res += char
        x += 1
      }
      res += '\n'
      y += 1
      x = 0
    }

    return res
  }

}
