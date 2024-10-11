import ImageSaver.ImageSaver
import org.scalatest.funsuite.AnyFunSuite
import DataModels.{AsciiImage, Command, Image}

import java.nio.file.{Files, Paths}
import scala.collection.mutable.ArrayBuffer

class ImageSaverTest extends AnyFunSuite{
  test("Valid path and command saves image"){
    val saver = new ImageSaver
    val asciiImage = new AsciiImage("TestImage\nNextValue\n@@@@")
    val path = "src/test/testPictures/result.txt"

    //this test if user puth argument after --output-console it should just ignore the argument
    saver.saveImage(asciiImage, ArrayBuffer[Command](Command("--output-console", path)))

    assert(!Files.exists(Paths.get(path)))

    saver.saveImage(asciiImage, ArrayBuffer[Command](Command("--output-file", path)))

    assert(Files.exists(Paths.get(path)))

    val fileContent = Files.readAllLines(Paths.get(path))
    assert(fileContent.get(0) == "TestImage")
    assert(fileContent.get(1) == "NextValue")
    assert(fileContent.get(2) == "@@@@")

    Files.deleteIfExists(Paths.get(path))
  }

  test("Invalid command throws error"){
    val saver = new ImageSaver
    val asciiImage = new AsciiImage("TestImage\nNextValue\n@@@@")
    val path = "src/test/testPictures/result.txt"

    assertThrows[IllegalArgumentException]{
      saver.saveImage(asciiImage, ArrayBuffer[Command](Command("--some-wierd-command",path)))
    }
    assert(!Files.exists(Paths.get(path)))
  }

}
