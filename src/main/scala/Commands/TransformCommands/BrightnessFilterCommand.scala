package Commands.TransformCommands
import TransformationHandelers.Filters.BrightnessFilter
import TransformationHandelers.TransformationHandeler

class BrightnessFilterCommand(val intensity : Int) extends TransformCommand {
  override def applyCommand(handeler: TransformationHandeler): Unit = {
    handeler.addFilter(new BrightnessFilter(intensity))
  }
}
