import Parser.CommandParser
import org.scalatest.funsuite.AnyFunSuite
import DataModels.Command

class ParserTest extends AnyFunSuite{
  test("image loading test"){
    val parser = new CommandParser()
    val res = parser.parse(Seq[String]("--image","Some/Path/somewhere/file.png"))
    assert(res.loadCommand.name == "--image")
    assert(res.loadCommand.value == "Some/Path/somewhere/file.png")
    assert(res.saveCommands.isEmpty)
    assert(res.transformCommands.isEmpty)

    val res2 = parser.parse(Seq[String]("--image-random"))
    assert(res2.loadCommand.name == "--image-random")
    assert(res2.loadCommand.value == "")
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
    assert(res.loadCommand.name == "")
    assert(res.loadCommand.value == "")
    assert(res.saveCommands.isEmpty)
    assert(res.transformCommands.nonEmpty)

    assert(res.transformCommands(0).equals(Command("--rotate", "+90")))
    assert(res.transformCommands(1).equals(Command("--invert", "")))
    assert(res.transformCommands(2).equals(Command("--scale", "0.25")))
    assert(res.transformCommands(3).equals(Command("--random-new-command", "with some value + more value")))
  }

  test("save parsing test") {
    val parser = new CommandParser()
    var res = parser.parse(Seq[String]("--output-file", "Path/to/some/file","--image-random", "--output-console"))
    assert(res.saveCommands.nonEmpty)
    assert(res.transformCommands.isEmpty)

    assert(res.saveCommands(0) == Command("--output-file", "Path/to/some/file"))
    assert(res.saveCommands(1) == Command("--output-console", ""))
  }

}
