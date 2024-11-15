package IOTests.LoaderTests

import ImageLoader.GifLoader
import org.scalatest.funsuite.AnyFunSuite

class GifLoaderTest extends AnyFunSuite{
  //no need for more tests since everything else is tested elsewhere
  test("Valid path and file format creates image GIF with corectly loaded pixels -- default") {
    val loader = new GifLoader("src/test/testPictures/testpicture.gif")
    val image = loader.loadImage()
    validateTestImage(image)
  }

  test("Valid path and file format creates image GIF with corectly loaded pixels -- wider") {
    val loader = new GifLoader("src/test/testPictures/testpictureWide.gif")
    val image = loader.loadImage()
    validateTestImageWide(image)
  }

  test("Valid path and file format creates image GIF with corectly loaded pixels -- taller") {
    val loader = new GifLoader("src/test/testPictures/testpictureTall.gif")
    val image = loader.loadImage()
    validateTestImageTall(image)
  }
}
