import Parser.CommandParser
import org.scalatest.funsuite.AnyFunSuite

class ParserTest extends AnyFunSuite{
  test("image loading test"){
    val parser = new CommandParser()
    val res = parser.parse(Seq[String]("--image","Some/Path/somewhere/file.png"))
    assert(res.loadCommand.name == "--image")
    assert(res.loadCommand.value == "Some/Path/somewhere/file.png")
  }
}
