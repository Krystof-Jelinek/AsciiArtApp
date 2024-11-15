package IOTests.LoaderTests

import ImageLoader.PngLoader
import org.scalatest.funsuite.AnyFunSuite

class PngLoaderTest extends AnyFunSuite{

  //no need for more tests since everything else is tested elsewhere
  test("Valid path and file format creates image PNG with corectly loaded pixels") {
    val loader = new PngLoader("src/test/testPictures/testpicture.png")
    val image = loader.loadImage()
    validateTestImage(image)
  }

}
