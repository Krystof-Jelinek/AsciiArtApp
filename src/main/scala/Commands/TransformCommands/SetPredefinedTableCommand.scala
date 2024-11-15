package Commands.TransformCommands

import Handlers.TransformationHandler

class SetPredefinedTableCommand(val index : Int) extends TransformCommand {
  override def applyCommand(handeler: TransformationHandler): Unit = {
    handeler.accessTable().setPredifinedTable(index)
  }
}
