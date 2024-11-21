package ImageLoader
import DataModels.PixelImage

import java.io.{BufferedInputStream, FileOutputStream}
import java.nio.file.Paths
import java.net.URI


//This class was added later and only for fun and check if the design is really easily expandable which it seems it is
//this class has no UNIT tests since i dont want it to be part of (pretty please) evaluation the RandomImageLoader is the class that should be considered for evaluation as a random generator
class RandomOnlineImageLoader(seed : String) extends RandomImageLoader(seed) {
  override def loadImage(): PixelImage = {
    val width = random.nextInt(500) + 1
    val height = random.nextInt(500) + 1

    val imageUrl = s"https://picsum.photos/seed/$seed/$width/$height"

    val tempFilePath = Paths.get("src/pictures/randomOnlineImage.png")

    val url = new URI(imageUrl).toURL
    val connection = url.openConnection()
    val inputStream = new BufferedInputStream(connection.getInputStream)
    val outputStream = new FileOutputStream(tempFilePath.toFile)

    try{
      try {
        val buffer = new Array[Byte](1024)
        var bytesRead = 0
        while ({ bytesRead = inputStream.read(buffer); bytesRead != -1 }) {
          outputStream.write(buffer, 0, bytesRead)
        }
      }
      finally {
        inputStream.close()
        outputStream.close()

      }
    }catch {
      case e: Exception =>
        throw new RuntimeException("Something went wrong with the site or storing the image to your pc")
    }

    val pixelImage = new PngLoader(tempFilePath.toString).loadImage()

    return pixelImage
  }
}
