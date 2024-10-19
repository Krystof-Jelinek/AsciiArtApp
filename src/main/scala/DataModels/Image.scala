package DataModels

import scala.collection.mutable.ArrayBuffer

abstract class Image[T](var width: Int, var height: Int, defaultValue : T) {
  require(width > 0 && width <= 2048, "Width of Image must be between 1 and 2048")
  require(height > 0 && height <= 1080, "Height of Image must be between 1 and 1080")

  protected val vals: ArrayBuffer[ArrayBuffer[T]] = ArrayBuffer.fill(width)(
    ArrayBuffer.fill(height)(defaultValue)
  )
  def getVal(x: Int, y: Int): Option[T] = {
    if (x >= 0 && x < width && y >= 0 && y < height) {
      return Some(vals(x)(y))
    } else {
      return None
    }
  }

  def setVal(x: Int, y: Int, in: T): Unit = {
    if (x >= 0 && x < width && y >= 0 && y < height) {
      vals(x)(y) = in
    } else {
      throw new IllegalArgumentException("Coordinates in Image are out of bounds.")
    }
  }

  def resize(x: Int, y: Int): Unit = {
    require(x > 0 && x <= 2048, "Width of Image must be between 1 and 2048")
    require(y > 0 && y <= 1080, "Height of Image must be between 1 and 1080")
    width = x
    height = y
    //shrinking if needed
    vals.dropRightInPlace(vals.length - width)
    for (column <- vals) {
      column.dropRightInPlace(column.length - height)
    }
    //expanding if needed
    while (height - vals(0).length > 0) {
      for (column <- vals) {
        column.addOne(defaultValue)
      }
    }
    while (width - vals.length > 0) {
      vals.addOne(ArrayBuffer.fill(height)(defaultValue))
    }
  }
}
