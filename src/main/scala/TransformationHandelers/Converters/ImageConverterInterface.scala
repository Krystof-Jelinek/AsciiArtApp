package TransformationHandelers.Converters

import DataModels.{AsciiImage, Image}

trait ImageConverterInterface{
  val conversionTable: ConversionTable
  def convert(img : Image) : AsciiImage = {
    var res = new AsciiImage
    var x = 0
    var y = 0

    while (y < img.height) {
      while (x < img.width) {
        val pixel = img.getPixel(x, y).get
        res.value += conversionTable(getTableIndex(pixel.greyscale))
        x += 1
      }
      res.value += '\n'
      y += 1
      x = 0
    }
    return res
  }

  def getTableIndex(i: Int) : Int
}
