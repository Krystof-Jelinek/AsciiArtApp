package Commands.TransformCommands
import TransformationHandelers.TransformationHandeler

class SetPredefinedTableCommand(val index : Int) extends TransformCommand {
  override def applyCommand(handeler: TransformationHandeler): Unit = {
    handeler.accessTable().setPredifinedTable(index)
  }
}
