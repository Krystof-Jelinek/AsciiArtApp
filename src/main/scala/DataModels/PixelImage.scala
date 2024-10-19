package DataModels

import scala.collection.mutable.ArrayBuffer

class PixelImage(width: Int, height: Int) extends Image[Pixel](width, height, Pixel(0,0,0)){
  def testInternalSize(x : Int, y : Int): Unit = {
    val t = vals(x)(y)
  }
}
