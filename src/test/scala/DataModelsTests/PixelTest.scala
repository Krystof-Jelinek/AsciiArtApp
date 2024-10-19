package DataModelsTests

import DataModels.Pixel
import org.scalatest.funsuite.AnyFunSuite


class PixelTest extends AnyFunSuite{
  test("valid Pixel grayscale check"){
    val pixel = Pixel(0,0,0)
    assert(pixel.greyscale == 0)

    val pixel2 = Pixel(255, 255, 255)
    assert(pixel2.greyscale == 255)

    val pixel3 = Pixel(69, 120, 69)
    assert(pixel3.greyscale == 99)
  }

  test("Creating unvalid pixel") {
    assertThrows[IllegalArgumentException]{
      val pixel = Pixel(-69, 120, 69)
    }
    assertThrows[IllegalArgumentException] {
      val pixel = Pixel(69, -120, 69)
    }
    assertThrows[IllegalArgumentException] {
      val pixel = Pixel(69, 120, -1)
    }
    assertThrows[IllegalArgumentException] {
      val pixel = Pixel(69, 280, 1)
    }
  }
}
