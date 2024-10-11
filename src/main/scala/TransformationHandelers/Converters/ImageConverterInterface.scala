package TransformationHandelers.Converters

import DataModels.{AsciiImage, Image}

trait ImageConverterInterface {
  def convert(img : Image) : AsciiImage
}
