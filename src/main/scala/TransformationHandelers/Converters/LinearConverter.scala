package TransformationHandelers.Converters
import DataModels.{AsciiImage, Image, Pixel}

class LinearConverter(conversionTable: ConversionTable) extends ImageConverterInterface(conversionTable) {

  override def getTableIndex(greyscaleVal : Int) : Int = {
    val range : Double = 256.toDouble/conversionTable.length().toDouble
    return (greyscaleVal/range).toInt
  }
}
