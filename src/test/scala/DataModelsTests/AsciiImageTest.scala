package DataModelsTests

import DataModels.AsciiImage
import org.scalatest.funsuite.AnyFunSuite

//all the Abstract Image methods are tested in PixelImageTest so here only testing the conversion to string
class AsciiImageTest extends AnyFunSuite{
  test("empty image case test") {
    val img = new AsciiImage(2, 2)
    assert(img.getString == "  \n  \n")
  }

  test("basic image case test") {
    val img = new AsciiImage(2, 2)
    img.setVal(0,0,'X')
    img.setVal(1,1,'Y')
    assert(img.getString == "X \n Y\n")
  }

  test("non square image case test -- wider") {
    val img = new AsciiImage(3, 1)
    img.setVal(0, 0, 'X')
    img.setVal(1, 0, 'Y')
    img.setVal(2, 0, 'Z')
    assert(img.getString == "XYZ\n")
  }

  test("non square image case test -- taller") {
    val img = new AsciiImage(2, 4)
    img.setVal(0, 0, 'X')
    img.setVal(1, 1, 'Y')
    img.setVal(1, 3, 'Z')
    assert(img.getString == "X \n Y\n  \n Z\n")
  }
}
