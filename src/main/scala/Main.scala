@main
def main(args: String*): Unit = {
  val appController = AppController()
  appController.run(args)
}
