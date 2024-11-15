package IOTests.LoaderTests

import ImageLoader.GifLoader
import org.scalatest.funsuite.AnyFunSuite

class GifLoaderTest extends AnyFunSuite{
  //no need for more tests since everything else is tested elsewhere
  test("Valid path and file format creates image GIF with corectly loaded pixels") {
    val loader = new GifLoader("src/test/testPictures/testpicture.gif")
    val image = loader.loadImage()
    validateTestImage(image)
  }
}
