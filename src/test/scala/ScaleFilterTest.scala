import org.scalatest.funsuite.AnyFunSuite
import TransformationHandelers.Filters.ScaleFilter
import DataModels.{Image, Pixel}

class ScaleFilterTest extends AnyFunSuite{
  test("basic enlargment test"){
    var filter = ScaleFilter("4".toFloat)
    var img = Image(1,1)
    img.setPixel(0,0,Pixel(10,10,10))

    filter.applyFilter(img)

    assert(img.getPixel(0,0).get == Pixel(10,10,10))
    assert(img.getPixel(0,1).get == Pixel(10,10,10))
    assert(img.getPixel(1,0).get == Pixel(10,10,10))
    assert(img.getPixel(1,1).get == Pixel(10,10,10))

    img.resize(1,1)
    assert(img.getPixel(0,0).get == Pixel(10,10,10))

  }

  test("larger enlargment test from courses") {
    var filter = ScaleFilter("4".toFloat)
    var img = Image(2, 2)
    img.setPixel(0, 0, Pixel(1, 1, 1))
    img.setPixel(1, 0, Pixel(2, 2, 2))
    img.setPixel(0, 1, Pixel(3, 3, 3))
    img.setPixel(1, 1, Pixel(4, 4, 4))

    filter.applyFilter(img)

    assert(img.getPixel(0, 0).get == Pixel(1, 1, 1))
    assert(img.getPixel(1, 0).get == Pixel(1, 1, 1))
    assert(img.getPixel(2, 0).get == Pixel(2, 2, 2))
    assert(img.getPixel(3, 0).get == Pixel(2, 2, 2))
    assert(img.getPixel(0, 1).get == Pixel(1, 1, 1))
    assert(img.getPixel(1, 1).get == Pixel(1, 1, 1))
    assert(img.getPixel(2, 1).get == Pixel(2, 2, 2))
    assert(img.getPixel(3, 1).get == Pixel(2, 2, 2))
    assert(img.getPixel(0, 2).get == Pixel(3, 3, 3))
    assert(img.getPixel(1, 2).get == Pixel(3, 3, 3))
    assert(img.getPixel(2, 2).get == Pixel(4, 4, 4))
    assert(img.getPixel(3, 2).get == Pixel(4, 4, 4))
    assert(img.getPixel(0, 3).get == Pixel(3, 3, 3))
    assert(img.getPixel(1, 3).get == Pixel(3, 3, 3))
    assert(img.getPixel(2, 3).get == Pixel(4, 4, 4))
    assert(img.getPixel(3, 3).get == Pixel(4, 4, 4))

    img.resize(1, 1)
    assert(img.getPixel(0, 0).get == Pixel(1, 1, 1))

  }



}
