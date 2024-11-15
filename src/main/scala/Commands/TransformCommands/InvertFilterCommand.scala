package Commands.TransformCommands

import Handlers.TransformationHandler
import ImageTransformation.Filters.InvertFilter

class InvertFilterCommand extends TransformCommand {
  override def applyCommand(handeler: TransformationHandler): Unit = {
    handeler.addFilter(new InvertFilter)
  }
}
