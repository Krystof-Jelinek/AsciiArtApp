package ImageTransformation.Filters

import DataModels.{Image, PixelImage}

abstract class Filter {
 def applyFilter(img : PixelImage) : Unit
}
