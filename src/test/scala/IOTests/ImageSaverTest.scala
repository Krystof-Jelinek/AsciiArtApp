package IOTests

import DataModels.{AsciiImage, Command, Image}
import ImageSaver.ImageSaver
import org.scalatest.funsuite.AnyFunSuite

import java.io.{ByteArrayOutputStream, PrintStream}
import java.nio.file.{Files, Paths}
import scala.collection.mutable.ArrayBuffer

class ImageSaverTest extends AnyFunSuite{

  test("Valid path and command saves image"){
    val saver = new ImageSaver
    val asciiImage = AsciiImage(4,3)
    //Test\nNext\n@@@@\n
    asciiImage.setChar(0,0, 'T')
    asciiImage.setChar(1,0, 'e')
    asciiImage.setChar(2,0, 's')
    asciiImage.setChar(3,0, 't')
    asciiImage.setChar(0,1, 'N')
    asciiImage.setChar(1,1, 'e')
    asciiImage.setChar(2,1, 'x')
    asciiImage.setChar(3,1, 't')
    asciiImage.setChar(0, 2, '@')
    asciiImage.setChar(1, 2, '@')
    asciiImage.setChar(2, 2, '@')
    asciiImage.setChar(3, 2, '@')

    assert(asciiImage.getString == "Test\nNext\n@@@@\n")
    val path = "src/test/testPictures/result.txt"

    val outputStream = new ByteArrayOutputStream()
    Console.withOut(outputStream) {
    //this test if user puth argument after --output-console it should just ignore the argument
      saver.saveImage(asciiImage, ArrayBuffer[Command](Command("--output-console", path)))
    }


    assert(!Files.exists(Paths.get(path)))

    saver.saveImage(asciiImage, ArrayBuffer[Command](Command("--output-file", path)))

    assert(Files.exists(Paths.get(path)))

    val fileContent = Files.readAllLines(Paths.get(path))
    assert(fileContent.get(0) == "Test")
    assert(fileContent.get(1) == "Next")
    assert(fileContent.get(2) == "@@@@")

    Files.deleteIfExists(Paths.get(path))
  }

  test("Invalid command throws error"){
    val saver = new ImageSaver
    val asciiImage = AsciiImage(4,3)
    //Test\nNext\n@@@@\n
    asciiImage.setChar(0,0, 'T')
    asciiImage.setChar(1,0, 'e')
    asciiImage.setChar(2,0, 's')
    asciiImage.setChar(3,0, 't')
    asciiImage.setChar(0,1, 'N')
    asciiImage.setChar(1,1, 'e')
    asciiImage.setChar(2,1, 'x')
    asciiImage.setChar(3,1, 't')
    asciiImage.setChar(0, 2, '@')
    asciiImage.setChar(1, 2, '@')
    asciiImage.setChar(2, 2, '@')
    asciiImage.setChar(3, 2, '@')
    val path = "src/test/testPictures/result.txt"

    assertThrows[IllegalArgumentException]{
      saver.saveImage(asciiImage, ArrayBuffer[Command](Command("--some-wierd-command",path)))
    }
    assert(!Files.exists(Paths.get(path)))
  }

}
