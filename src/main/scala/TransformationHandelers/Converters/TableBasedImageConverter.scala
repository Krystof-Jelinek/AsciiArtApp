package TransformationHandelers.Converters

import DataModels.{AsciiImage, PixelImage}

abstract class TableBasedImageConverter(val conversionTable: ConversionTable) extends ImageConverterInterface {

  def convert(img: PixelImage): AsciiImage = {
    var res = new AsciiImage(img.width, img.height)
    var x = 0
    var y = 0

    while (y < img.height) {
      while (x < img.width) {
        val pixel = img.getVal(x, y).get
        res.setVal(x, y, conversionTable(getTableIndex(pixel.greyscale)))
        x += 1
      }
      y += 1
      x = 0
    }
    return res
  }

  def getTableIndex(i: Int): Int
}
