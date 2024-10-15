package TransformationHandelers

import DataModels.{AsciiImage, Command, Image}
import TransformationHandelers.Converters.{ConversionTable, ImageConverterInterface, LinearConverter, NonLinearConverter}
import Filters.{BrightnessFilter, Filter, InvertFilter, ScaleFilter}

import scala.collection.mutable.ArrayBuffer

class TransformationHandeler {
  var table = new ConversionTable
  def execute(img : Image, commands: ArrayBuffer[Command]) : AsciiImage= {
    var converter: ImageConverterInterface = new LinearConverter(table)
    val filterArray = ArrayBuffer.empty[Filter]

    //process every command
    for(command : Command <- commands){
      command.name match {
        case "--table-non-linear" => converter = new NonLinearConverter(table)
        case "--table" => table.setPredifinedTable(command.value.toInt)
        case "--custom-table" => table.setTable(command.value)
        case "--invert" => filterArray.addOne(createFilter(command.name, command.value))
        case "--brightness" => filterArray.addOne(createFilter(command.name, command.value))
        case "--scale" => filterArray.addOne(createFilter(command.name, command.value))
        case _ => throw IllegalArgumentException("Invalid filter or table name")
      }
    }

    for(filter : Filter <- filterArray){
      filter.applyFilter(img)
    }

    return converter.convert(img)
  }

  def createFilter(name : String, value : String) : Filter = {
    name match {
      case "--invert" => new InvertFilter()
      case "--brightness" => new BrightnessFilter(value.toInt)
      case "--scale" => new ScaleFilter(value.toFloat)
      case _ => throw IllegalArgumentException("Invalid filter name")
    }
  }
}
