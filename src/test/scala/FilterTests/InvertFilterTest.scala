package FilterTests

import DataModels.{Image, Pixel}
import TransformationHandelers.Filters.InvertFilter
import org.scalatest.funsuite.AnyFunSuite

class InvertFilterTest extends AnyFunSuite{
  test("basic test"){
    var filter = new InvertFilter()
    var img = new Image(1, 1)
    img.setPixel(0, 0, Pixel(10, 10, 10))

    filter.applyFilter(img)

    assert(img.getPixel(0, 0).get == Pixel(246, 246, 246))
  }

  test("larger image test") {
    var filter = new InvertFilter()
    var img = new Image(3, 3)
    img.setPixel(0, 0, Pixel(10, 10, 10))
    img.setPixel(0, 1, Pixel(20, 20, 20))
    img.setPixel(0, 2, Pixel(255, 255, 255))

    filter.applyFilter(img)

    assert(img.getPixel(0, 0).get == Pixel(246, 246, 246))
    assert(img.getPixel(0, 1).get == Pixel(236, 236, 236))
    assert(img.getPixel(0, 2).get == Pixel(0, 0, 0))
    assert(img.getPixel(1, 0).get == Pixel(255, 255, 255))
    assert(img.getPixel(1, 1).get == Pixel(255, 255, 255))
    assert(img.getPixel(1, 2).get == Pixel(255, 255, 255))
    assert(img.getPixel(2, 0).get == Pixel(255, 255, 255))
    assert(img.getPixel(2, 1).get == Pixel(255, 255, 255))
    assert(img.getPixel(2, 2).get == Pixel(255, 255, 255))
  }
}
