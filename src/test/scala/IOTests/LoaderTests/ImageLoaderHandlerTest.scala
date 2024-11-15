package IOTests.LoaderTests

import Commands.LoaderCommands.{LoadGifImageCommand, LoadJpgImageCommand, LoadPngImageCommand, LoadRandomImageCommand}
import DataModels.PixelImage
import Handlers.ImageLoaderHandler
import ImageLoader.{GifLoader, JpgLoader, PngLoader, RandomImageLoader}
import org.mockito.Mockito.spy
import org.scalatest.funsuite.AnyFunSuite

import java.io.FileNotFoundException

class ImageLoaderHandlerTest extends AnyFunSuite{

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

  test("command pngLoader uses the loader interface set as PngLoader") {
    val mockSaver = spy(new ImageLoaderHandler)

    mockSaver.loadImage(new LoadPngImageCommand("src/test/testPictures/testpicture.png"))

    val field = classOf[ImageLoaderHandler].getDeclaredField("loader")
    field.setAccessible(true)
    val fieldValue = field.get(mockSaver)

    assert(fieldValue.isInstanceOf[Option[?]])
    val unwrapped = fieldValue.asInstanceOf[Option[?]].get
    assert(unwrapped.isInstanceOf[PngLoader], "loader is not of type PngLoader")
  }

  test("command gifLoader uses the loader interface set as GifLoader") {
    val mockSaver = spy(new ImageLoaderHandler)

    mockSaver.loadImage(new LoadGifImageCommand("src/test/testPictures/testpicture.gif"))

    val field = classOf[ImageLoaderHandler].getDeclaredField("loader")
    field.setAccessible(true)
    val fieldValue = field.get(mockSaver)

    assert(fieldValue.isInstanceOf[Option[?]])
    val unwrapped = fieldValue.asInstanceOf[Option[?]].get
    assert(unwrapped.isInstanceOf[GifLoader], "loader is not of type GifLoader")
  }

  test("command jpgLoader uses the loader interface set as JpgLoader") {
    val mockSaver = spy(new ImageLoaderHandler)

    mockSaver.loadImage(new LoadJpgImageCommand("src/test/testPictures/testpicture.jpg"))

    val field = classOf[ImageLoaderHandler].getDeclaredField("loader")
    field.setAccessible(true)
    val fieldValue = field.get(mockSaver)

    assert(fieldValue.isInstanceOf[Option[?]])
    val unwrapped = fieldValue.asInstanceOf[Option[?]].get
    assert(unwrapped.isInstanceOf[JpgLoader], "loader is not of type JpgLoader")
  }

  test("command randomLoader uses the loader interface set as RandomImageLoader") {
    val mockSaver = spy(new ImageLoaderHandler)

    mockSaver.loadImage(new LoadRandomImageCommand(""))

    val field = classOf[ImageLoaderHandler].getDeclaredField("loader")
    field.setAccessible(true)
    val fieldValue = field.get(mockSaver)

    assert(fieldValue.isInstanceOf[Option[?]])
    val unwrapped = fieldValue.asInstanceOf[Option[?]].get
    assert(unwrapped.isInstanceOf[RandomImageLoader], "loader is not of type RandomImageLoader")
  }

}
