package TransformationHandelers

import DataModels.{AsciiImage, Command, Image}
import TransformationHandelers.Converters.{ConversionTable, ImageConverterInterface, LinearConverter, NonLinearConverter}
import Filters.{BrightnessFilter, Filter, InvertFilter, ScaleFilter}

import scala.collection.mutable.ArrayBuffer

class TransformationHandeler {
  def execute(img : Image, commands: ArrayBuffer[Command]) : AsciiImage= {
    var table = new ConversionTable
    var converter: ImageConverterInterface = new LinearConverter(table)
    val filterArray = ArrayBuffer.empty[Filter]

    //process every command
    for(command : Command <- commands){
      command.name match {
        case "--table-non-linear" => converter = new NonLinearConverter(table)
        case "--invert" => filterArray.addOne(InvertFilter())
        case "--brightness" => filterArray.addOne(BrightnessFilter(command.value.toInt))
        case "--scale" => filterArray.addOne(ScaleFilter(command.value.toFloat))
        case _ => throw IllegalArgumentException("Invalid filter or table argument")
      }
    }

    for(filter : Filter <- filterArray){
      filter.applyFilter(img)
    }

    return converter.convert(img)
  }
}
