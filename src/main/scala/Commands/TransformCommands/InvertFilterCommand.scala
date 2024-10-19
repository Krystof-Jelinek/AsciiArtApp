package Commands.TransformCommands
import TransformationHandelers.Filters.InvertFilter
import TransformationHandelers.TransformationHandeler

class InvertFilterCommand extends TransformCommand {
  override def applyCommand(handeler: TransformationHandeler): Unit = {
    handeler.addFilter(new InvertFilter)
  }
}
