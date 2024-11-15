package Handlers

import Commands.TransformCommands.TransformCommand
import DataModels.{AsciiImage, PixelImage}
import ImageTransformation.Converters.{ConversionTable, ImageConverterInterface, LinearConverter}
import ImageTransformation.Filters.Filter

import scala.collection.mutable.ArrayBuffer

//before transforming any image we need to know how and what transformations to use
// thats the responsibility of this class
class TransformationHandler {
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

  //the information about how and what conversions to do is stored inside the transformcommand array
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
