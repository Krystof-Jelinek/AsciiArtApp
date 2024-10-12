package ConversionTests

import DataModels.{Image, Pixel}
import TransformationHandelers.Converters.{ConversionTable, LinearConverter}
import org.scalatest.funsuite.AnyFunSuite

class LinearConverterTest extends AnyFunSuite{

  test("valid default test"){
    val image = new Image(2,2)
    val table = new ConversionTable
    val converter = new LinearConverter(table)
    val res = converter.convert(image)

    assert(res.value == "$$\n$$\n")

  }

  test("custom table test") {
    val image = new Image(2, 2)
    val table = new ConversionTable

    table.setTable("1234")

    val converter = new LinearConverter(table)
    val res = converter.convert(image)

    assert(res.value == "11\n11\n")

    table.setTable("4321")
    val res2 = converter.convert(image)
    assert(res2.value == "44\n44\n")

    image.setPixel(0,0,Pixel(255,255,255))
    image.setPixel(1,1,Pixel(120,120,120))

    val res3 = converter.convert(image)
    assert(res3.value == "14\n43\n")
  }
}
