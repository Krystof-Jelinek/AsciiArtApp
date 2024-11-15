package IOTests.LoaderTests

import DataModels.Pixel
import ImageLoader.JpgLoader
import org.scalatest.funsuite.AnyFunSuite

class JpgLoaderTest extends AnyFunSuite{
  //no need for more tests since everything else is tested elsewhere
  test("Valid path and file format creates image JPG with corectly loaded pixels") {
    val loader = new JpgLoader("src/test/testPictures/testpicture.jpg")
    val image = loader.loadImage()

    //jpg has different colors then png/gif so it has to be done separately
    assert(image.getVal(0, 0).get == Pixel(0, 0, 11), s"Pixel did not match expected value")
    assert(image.getVal(1, 0).get == Pixel(38, 178, 73), s"Pixel did not match expected value")
    assert(image.getVal(2, 0).get == Pixel(255, 124, 9), s"Pixel did not match expected value")
    assert(image.getVal(0, 1).get == Pixel(255, 255, 243), s"Pixel did not match expected value")
    assert(image.getVal(1, 1).get == Pixel(148, 0, 47), s"Pixel did not match expected value")
    assert(image.getVal(2, 1).get == Pixel(0, 189, 244), s"Pixel did not match expected value")
    assert(image.getVal(0, 2).get == Pixel(174, 227, 21), s"Pixel did not match expected value")
    assert(image.getVal(1, 2).get == Pixel(103, 53, 152), s"Pixel did not match expected value")
    assert(image.getVal(2, 2).get == Pixel(247, 25, 38), s"Pixel did not match expected value")
  }
}
