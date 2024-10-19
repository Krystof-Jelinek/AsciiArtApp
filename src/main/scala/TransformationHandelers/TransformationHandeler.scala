package TransformationHandelers

import Commands.TransformCommands.TransformCommand
import DataModels.{AsciiImage, PixelImage}
import TransformationHandelers.Converters.{ConversionTable, ImageConverterInterface, LinearConverter}
import Filters.Filter

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


  def execute(img : PixelImage, commands: ArrayBuffer[TransformCommand]) : AsciiImage= {
    //process every command
    for(command : TransformCommand <- commands){
      command.applyCommand(this)
    }

    for(filter : Filter <- filterArray){
      filter.applyFilter(img)
    }

    return imgConverter.convert(img)
  }
}
