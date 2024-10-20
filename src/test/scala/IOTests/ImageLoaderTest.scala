package IOTests

import Commands.LoaderCommands.{LoadGifImageCommand, LoadJpgImageCommand, LoadPngImageCommand, LoadRandomImageCommand}
import DataModels.Pixel
import ImageLoader.ImageLoader
import Parser.CommandParser
import org.scalatest.funsuite.AnyFunSuite

import java.io.FileNotFoundException

class ImageLoaderTest extends AnyFunSuite{

  test("Valid path and file format creates image"){
    val loader = new ImageLoader
    val command = LoadPngImageCommand("src/test/testPictures/penguin-smaller.png")
    val image = loader.loadImage(command)
    assert(image.getVal(0,0).get == Pixel(213,213,213))

    val command2 = LoadJpgImageCommand("src/test/testPictures/penguin-smaller.jpg")
    val image2 = loader.loadImage(command2)
    assert(image2.getVal(0, 0).get == Pixel(212, 212, 212))

    val command3 = LoadGifImageCommand("src/test/testPictures/penguin-smaller.gif")
    val image3 = loader.loadImage(command3)
    assert(image3.getVal(0, 0).get == Pixel(213, 213, 213))

    val command4 = LoadRandomImageCommand("rngSeed")
    val image4 = loader.loadImage(command4)
    assert(image4.getVal(0, 0).get == Pixel(163,97,144))

    val command5 = LoadRandomImageCommand("")
    val image5 = loader.loadImage(command5)

  }

  test("Testing loading of individual pictures"){
    val loader = new ImageLoader
    val command = LoadPngImageCommand("src/test/testPictures/testpicture.png")
    val image = loader.loadImage(command)

    assert(image.getVal(0, 0).get == Pixel(0, 0, 0))
    assert(image.getVal(1, 0).get == Pixel(34, 177, 76))
    assert(image.getVal(2, 0).get == Pixel(255, 126, 0))
    assert(image.getVal(0, 1).get == Pixel(255, 255, 255))
    assert(image.getVal(1, 1).get == Pixel(153, 0, 48))
    assert(image.getVal(2, 1).get == Pixel(0, 183, 239))
    assert(image.getVal(0, 2).get == Pixel(168, 230, 29))
    assert(image.getVal(1, 2).get == Pixel(111, 49, 152))
    assert(image.getVal(2, 2).get == Pixel(237, 28, 36))
  }

  test("Unvalid path throws FileNotFoundException"){
    val loader = new ImageLoader
    val command = LoadPngImageCommand("XXX@@@INVALIDPATHXXX@@@")
    assertThrows[FileNotFoundException] {
      loader.loadImage(command)
    }
  }

}
