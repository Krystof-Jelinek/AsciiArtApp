package Commands.TransformCommands

import Handlers.TransformationHandler

class SetCustomTableCommand(val table : String) extends TransformCommand {
  override def applyCommand(handeler: TransformationHandler): Unit = {
    handeler.accessTable().setTable(table)
  }
}
