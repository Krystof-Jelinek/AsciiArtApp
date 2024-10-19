package IOTests

import Commands.LoaderCommands.{LoadPngImageCommand, RandomImageCommand}
import Commands.SaverCommands.{OutputConsoleCommand, OutputFileCommand}
import DataModels.StringCommandTemplate
import Parser.CommandParser
import org.scalatest.funsuite.AnyFunSuite

class ParserTest extends AnyFunSuite{
  test("image loading test"){
    val parser = new CommandParser()
    val res = parser.parse(Seq[String]("--image", "Some/Path/somewhere/file.png"))
    res.loadCommand match {
      case loadCmd: LoadPngImageCommand =>
        assert(loadCmd.path == "Some/Path/somewhere/file.png")
      case _ =>
        fail("Expected LoadPngImageCommand, but got something else")
    }
    assert(res.saveCommands.isEmpty)
    assert(res.transformCommands.isEmpty)

    val res2 = parser.parse(Seq[String]("--image-random"))
    res2.loadCommand match {
      case loadCmd: RandomImageCommand =>
      case _ =>
        fail("Expected RandomImageCommand, but got something else")
    }
    assert(res2.saveCommands.isEmpty)
    assert(res2.transformCommands.isEmpty)

  }

  test("more image commands throws error") {
    val parser = new CommandParser()
    assertThrows[IllegalArgumentException]{
      var res = parser.parse(Seq[String]("--image", "Some/Path/somewhere/file.png", "--image-random"))
    }
  }

  test("filter parsing test") {
    val parser = new CommandParser()
    var res = parser.parse(Seq[String]("--rotate", "+90", "--invert", "--scale", "0.25", "--random-new-command", "with some value", " + more value"))
    res.loadCommand match {
      case loadCmd: RandomImageCommand =>
        assert(loadCmd.seed == "")
      case _ =>
        fail("Expected RandomImageCommand, but got something else")
    }
    assert(res.saveCommands.isEmpty)
    assert(res.transformCommands.nonEmpty)

    assert(res.transformCommands(0).equals(StringCommandTemplate("--rotate", "+90")))
    assert(res.transformCommands(1).equals(StringCommandTemplate("--invert", "")))
    assert(res.transformCommands(2).equals(StringCommandTemplate("--scale", "0.25")))
    assert(res.transformCommands(3).equals(StringCommandTemplate("--random-new-command", "with some value + more value")))
  }

  test("save parsing test") {
    val parser = new CommandParser()
    var res = parser.parse(Seq[String]("--output-file", "Path/to/some/file","--image-random", "--output-console"))
    assert(res.saveCommands.nonEmpty)
    assert(res.transformCommands.isEmpty)

    res.saveCommands(0) match {
      case outputFileCommand: OutputFileCommand =>
        assert(outputFileCommand.filepath == "Path/to/some/file")
      case _ =>
        fail("Expected OutputFileCommand, but got something else")
    }
    assert(res.saveCommands(1).isInstanceOf[OutputConsoleCommand])
  }

  test("One Parser parses multiple commands"){
    val parser = new CommandParser()
    var res = parser.parse(Seq[String]("--output-file", "Path/to/some/file", "--image-random", "--output-console"))

    assert(res.saveCommands.nonEmpty)
    assert(res.transformCommands.isEmpty)

    res.saveCommands(0) match {
      case outputFileCommand: OutputFileCommand =>
        assert(outputFileCommand.filepath == "Path/to/some/file")
      case _ =>
        fail("Expected OutputFileCommand, but got something else")
    }
    assert(res.saveCommands(1).isInstanceOf[OutputConsoleCommand])

    var res2 = parser.parse(Seq[String]("--rotate", "+90", "--invert", "--scale", "0.25", "--random-new-command", "with some value", " + more value"))
    res.loadCommand match {
      case loadCmd: RandomImageCommand =>
        assert(loadCmd.seed == "")
      case _ =>
        fail("Expected RandomImageCommand, but got something else")
    }
    assert(res2.saveCommands.isEmpty)
    assert(res2.transformCommands.nonEmpty)

    assert(res2.transformCommands(0).equals(StringCommandTemplate("--rotate", "+90")))
    assert(res2.transformCommands(1).equals(StringCommandTemplate("--invert", "")))
    assert(res2.transformCommands(2).equals(StringCommandTemplate("--scale", "0.25")))
    assert(res2.transformCommands(3).equals(StringCommandTemplate("--random-new-command", "with some value + more value")))

    val res3 = parser.parse(Seq[String]("--image", "Some/Path/somewhere/file.png"))
    res3.loadCommand match {
      case loadCmd: LoadPngImageCommand =>
        assert(loadCmd.path == "Some/Path/somewhere/file.png")
      case _ =>
        fail("Expected LoadPngImageCommand, but got something else")
    }
    assert(res3.saveCommands.isEmpty)
    assert(res3.transformCommands.isEmpty)

    val res4 = parser.parse(Seq[String]("--image-random"))
    res4.loadCommand match {
      case loadCmd: RandomImageCommand =>
        assert(loadCmd.seed == "")
      case _ =>
        fail("Expected RandomImageCommand, but got something else")
    }
    assert(res4.saveCommands.isEmpty)
    assert(res4.transformCommands.isEmpty)
  }

  test("Unvalid file format throws IllegalArgumentException") {
    val parser = CommandParser()
    assertThrows[IllegalArgumentException] {
      parser.parse(Seq[String]("--image", "src/test/testPictures/penguin.txt"))
    }
  }

}
