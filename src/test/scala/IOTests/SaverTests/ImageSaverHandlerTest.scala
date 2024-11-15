package IOTests.SaverTests

import Commands.SaverCommands.{OutputConsoleCommand, OutputFileCommand, SaveCommand}
import DataModels.AsciiImage
import Handlers.ImageSaverHandler
import ImageSaver.{FileSaver, PrintSaver}
import org.mockito.Mockito.spy
import org.scalatest.funsuite.AnyFunSuite

import java.io.ByteArrayOutputStream
import java.nio.file.{Files, Paths}
import scala.collection.mutable.ArrayBuffer

class ImageSaverHandlerTest extends AnyFunSuite{

  test("No command specified leaves the saver interface empty") {
    val mockSaver = spy(new ImageSaverHandler)
    val asciiImage = AsciiImage(4, 3)

    mockSaver.saveImage(asciiImage, ArrayBuffer.empty[SaveCommand])

    val field = classOf[ImageSaverHandler].getDeclaredField("imgSaver")
    field.setAccessible(true)
    val fieldValue = field.get(mockSaver)

    // Assert the private field value
    assert(fieldValue == None, "Private field did not match expected value")
  }

  test("command filesaver uses the saver interface set as fileSaver") {
    val mockSaver = spy(new ImageSaverHandler)
    val asciiImage = AsciiImage(4, 3)
    val path = "src/test/testPictures/result.txt"

    mockSaver.saveImage(asciiImage, ArrayBuffer[SaveCommand](new OutputFileCommand(path)))

    val field = classOf[ImageSaverHandler].getDeclaredField("imgSaver")
    field.setAccessible(true)
    val fieldValue = field.get(mockSaver)

    assert(fieldValue.isInstanceOf[Option[?]])
    val unwrapped = fieldValue.asInstanceOf[Option[?]].get
    assert(unwrapped.isInstanceOf[FileSaver], "imgSaver is not of type FileSaver")
    Files.deleteIfExists(Paths.get(path))
  }

  test("command output uses the saver interface set as PrintSaver") {
    val mockSaver = spy(new ImageSaverHandler)
    val asciiImage = AsciiImage(4, 3)

    val outputStream = new ByteArrayOutputStream()

    Console.withOut(outputStream) {
      mockSaver.saveImage(asciiImage, ArrayBuffer[SaveCommand](new OutputConsoleCommand()))
    }

    val field = classOf[ImageSaverHandler].getDeclaredField("imgSaver")
    field.setAccessible(true)
    val fieldValue = field.get(mockSaver)

    assert(fieldValue.isInstanceOf[Option[?]])
    val unwrapped = fieldValue.asInstanceOf[Option[?]].get
    assert(unwrapped.isInstanceOf[PrintSaver], "imgSaver is not of type FileSaver")
  }

}
