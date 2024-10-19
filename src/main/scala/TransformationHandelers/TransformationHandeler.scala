package TransformationHandelers

import DataModels.{AsciiImage, Command, Image, PixelImage}
import TransformationHandelers.Converters.{ConversionTable, ImageConverterInterface, LinearConverter, NonLinearConverter}
import Filters.{BrightnessFilter, Filter, InvertFilter, ScaleFilter}

import scala.collection.mutable.ArrayBuffer

class TransformationHandeler {
  var table = new ConversionTable
  def execute(img : PixelImage, commands: ArrayBuffer[Command]) : AsciiImage= {
    var converter: ImageConverterInterface = new LinearConverter(table)
    val filterArray = ArrayBuffer.empty[Filter]

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
