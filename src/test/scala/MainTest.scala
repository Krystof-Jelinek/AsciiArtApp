import Commands.SaverCommands.SaveCommand
import org.scalatest.funsuite.AnyFunSuite

import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets


class MainTest extends AnyFunSuite {
  test("Foo"){
    val controller = new AppController
    controller.run(Seq("--image","src/pictures/penguin-smaller.png", "--table-non-linear", "--custom-table", "aaaa", "--invert", "--brightness" , "10"))

    main("--image","src/pictures/penguin-smaller.png", "--scale", "0.25", "--table", "1")
  }

  test("error is caught and printed") {
    var controller = new AppController()
    val outputStream = new ByteArrayOutputStream()

    Console.withOut(outputStream) {
      controller.run(Seq("--wrong-command","--image", "src/pictures/penguin-smaller.png"))
      val outputContent = outputStream.toString(StandardCharsets.UTF_8.name())
      assert(outputContent == "\n***********************************************************************" +
        "\nSomething went wrong: \n\nThis command : --wrong-command is not supported\n\n" +
        "***********************************************************************\n\n"
      )
    }
  }
}
