package TransformationHandelers.Filters

import DataModels.Image

abstract class Filter {
 def applyFilter(img : Image) : Unit
}
