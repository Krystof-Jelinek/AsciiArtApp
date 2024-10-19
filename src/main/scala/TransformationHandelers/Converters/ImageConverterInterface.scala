package TransformationHandelers.Converters

import DataModels.{AsciiImage, Image}

trait ImageConverterInterface{
  val conversionTable: ConversionTable
  def convert(img : Image) : AsciiImage = {
    var res = new AsciiImage(img.width, img.height)
    var x = 0
    var y = 0

    while (y < img.height) {
      while (x < img.width) {
        val pixel = img.getPixel(x, y).get
        res.setChar(x, y, conversionTable(getTableIndex(pixel.greyscale)))
        x += 1
      }
      y += 1
      x = 0
    }
    return res
  }

  def getTableIndex(i: Int) : Int
}
