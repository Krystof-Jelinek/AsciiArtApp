package TransformationHandelers.Converters
import DataModels.{AsciiImage, Image}

class DefaultConverter extends ImageConverterInterface {
  override def convert(img: Image): AsciiImage = ???
}
