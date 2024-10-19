package FilterTests

import DataModels.{Pixel, PixelImage}
import TransformationHandelers.Filters.ScaleFilter
import org.scalatest.funsuite.AnyFunSuite

class ScaleFilterTest extends AnyFunSuite{
  test("test for scale 1"){
    var filter = ScaleFilter("1".toFloat)
    var img = PixelImage(1, 1)
    img.setVal(0, 0, Pixel(10, 10, 10))

    filter.applyFilter(img)

    assert(img.getVal(0, 0).get == Pixel(10, 10, 10))
    assert(img.width == 1)
    assert(img.height == 1)
  }

  test("basic enlargment test"){
    var filter = ScaleFilter("4".toFloat)
    var img = PixelImage(1,1)
    img.setVal(0,0,Pixel(10,10,10))

    filter.applyFilter(img)

    assert(img.getVal(0,0).get == Pixel(10,10,10))
    assert(img.getVal(0,1).get == Pixel(10,10,10))
    assert(img.getVal(1,0).get == Pixel(10,10,10))
    assert(img.getVal(1,1).get == Pixel(10,10,10))

    img.resize(1,1)
    assert(img.getVal(0,0).get == Pixel(10,10,10))

  }

  test("larger enlargment test from courses") {
    var filter = ScaleFilter("4".toFloat)
    var img = PixelImage(2, 2)
    img.setVal(0, 0, Pixel(1, 1, 1))
    img.setVal(1, 0, Pixel(2, 2, 2))
    img.setVal(0, 1, Pixel(3, 3, 3))
    img.setVal(1, 1, Pixel(4, 4, 4))

    filter.applyFilter(img)

    assert(img.getVal(0, 0).get == Pixel(1, 1, 1))
    assert(img.getVal(1, 0).get == Pixel(1, 1, 1))
    assert(img.getVal(2, 0).get == Pixel(2, 2, 2))
    assert(img.getVal(3, 0).get == Pixel(2, 2, 2))
    assert(img.getVal(0, 1).get == Pixel(1, 1, 1))
    assert(img.getVal(1, 1).get == Pixel(1, 1, 1))
    assert(img.getVal(2, 1).get == Pixel(2, 2, 2))
    assert(img.getVal(3, 1).get == Pixel(2, 2, 2))
    assert(img.getVal(0, 2).get == Pixel(3, 3, 3))
    assert(img.getVal(1, 2).get == Pixel(3, 3, 3))
    assert(img.getVal(2, 2).get == Pixel(4, 4, 4))
    assert(img.getVal(3, 2).get == Pixel(4, 4, 4))
    assert(img.getVal(0, 3).get == Pixel(3, 3, 3))
    assert(img.getVal(1, 3).get == Pixel(3, 3, 3))
    assert(img.getVal(2, 3).get == Pixel(4, 4, 4))
    assert(img.getVal(3, 3).get == Pixel(4, 4, 4))

    img.resize(1, 1)
    assert(img.getVal(0, 0).get == Pixel(1, 1, 1))

  }

  test("basic shrinking test"){
    var filter = ScaleFilter("0.25".toFloat)
    var img = PixelImage(2, 2)
    img.setVal(0, 0, Pixel(1, 1, 1))
    img.setVal(1, 0, Pixel(2, 2, 2))
    img.setVal(0, 1, Pixel(3, 3, 3))
    img.setVal(1, 1, Pixel(4, 4, 4))

    filter.applyFilter(img)
    assert(img.getVal(0, 0).get == Pixel(1, 1, 1))
    assert(img.width == 1)
    assert(img.height == 1)
  }

  test("advanced even shrinking from courses"){
    var filter = ScaleFilter("0.25".toFloat)
    var img = PixelImage(4, 4)

    img.setVal(0, 0, Pixel(1, 1, 1))
    img.setVal(1, 0, Pixel(1, 1, 1))
    img.setVal(2, 0, Pixel(2, 2, 2))
    img.setVal(3, 0, Pixel(2, 2, 2))
    img.setVal(0, 1, Pixel(1, 1, 1))
    img.setVal(1, 1, Pixel(1, 1, 1))
    img.setVal(2, 1, Pixel(2, 2, 2))
    img.setVal(3, 1, Pixel(2, 2, 2))
    img.setVal(0, 2, Pixel(3, 3, 3))
    img.setVal(1, 2, Pixel(3, 3, 3))
    img.setVal(2, 2, Pixel(4, 4, 4))
    img.setVal(3, 2, Pixel(4, 4, 4))
    img.setVal(0, 3, Pixel(3, 3, 3))
    img.setVal(1, 3, Pixel(3, 3, 3))
    img.setVal(2, 3, Pixel(4, 4, 4))
    img.setVal(3, 3, Pixel(4, 4, 4))

    filter.applyFilter(img)

    assert(img.getVal(0, 0).get == Pixel(1, 1, 1))
    assert(img.getVal(1, 0).get == Pixel(2, 2, 2))
    assert(img.getVal(0, 1).get == Pixel(3, 3, 3))
    assert(img.getVal(1, 1).get == Pixel(4, 4, 4))

  }

  test("odd number of pixels test"){
    var filter = ScaleFilter("0.25".toFloat)
    var img = PixelImage(3, 3)

    img.setVal(0, 0, Pixel(1, 1, 1))
    img.setVal(0, 2, Pixel(2, 2, 2))
    img.setVal(2, 0, Pixel(3, 3, 3))
    img.setVal(2, 2, Pixel(4, 4, 4))

    filter.applyFilter(img)

    assert(img.getVal(0, 0).get == Pixel(1, 1, 1))
    assert(img.getVal(0, 1).get == Pixel(2, 2, 2))
    assert(img.getVal(1, 0).get == Pixel(3, 3, 3))
    assert(img.getVal(1, 1).get == Pixel(4, 4, 4))
  }

  test("too small image") {
    var filter = ScaleFilter("0.25".toFloat)
    var img = PixelImage(1, 10)

    assertThrows[IllegalArgumentException]{
      filter.applyFilter(img)
    }
  }

  test("illegal argument for filter"){
    var filter = ScaleFilter("0.420".toFloat)
    var img = PixelImage(10, 10)

    assertThrows[IllegalArgumentException] {
      filter.applyFilter(img)
    }
  }



}
