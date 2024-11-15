package Commands.TransformCommands

import Handlers.TransformationHandler
import ImageTransformation.Filters.BrightnessFilter

class BrightnessFilterCommand(val intensity : Int) extends TransformCommand {
  override def applyCommand(handeler: TransformationHandler): Unit = {
    handeler.addFilter(new BrightnessFilter(intensity))
  }
}
