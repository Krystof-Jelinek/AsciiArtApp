package IOTests

import Commands.LoaderCommands.{LoadGifImageCommand, LoadJpgImageCommand, LoadPngImageCommand, LoadRandomImageCommand}
import Commands.SaverCommands.{OutputConsoleCommand, OutputFileCommand}
import Commands.StringCommandTemplate
import Commands.TransformCommands.{BrightnessFilterCommand, InvertFilterCommand, NonLinearTableCommand, ScaleFilterCommand, SetCustomTableCommand, SetPredefinedTableCommand}
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
      case loadCmd: LoadRandomImageCommand =>
      case _ =>
        fail("Expected LoadRandomImageCommand, but got something else")
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
    var res = parser.parse(Seq[String]("--invert", "--scale", "0.25", "--brightness", "20"))
    res.loadCommand match {
      case loadCmd: LoadRandomImageCommand =>
        assert(loadCmd.seed == "")
      case _ =>
        fail("Expected LoadRandomImageCommand, but got something else")
    }
    assert(res.saveCommands.isEmpty)
    assert(res.transformCommands.nonEmpty)

    assert(res.transformCommands(0).isInstanceOf[InvertFilterCommand])
    res.transformCommands(1) match {
      case cmd: ScaleFilterCommand =>
        assert(cmd.scale == 0.25)
      case _ =>
        fail("Expected ScaleFilterCommand, but got something else")
    }
    res.transformCommands(2) match {
      case cmd: BrightnessFilterCommand =>
        assert(cmd.intensity == 20)
      case _ =>
        fail("Expected BrightnessFilterCommand, but got something else")
    }
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

    var res2 = parser.parse(Seq[String]("--invert", "--scale", "0.25", "--brightness", "20"))
    res.loadCommand match {
      case loadCmd: LoadRandomImageCommand =>
        assert(loadCmd.seed == "")
      case _ =>
        fail("Expected LoadRandomImageCommand, but got something else")
    }
    assert(res2.saveCommands.isEmpty)
    assert(res2.transformCommands.nonEmpty)

    assert(res2.transformCommands(0).isInstanceOf[InvertFilterCommand])
    res2.transformCommands(1) match {
      case cmd: ScaleFilterCommand =>
        assert(cmd.scale == 0.25)
      case _ =>
        fail("Expected ScaleFilterCommand, but got something else")
    }
    res2.transformCommands(2) match {
      case cmd: BrightnessFilterCommand =>
        assert(cmd.intensity == 20)
      case _ =>
        fail("Expected BrightnessFilterCommand, but got something else")
    }

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
      case loadCmd: LoadRandomImageCommand =>
        assert(loadCmd.seed == "")
      case _ =>
        fail("Expected LoadRandomImageCommand, but got something else")
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

  test("Invalid --output command"){
    val parser = CommandParser()
    assertThrows[IllegalArgumentException] {
      parser.parse(Seq[String]("--output-random", "HUHARGUMENT"))
    }
  }

  test("jpg image loading"){
    val parser = new CommandParser()
    val res = parser.parse(Seq[String]("--image", "Some/Path/somewhere/file.jpg"))
    res.loadCommand match {
      case loadCmd: LoadJpgImageCommand =>
        assert(loadCmd.path == "Some/Path/somewhere/file.jpg")
      case _ =>
        fail("Expected LoadJpgImageCommand, but got something else")
    }
    assert(res.saveCommands.isEmpty)
    assert(res.transformCommands.isEmpty)
  }

  test("gif image loading") {
    val parser = new CommandParser()
    val res = parser.parse(Seq[String]("--image", "Some/Path/somewhere/file.gif"))
    res.loadCommand match {
      case loadCmd: LoadGifImageCommand =>
        assert(loadCmd.path == "Some/Path/somewhere/file.gif")
      case _ =>
        fail("Expected LoadGifImageCommand, but got something else")
    }
    assert(res.saveCommands.isEmpty)
    assert(res.transformCommands.isEmpty)
  }

  test("Invalid --image command") {
    val parser = CommandParser()
    assertThrows[IllegalArgumentException] {
      parser.parse(Seq[String]("--image-IMWRONG", "HUHARGUMENT"))
    }
  }

  test("non linear table command gets created"){
    val parser = new CommandParser()
    val res = parser.parse(Seq[String]("--image", "Some/Path/somewhere/file.gif", "--table-non-linear"))
    res.transformCommands(0) match {
      case cmd: NonLinearTableCommand =>
      case _ =>
        fail("Expected NonLinearTableCommand, but got something else")
    }
  }

  test("predifined table command gets created") {
    val parser = new CommandParser()
    val res = parser.parse(Seq[String]("--image", "Some/Path/somewhere/file.gif", "--table", "0"))
    res.transformCommands(0) match {
      case cmd: SetPredefinedTableCommand =>
        assert(cmd.index == 0)
      case _ =>
        fail("Expected NonLinearTableCommand, but got something else")
    }
  }

  test("predifined table command fails with nonnumberargument") {
    val parser = new CommandParser()
    assertThrows[IllegalArgumentException]{
      val res = parser.parse(Seq[String]("--image", "Some/Path/somewhere/file.gif", "--table", "NaN"))
    }
  }

  test("custom table command gets created") {
    val parser = new CommandParser()
    val res = parser.parse(Seq[String]("--image", "Some/Path/somewhere/file.gif", "--custom-table", "abcedf"))
    res.transformCommands(0) match {
      case cmd: SetCustomTableCommand =>
        assert(cmd.table == "abcedf")
      case _ =>
        fail("Expected SetCustomTableCommand, but got something else")
    }
  }

  test("brightness command fails with nonnumberargument") {
    val parser = new CommandParser()
    assertThrows[IllegalArgumentException] {
      val res = parser.parse(Seq[String]("--image", "Some/Path/somewhere/file.gif", "--brightness", "Wrong"))
    }
  }

  test("scale command fails with nonnumberargument") {
    val parser = new CommandParser()
    assertThrows[IllegalArgumentException] {
      val res = parser.parse(Seq[String]("--image", "Some/Path/somewhere/file.gif", "--scale", "Mistake"))
    }
  }

}
