package Commands.TransformCommands
import TransformationHandelers.Filters.ScaleFilter
import TransformationHandelers.TransformationHandeler

class ScaleFilterCommand(val scale : Float) extends TransformCommand {
  override def applyCommand(handeler: TransformationHandeler): Unit = {
    handeler.addFilter(ScaleFilter(scale))
  }
}
