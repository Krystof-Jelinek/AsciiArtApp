package IOTests

import DataModels.{Command, Pixel}
import ImageLoader.ImageLoader
import org.scalatest.funsuite.AnyFunSuite

import java.io.FileNotFoundException

class ImageLoaderTest extends AnyFunSuite{

  test("Valid path and file format creates image"){
    val loader = new ImageLoader
    val command = Command("--image", "src/test/testPictures/penguin-smaller.png")
    val image = loader.loadImage(command)
    image.getPixel(0,0) == Pixel(255,255,255)

    val command2 = Command("--image", "src/test/testPictures/penguin-smaller.jpg")
    val image2 = loader.loadImage(command2)
    image2.getPixel(0, 0) == Pixel(255, 255, 255)

    val command3 = Command("--image", "src/test/testPictures/penguin-smaller.gif")
    val image3 = loader.loadImage(command3)
    image3.getPixel(0, 0) == Pixel(255, 255, 255)


  }

  test("Unvalid path throws FileNotFoundException"){
    val loader = new ImageLoader
    val command = Command("--image", "XXX@@@INVALIDPATHXXX@@@")
    assertThrows[FileNotFoundException] {
      loader.loadImage(command)
    }
  }

  test("Unvalid file format throws IllegalArgumentException") {
    val loader = new ImageLoader
    val command = Command("--image", "src/test/testPictures/penguin.txt")
    assertThrows[IllegalArgumentException] {
      loader.loadImage(command)
    }
  }

}
