package TransformationHandelers.Converters

class LinearConverter(conversionTable: ConversionTable) extends TableBasedImageConverter(conversionTable) {

  override def getTableIndex(greyscaleVal : Int) : Int = {
    val range : Double = 256.toDouble/conversionTable.length().toDouble
    return (greyscaleVal/range).toInt
  }
}
