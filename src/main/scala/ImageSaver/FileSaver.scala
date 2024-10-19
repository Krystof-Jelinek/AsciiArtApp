package ImageSaver
import DataModels.AsciiImage

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}

class FileSaver(path : String) extends ImageSaverInterface {
  override def saveImage(img: AsciiImage): Unit = {
    Files.write(Paths.get(path), img.getString.getBytes(StandardCharsets.UTF_8))
  }
}
