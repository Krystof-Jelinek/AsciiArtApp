package TransformationHandelers.Converters
import DataModels.{AsciiImage, Image, Pixel}

class LinearConverter(val conversionTable: ConversionTable) extends ImageConverterInterface {

  override def getTableIndex(greyscaleVal : Int) : Int = {
    val range : Double = 256.toDouble/conversionTable.length().toDouble
    return (greyscaleVal/range).toInt
  }
}
