package TransformationHandelers

import DataModels.{AsciiImage, Command, Image}
import TransformationHandelers.Converters.{ConversionTable, ImageConverterInterface, LinearConverter, NonLinearConverter}

import scala.collection.mutable.ArrayBuffer

class TransformationHandeler {
  def execute(img : Image, commands: ArrayBuffer[Command]) : AsciiImage= {
    var table = new ConversionTable
    var converter: ImageConverterInterface = new LinearConverter(table)
    return converter.convert(img)
  }
}
