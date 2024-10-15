import org.scalatest.funsuite.AnyFunSuite


class MainTest extends AnyFunSuite {
  test("Foo"){
    val controller = new AppController
    controller.run(Seq("--image","src/pictures/penguin-smaller.png"))
  }
}
