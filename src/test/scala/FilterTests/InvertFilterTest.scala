package FilterTests

import DataModels.{Pixel, PixelImage}
import ImageTransformation.Filters.InvertFilter
import org.scalatest.funsuite.AnyFunSuite

class InvertFilterTest extends AnyFunSuite{
  test("basic test"){
    var filter = new InvertFilter()
    var img = new PixelImage(1, 1)
    img.setVal(0, 0, Pixel(10, 10, 10))

    filter.applyFilter(img)

    assert(img.getVal(0, 0).get == Pixel(246, 246, 246))
  }

  test("255 edge value test"){
    var filter = new InvertFilter()
    var img = new PixelImage(1, 1)
    img.setVal(0, 0, Pixel(255, 255, 255))

    filter.applyFilter(img)

    assert(img.getVal(0, 0).get == Pixel(0, 0, 0))
  }

  test("0 edge value test") {
    var filter = new InvertFilter()
    var img = new PixelImage(1, 1)
    img.setVal(0, 0, Pixel(0, 0, 0))

    filter.applyFilter(img)

    assert(img.getVal(0, 0).get == Pixel(255, 255, 255))
  }

  test("apply invert twice should return same value") {
    var filter = new InvertFilter()
    var img = new PixelImage(1, 1)
    img.setVal(0, 0, Pixel(0, 0, 0))

    filter.applyFilter(img)
    filter.applyFilter(img)

    assert(img.getVal(0, 0).get == Pixel(0, 0, 0))
  }

  test("larger image test with edge values") {
    var filter = new InvertFilter()
    var img = new PixelImage(3, 3)
    img.setVal(0, 0, Pixel(10, 10, 10))
    img.setVal(0, 1, Pixel(20, 20, 20))
    img.setVal(0, 2, Pixel(255, 255, 255))

    filter.applyFilter(img)

    assert(img.getVal(0, 0).get == Pixel(246, 246, 246))
    assert(img.getVal(0, 1).get == Pixel(236, 236, 236))
    assert(img.getVal(0, 2).get == Pixel(0, 0, 0))
    assert(img.getVal(1, 0).get == Pixel(255, 255, 255))
    assert(img.getVal(1, 1).get == Pixel(255, 255, 255))
    assert(img.getVal(1, 2).get == Pixel(255, 255, 255))
    assert(img.getVal(2, 0).get == Pixel(255, 255, 255))
    assert(img.getVal(2, 1).get == Pixel(255, 255, 255))
    assert(img.getVal(2, 2).get == Pixel(255, 255, 255))
  }
}
