package ConversionTests

import DataModels.{Pixel, PixelImage}
import ImageTransformation.Converters.{ConversionTable, NonLinearConverter}
import org.scalatest.funsuite.AnyFunSuite

class NonLinearConverterTest extends AnyFunSuite{
  test("valid default test") {
    val image = new PixelImage(2, 2)
    val table = new ConversionTable
    val converter = new NonLinearConverter(table)
    val res = converter.convert(image)

    assert(res.getString == "$$\n$$\n")

  }

  test("custom table test") {
    val image = new PixelImage(2, 2)
    val table = new ConversionTable

    table.setTable("1234")

    val converter = new NonLinearConverter(table)
    val res = converter.convert(image)

    assert(res.getString == "11\n11\n")

    table.setTable("4321")
    val res2 = converter.convert(image)
    assert(res2.getString == "44\n44\n")

    image.setVal(0,0,Pixel(255,255,255))
    image.setVal(1,1,Pixel(120,120,120))
    image.setVal(0,1,Pixel(200,200,200))
    image.setVal(1,0,Pixel(201,201,201))

    val res3 = converter.convert(image)
    assert(res3.getString == "13\n44\n")
  }
}
