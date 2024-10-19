package TransformationHandelers

import DataModels.{AsciiImage, Command, PixelImage}
import TransformationHandelers.Converters.{ConversionTable, ImageConverterInterface, LinearConverter, NonLinearConverter}
import Filters.{BrightnessFilter, Filter, InvertFilter, ScaleFilter}

import scala.collection.mutable.ArrayBuffer

class TransformationHandeler {
  var table: ConversionTable = new ConversionTable
  private var imgConverter : ImageConverterInterface = new LinearConverter(table)
  private val filterArray = ArrayBuffer.empty[Filter]

  def setImageConverterInterface(in : ImageConverterInterface): Unit = {
    imgConverter = in
  }

  def addFilter(in : Filter): Unit = {
    filterArray.addOne(in)
  }

  def accessTable() : ConversionTable = {
    table
  }


  def execute(img : PixelImage, commands: ArrayBuffer[Command]) : AsciiImage= {
    var converter: ImageConverterInterface = new LinearConverter(table)

    //process every command
    for(command : Command <- commands){
      command.name match {
        case "--table-non-linear" => converter = new NonLinearConverter(table)
        case "--table" => table.setPredifinedTable(command.value.toInt)
        case "--custom-table" => table.setTable(command.value)
        case "--invert" => filterArray.addOne(createFilter(command))
        case "--brightness" => filterArray.addOne(createFilter(command))
        case "--scale" => filterArray.addOne(createFilter(command))
        case _ => throw IllegalArgumentException("Invalid filter or table name")
      }
    }

    for(filter : Filter <- filterArray){
      filter.applyFilter(img)
    }

    return converter.convert(img)
  }

  private def createFilter(filter: Command) : Filter = {
    filter.name match {
      case "--invert" => new InvertFilter()
      case "--brightness" => new BrightnessFilter(filter.value.toInt)
      case "--scale" => new ScaleFilter(filter.value.toFloat)
      case _ => throw IllegalArgumentException("Invalid filter name")
    }
  }
}
