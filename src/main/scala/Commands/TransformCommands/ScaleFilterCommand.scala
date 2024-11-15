package Commands.TransformCommands

import Handlers.TransformationHandler
import ImageTransformation.Filters.ScaleFilter

class ScaleFilterCommand(val scale : Float) extends TransformCommand {
  override def applyCommand(handeler: TransformationHandler): Unit = {
    handeler.addFilter(ScaleFilter(scale))
  }
}
