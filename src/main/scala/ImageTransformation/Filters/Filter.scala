package ImageTransformation.Filters

import DataModels.PixelImage

abstract class Filter {
 def applyFilter(img : PixelImage) : Unit
}
