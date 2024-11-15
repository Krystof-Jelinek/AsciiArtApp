package IOTests.LoaderTests

import DataModels.{Pixel, PixelImage}

//compares the loaded picture directly to the file for png and gif since they have the same colors
def validateTestImage(image: PixelImage): Unit = {
    assert(image.width == 3)
    assert(image.height == 3)
    assert(image.getVal(0, 0).get == Pixel(0,0,0), s"Pixel did not match expected value")
    assert(image.getVal(1, 0).get == Pixel(34,177,76), s"Pixel did not match expected value")
    assert(image.getVal(2, 0).get == Pixel(255,126,0), s"Pixel did not match expected value")
    assert(image.getVal(0, 1).get == Pixel(255,255,255), s"Pixel did not match expected value")
    assert(image.getVal(1, 1).get == Pixel(153,0,48), s"Pixel did not match expected value")
    assert(image.getVal(2, 1).get == Pixel(0,183,239), s"Pixel did not match expected value")
    assert(image.getVal(0, 2).get == Pixel(168,230,29), s"Pixel did not match expected value")
    assert(image.getVal(1, 2).get == Pixel(111,49,152), s"Pixel did not match expected value")
    assert(image.getVal(2, 2).get == Pixel(237,28,36), s"Pixel did not match expected value")
  }
