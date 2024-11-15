package IOTests.LoaderTests

import ImageLoader.PngLoader
import org.scalatest.funsuite.AnyFunSuite

class PngLoaderTest extends AnyFunSuite{

  //no need for more tests since everything else is tested elsewhere
  test("Valid path and file format creates image PNG with corectly loaded pixels -- default") {
    val loader = new PngLoader("src/test/testPictures/testpicture.png")
    val image = loader.loadImage()
    validateTestImage(image)
  }

  test("Valid path and file format creates image PNG with corectly loaded pixels -- wider") {
    val loader = new PngLoader("src/test/testPictures/testpictureWide.png")
    val image = loader.loadImage()
    validateTestImageWide(image)
  }

  test("Valid path and file format creates image PNG with corectly loaded pixels -- taller") {
    val loader = new PngLoader("src/test/testPictures/testpictureTall.png")
    val image = loader.loadImage()
    validateTestImageTall(image)
  }

}
