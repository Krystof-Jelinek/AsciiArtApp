package Commands.LoaderCommands

import Commands.Command
import ImageLoader.ImageLoader

import java.io.{File, FileNotFoundException}

abstract class LoadCommand extends Command[ImageLoader]{
  protected def validatePathOrThrow(path : String) : Unit = {
    val file = new File(path)
    if (!file.exists()) {
      throw new FileNotFoundException(s"File not found: $path")
    }
  }
}
