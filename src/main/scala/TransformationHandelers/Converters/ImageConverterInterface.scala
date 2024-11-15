package TransformationHandelers.Converters

import DataModels.{AsciiImage, PixelImage}

trait ImageConverterInterface{
  def convert(img : PixelImage) : AsciiImage
}
