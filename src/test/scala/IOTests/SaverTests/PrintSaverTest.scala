package IOTests.SaverTests

import DataModels.AsciiImage
import ImageSaver.PrintSaver
import org.scalatest.funsuite.AnyFunSuite

import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets

class PrintSaverTest extends AnyFunSuite{
  //no need for more tests since everything else is tested in other unit tests such as asciimmage.getString
  test("default test"){
    val saver = new PrintSaver

    val asciiImage = AsciiImage(2, 2)
    //Te\nst\n
    asciiImage.setVal(0, 0, 'T')
    asciiImage.setVal(1, 0, 'e')
    asciiImage.setVal(0, 1, 's')
    asciiImage.setVal(1, 1, 't')

    val outputStream = new ByteArrayOutputStream()

    Console.withOut(outputStream) {
      saver.saveImage(asciiImage)
      val outputContent = outputStream.toString(StandardCharsets.UTF_8.name())
      assert(outputContent == "Te\nst\n")
    }
  }

  test("empty image test") {
    val saver = new PrintSaver

    val asciiImage = AsciiImage(2, 2)

    val outputStream = new ByteArrayOutputStream()

    Console.withOut(outputStream) {
      saver.saveImage(asciiImage)
      val outputContent = outputStream.toString(StandardCharsets.UTF_8.name())
      assert(outputContent == "  \n  \n")
    }
  }
}
