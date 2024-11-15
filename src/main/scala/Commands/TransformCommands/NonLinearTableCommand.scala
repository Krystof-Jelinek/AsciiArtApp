package Commands.TransformCommands

import Handlers.TransformationHandler
import ImageTransformation.Converters.NonLinearConverter

class NonLinearTableCommand extends TransformCommand {
  override def applyCommand(handeler: TransformationHandler): Unit = {
    handeler.setImageConverterInterface(new NonLinearConverter(handeler.accessTable()))      
  }
}
