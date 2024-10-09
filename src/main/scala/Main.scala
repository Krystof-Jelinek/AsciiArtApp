@main
def main(args: String*): Unit = {
  var parser = CommandParser()
  var tmp = parser.parse(args)
  println("wait")
}
