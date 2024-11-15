package IOTests.LoaderTests

import DataModels.Pixel
import ImageLoader.RandomImageLoader
import org.scalatest.funsuite.AnyFunSuite

class RandomImageLoaderTest extends AnyFunSuite{
  test("random image generation generates some picture") {
    val loader = new RandomImageLoader("rngSeed")
    val image = loader.loadImage()
    assert(image.width == 228)
    assert(image.height == 346)
    assert(image.getVal(0, 0).get == Pixel(163, 97, 144))
  }

  test("random image generation generates some different picture for different seed") {
    val loader = new RandomImageLoader("rngSeed2")
    val image = loader.loadImage()
    assert(image.width == 166)
    assert(image.height == 230)
    assert(image.getVal(0, 0).get == Pixel(142, 31, 250))
  }
}
