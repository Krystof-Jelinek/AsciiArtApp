package TransformationHandelers.Converters
import DataModels.{AsciiImage, Image}

class NonLinearConverter(val conversionTable: ConversionTable) extends ImageConverterInterface{

  override def getTableIndex(greyscaleVal: Int): Int = {
    val range: Double = 54.toDouble / (conversionTable.length() - 2).toDouble
    if(greyscaleVal <= 200){
      return 0
    }
    val index = ((greyscaleVal - 201)/range).toInt + 1
    return index
  }

}
