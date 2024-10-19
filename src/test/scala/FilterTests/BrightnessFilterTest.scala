package FilterTests

import DataModels.{Pixel, PixelImage}
import TransformationHandelers.Filters.BrightnessFilter
import org.scalatest.funsuite.AnyFunSuite

class BrightnessFilterTest extends AnyFunSuite{
  test("basic test") {
    var filter = new BrightnessFilter(10)
    var img = new PixelImage(1, 1)
    img.setVal(0, 0, Pixel(10, 10, 10))

    filter.applyFilter(img)

    //because it works with floats
    assert(img.getVal(0, 0).get == Pixel(19, 19, 19))
  }

  test("bigger image test with edge values") {
    var filter = new BrightnessFilter(10)
    var img = new PixelImage(3, 3)
    img.setVal(0, 0, Pixel(10, 10, 10))
    img.setVal(0, 1, Pixel(254, 254, 254))

    filter.applyFilter(img)

    assert(img.getVal(0, 0).get == Pixel(19, 19, 19))
    assert(img.getVal(0, 1).get == Pixel(255, 255, 255))
  }

  test("negative val test") {
    var filter = new BrightnessFilter(-5)
    var img = new PixelImage(2, 2)
    img.setVal(0, 0, Pixel(255, 255, 255))
    img.setVal(0, 1, Pixel(5, 5, 5))
    img.setVal(1, 1, Pixel(2, 2, 2))

    filter.applyFilter(img)

    assert(img.getVal(0, 0).get == Pixel(250, 250, 250))
    assert(img.getVal(0, 1).get == Pixel(0, 0, 0))
    assert(img.getVal(1, 1).get == Pixel(0, 0, 0))
  }
}
