package Commands.TransformCommands
import TransformationHandelers.Converters.NonLinearConverter
import TransformationHandelers.TransformationHandeler

class NonLinearTableCommand extends TransformCommand {
  override def applyCommand(handeler: TransformationHandeler): Unit = {
    handeler.setImageConverterInterface(new NonLinearConverter(handeler.accessTable()))      
  }
}
