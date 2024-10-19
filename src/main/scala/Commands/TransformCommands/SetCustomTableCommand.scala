package Commands.TransformCommands
import TransformationHandelers.TransformationHandeler

class SetCustomTableCommand(val table : String) extends TransformCommand {
  override def applyCommand(handeler: TransformationHandeler): Unit = {
    handeler.accessTable().setTable(table)
  }
}
