package ConversionTests

import DataModels.{Pixel, PixelImage}
import TransformationHandelers.Converters.{ConversionTable, LinearConverter}
import org.scalatest.funsuite.AnyFunSuite

class LinearConverterTest extends AnyFunSuite{

  test("valid default test"){
    val image = new PixelImage(2,2)
    val table = new ConversionTable
    val converter = new LinearConverter(table)
    val res = converter.convert(image)

    assert(res.getString == "$$\n$$\n")

  }

  test("custom table test") {
    val image = new PixelImage(2, 2)
    val table = new ConversionTable

    table.setTable("1234")

    val converter = new LinearConverter(table)
    val res = converter.convert(image)

    assert(res.getString == "11\n11\n")

    table.setTable("4321")
    val res2 = converter.convert(image)
    assert(res2.getString == "44\n44\n")

    image.setVal(0,0,Pixel(255,255,255))
    image.setVal(1,1,Pixel(120,120,120))

    val res3 = converter.convert(image)
    assert(res3.getString == "14\n43\n")
  }

  test("small table test"){
    val image = new PixelImage(2, 2)
    val table = new ConversionTable

    table.setTable("0")
    image.setVal(0,0,Pixel(255,255,255))
    image.setVal(1,1,Pixel(120,120,120))

    val converter = new LinearConverter(table)
    val res = converter.convert(image)

    assert(res.getString == "00\n00\n")

  }
}
