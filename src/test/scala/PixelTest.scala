import org.scalatest.funsuite.AnyFunSuite
import DataModels.Pixel


class PixelTest extends AnyFunSuite{
  test("valid Pixel grayscale check"){
    val pixel = new Pixel(0,0,0)
    assert(pixel.greyscale == 0)

    val pixel2 = new Pixel(255, 255, 255)
    assert(pixel2.greyscale == 255)

    val pixel3 = new Pixel(69, 120, 69)
    assert(pixel3.greyscale == 99)
  }

  test("Creating unvalid pixel") {
    assertThrows[IllegalArgumentException]{
      val pixel = new Pixel(-69, 120, 69)
    }
    assertThrows[IllegalArgumentException] {
      val pixel = new Pixel(69, -120, 69)
    }
    assertThrows[IllegalArgumentException] {
      val pixel = new Pixel(69, 120, -1)
    }
    assertThrows[IllegalArgumentException] {
      val pixel = new Pixel(69, 280, 1)
    }
  }
}
