package IOTests.SaverTests

import DataModels.AsciiImage
import ImageSaver.FileSaver
import org.scalatest.funsuite.AnyFunSuite

import java.io.ByteArrayOutputStream
import java.nio.file.{Files, Paths}
import scala.collection.mutable.ArrayBuffer

class FileSaverTest extends AnyFunSuite{
  //no need for more tests since everything else is tested in other unit tests such as asciimmage.getString
  test("Valid path and command saves image") {
    val path = "src/test/testPictures/result.txt"
    val saver = new FileSaver(path)
    val asciiImage = AsciiImage(4, 3)
    //Test\nNext\n@@@@\n
    asciiImage.setVal(0, 0, 'T')
    asciiImage.setVal(1, 0, 'e')
    asciiImage.setVal(2, 0, 's')
    asciiImage.setVal(3, 0, 't')
    asciiImage.setVal(0, 1, 'N')
    asciiImage.setVal(1, 1, 'e')
    asciiImage.setVal(2, 1, 'x')
    asciiImage.setVal(3, 1, 't')
    asciiImage.setVal(0, 2, '@')
    asciiImage.setVal(1, 2, '@')
    asciiImage.setVal(2, 2, '@')
    asciiImage.setVal(3, 2, '@')

    assert(asciiImage.getString == "Test\nNext\n@@@@\n")

    assert(!Files.exists(Paths.get(path)))

    saver.saveImage(asciiImage)

    assert(Files.exists(Paths.get(path)))

    val fileContent = Files.readAllLines(Paths.get(path))
    assert(fileContent.get(0) == "Test")
    assert(fileContent.get(1) == "Next")
    assert(fileContent.get(2) == "@@@@")

    Files.deleteIfExists(Paths.get(path))
  }

  test("Valid path and command saves image -- empty image") {
    val path = "src/test/testPictures/result.txt"
    val saver = new FileSaver(path)
    val asciiImage = AsciiImage(4, 3)

    assert(!Files.exists(Paths.get(path)))

    saver.saveImage(asciiImage)

    assert(Files.exists(Paths.get(path)))

    val fileContent = Files.readAllLines(Paths.get(path))
    assert(fileContent.get(0) == "    ")
    assert(fileContent.get(1) == "    ")
    assert(fileContent.get(2) == "    ")

    Files.deleteIfExists(Paths.get(path))
  }
}
