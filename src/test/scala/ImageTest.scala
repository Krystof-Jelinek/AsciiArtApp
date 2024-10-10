import DataModels.{Image, Pixel}
import org.scalatest.funsuite.AnyFunSuite

class ImageTest extends AnyFunSuite {

  test("DataModels.Image should initialize with correct dimensions") {
    val img = new Image(100, 100)
    assert(img.width == 100)
    assert(img.height == 100)

    val img2 = new Image(420, 69)
    assert(img2.width == 420)
    assert(img2.height == 69)

    val img3 = new Image(1, 1)
    assert(img3.width == 1)
    assert(img3.height == 1)

    val img4 = new Image(2048, 1)
    assert(img4.width == 2048)
    assert(img4.height == 1)

    val img5 = new Image(1, 1080)
    assert(img5.width == 1)
    assert(img5.height == 1080)
  }

  test("DataModels.Image should set and get a pixel correctly") {
    val img = new Image(100, 100)
    val redPixel = Pixel(255, 0, 0)

    img.setPixel(10, 10, redPixel)
    assert(img.getPixel(10, 10) == Some(redPixel))
    assert(img.getPixel(1, 1) == Some(Pixel(0,0,0)))
  }

  test("DataModels.Image should return None for out-of-bounds pixel access") {
    val img = new Image(100, 100)

    assert(img.getPixel(-1, -1) == None)
    assert(img.getPixel(100, 10) == None)
    assert(img.getPixel(10, 100) == None)
  }

  test("DataModels.Image should throw an IllegalArgumentException when setting a pixel out of bounds") {
    val img = new Image(100, 100)
    val redPixel = Pixel(255, 0, 0)

    assertThrows[IllegalArgumentException] {
      img.setPixel(100, 100, redPixel) // Out of bounds
    }
  }

  test("DataModels.Image should throw an IllegalArgumentException when width or height is out of bounds") {
    assertThrows[IllegalArgumentException] {
      val img = new Image(-10, 100)
    }

    assertThrows[IllegalArgumentException] {
      val img = new Image(5, -15)
    }

    assertThrows[IllegalArgumentException] {
      val img = new Image(5, 1081)
    }

    assertThrows[IllegalArgumentException] {
      val img = new Image(2049, 108)
    }
  }
}
