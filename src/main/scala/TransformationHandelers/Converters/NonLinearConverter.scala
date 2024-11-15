package TransformationHandelers.Converters

class NonLinearConverter(conversionTable: ConversionTable) extends TableBasedImageConverter(conversionTable){

  override def getTableIndex(greyscaleVal: Int): Int = {
    val range: Double = 54.toDouble / (conversionTable.length() - 2).toDouble
    if(greyscaleVal <= 200){
      return 0
    }
    val index = ((greyscaleVal - 201)/range).toInt + 1
    return index
  }

}
