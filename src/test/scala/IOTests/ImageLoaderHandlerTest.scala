package IOTests

import Commands.LoaderCommands.{LoadGifImageCommand, LoadJpgImageCommand, LoadPngImageCommand, LoadRandomImageCommand}
import DataModels.{Pixel, PixelImage}
import Handlers.ImageLoaderHandler
import Parser.CommandParser
import org.scalatest.funsuite.AnyFunSuite

import java.io.FileNotFoundException

class ImageLoaderHandlerTest extends AnyFunSuite{

  //compares the loaded picture directly to the file for png and gif since they have the same colors
  def validateTestImage(image: PixelImage): Unit = {
    assert(image.width == 3)
    assert(image.height == 3)
    assert(image.getVal(0, 0).get == Pixel(0,0,0), s"Pixel did not match expected value")
    assert(image.getVal(1, 0).get == Pixel(34,177,76), s"Pixel did not match expected value")
    assert(image.getVal(2, 0).get == Pixel(255,126,0), s"Pixel did not match expected value")
    assert(image.getVal(0, 1).get == Pixel(255,255,255), s"Pixel did not match expected value")
    assert(image.getVal(1, 1).get == Pixel(153,0,48), s"Pixel did not match expected value")
    assert(image.getVal(2, 1).get == Pixel(0,183,239), s"Pixel did not match expected value")
    assert(image.getVal(0, 2).get == Pixel(168,230,29), s"Pixel did not match expected value")
    assert(image.getVal(1, 2).get == Pixel(111,49,152), s"Pixel did not match expected value")
    assert(image.getVal(2, 2).get == Pixel(237,28,36), s"Pixel did not match expected value")
  }

  test("Valid path and file format creates image PNG with corectly loaded pixels") {
    val loader = new ImageLoaderHandler
    val command = LoadPngImageCommand("src/test/testPictures/testpicture.png")
    val image = loader.loadImage(command)
    validateTestImage(image)
  }

  test("Valid path and file format creates image GIF with corectly loaded pixels") {
    val loader = new ImageLoaderHandler
    val command = LoadPngImageCommand("src/test/testPictures/testpicture.gif")
    val image = loader.loadImage(command)
    validateTestImage(image)
  }

  test("Valid path and file format creates image JPG") {
    val loader = new ImageLoaderHandler
    val command = LoadPngImageCommand("src/test/testPictures/testpicture.jpg")
    val image = loader.loadImage(command)
    assert(image.width == 3)
    assert(image.height == 3)
    assert(image.getVal(0, 0).get == Pixel(0,0,11), s"Pixel did not match expected value")
  }

  test("random image generation generates some picture"){
    val loader = new ImageLoaderHandler
    val command = LoadRandomImageCommand("rngSeed")
    val image = loader.loadImage(command)
    assert(image.width == 228)
    assert(image.height == 346)
    assert(image.getVal(0, 0).get == Pixel(163,97,144))
  }

  test("Testing loading of individual pixels of jpg format"){
    val loader = new ImageLoaderHandler
    val command = LoadPngImageCommand("src/test/testPictures/testpicture.jpg")
    val image = loader.loadImage(command)

    //jpg has different colors then png/gif so it has to be done separately
    assert(image.width == 3)
    assert(image.height == 3)
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

  test("Unvalid path throws FileNotFoundException for pngCommand"){
    val loader = new ImageLoaderHandler
    val command = LoadPngImageCommand("XXX@@@INVALIDPATHXXX@@@")
    assertThrows[FileNotFoundException] {
      loader.loadImage(command)
    }
  }

  test("Unvalid path throws FileNotFoundException for jpgCommand") {
    val loader = new ImageLoaderHandler
    val command = LoadJpgImageCommand("XXX@@@INVALIDPATHXXX@@@")
    assertThrows[FileNotFoundException] {
      loader.loadImage(command)
    }
  }

  test("Unvalid path throws FileNotFoundException for GifCommand") {
    val loader = new ImageLoaderHandler
    val command = LoadGifImageCommand("XXX@@@INVALIDPATHXXX@@@")
    assertThrows[FileNotFoundException] {
      loader.loadImage(command)
    }
  }

}
