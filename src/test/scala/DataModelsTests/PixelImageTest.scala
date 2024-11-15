package DataModelsTests

import DataModels.{Pixel, PixelImage}
import org.scalatest.funsuite.AnyFunSuite

class PixelImageTest extends AnyFunSuite {

  test("Image should initialize with correct dimensions") {
    val img = new PixelImage(100, 100)
    assert(img.width == 100)
    assert(img.height == 100)

    val img2 = new PixelImage(420, 69)
    assert(img2.width == 420)
    assert(img2.height == 69)

    val img3 = new PixelImage(1, 1)
    assert(img3.width == 1)
    assert(img3.height == 1)

    val img4 = new PixelImage(2048, 1)
    assert(img4.width == 2048)
    assert(img4.height == 1)

    val img5 = new PixelImage(1, 1080)
    assert(img5.width == 1)
    assert(img5.height == 1080)
  }

  test("Image should set and get correctly") {
    val img = new PixelImage(100, 100)
    val redPixel = Pixel(255, 0, 0)

    img.setVal(10, 10, redPixel)
    assert(img.getVal(10, 10).contains(redPixel))
    assert(img.getVal(1, 1).contains(Pixel(0, 0, 0)))
  }

  test("Image should return None for out-of-bounds access") {
    val img = new PixelImage(100, 100)

    assert(img.getVal(-1, -1).isEmpty)
    assert(img.getVal(100, 10).isEmpty)
    assert(img.getVal(10, 100).isEmpty)
  }

  test("Image should throw an IllegalArgumentException when setting out of bounds") {
    val img = new PixelImage(100, 100)
    val redPixel = Pixel(255, 0, 0)

    assertThrows[IllegalArgumentException] {
      img.setVal(100, 100, redPixel)
    }
  }

  test("Image should throw an IllegalArgumentException when width or height is out of bounds") {
    assertThrows[IllegalArgumentException] {
      val img = new PixelImage(-10, 100)
    }

    assertThrows[IllegalArgumentException] {
      val img = new PixelImage(5, -15)
    }

    assertThrows[IllegalArgumentException] {
      val img = new PixelImage(5, 1081)
    }

    assertThrows[IllegalArgumentException] {
      val img = new PixelImage(2049, 108)
    }
  }

  test("Resize test same size"){
    val img = new PixelImage(5, 5)
    img.resize(5, 5)
    assert(img.width == 5)
    assert(img.height == 5)
    assert(img.getVal(4, 4).get == Pixel(0, 0, 0))
    assertThrows[IndexOutOfBoundsException] {
      img.testInternalSize(4, 5)
    }
    assertThrows[IndexOutOfBoundsException] {
      img.testInternalSize(5, 0)
    }
  }

  test("Resize test shrinking"){
    val img = new PixelImage(10,10)
    img.resize(5,5)
    assert(img.width == 5)
    assert(img.height == 5)
    assert(img.getVal(4,4).get == Pixel(0,0,0))
    assertThrows[IndexOutOfBoundsException]{
      img.testInternalSize(4, 5)
    }
    assertThrows[IndexOutOfBoundsException] {
      img.testInternalSize(5, 0)
    }

    img.resize(3,3)
    assert(img.width == 3)
    assert(img.height == 3)
    assert(img.getVal(2, 2).get == Pixel(0, 0, 0))
    assert(img.getVal(0, 0).get == Pixel(0, 0, 0))
    assertThrows[IndexOutOfBoundsException] {
      img.testInternalSize(2,3)
    }
    assertThrows[IndexOutOfBoundsException] {
      img.testInternalSize(3,0)
    }

  }

  test("Resize test enlarging"){
    val img = new PixelImage(1, 1)
    img.setVal(0,0,Pixel(100,100,100))
    img.resize(3, 3)
    assert(img.width == 3)
    assert(img.height == 3)
    assert(img.getVal(2, 2).get == Pixel(0, 0, 0))
    assert(img.getVal(0, 0).get == Pixel(100, 100, 100))
    assertThrows[IndexOutOfBoundsException] {
      img.testInternalSize(2,3)
    }
    assertThrows[IndexOutOfBoundsException] {
      img.testInternalSize(3,0)
    }

    img.resize(5, 5)
    assert(img.width == 5)
    assert(img.height == 5)
    assert(img.getVal(2, 2).get == Pixel(0, 0, 0))
    assert(img.getVal(4, 3).get == Pixel(0, 0, 0))
    assert(img.getVal(0, 0).get == Pixel(100, 100, 100))
    assertThrows[IndexOutOfBoundsException] {
      img.testInternalSize(4,5)
    }
    assertThrows[IndexOutOfBoundsException] {
      img.testInternalSize(5, 0)
    }
  }

  test("Cant resize to bigger then max size or negative size"){
    assertThrows[IllegalArgumentException] {
      val img = new PixelImage(100, 100)
      img.resize(0,50)
    }
    assertThrows[IllegalArgumentException] {
      val img = new PixelImage(100, 100)
      img.resize(50, -20)
    }

    assertThrows[IllegalArgumentException] {
      val img = new PixelImage(100, 100)
      img.resize(4000, 15)
    }
  }

}
