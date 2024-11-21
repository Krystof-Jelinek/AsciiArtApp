package CommandLineUI.CommandObservers.TransformCommandObservers

import CommandLineUI.CommandObservers.CommandObserver
import CommandLineUI.StringCommandTemplate
import Commands.TransformCommands.SetPredefinedTableCommand
import Commands.CommandHolder

class SetPredefinedTableCommandObserver extends CommandObserver{
  override def observe(cmdTemplate: StringCommandTemplate, commandHolder: CommandHolder) : Boolean = {
    if(cmdTemplate.name == "--table"){
      try {
        val arg = cmdTemplate.value.toInt
        commandHolder.transformCommands.addOne(new SetPredefinedTableCommand(arg))
        return true
      } catch {
        case _: NumberFormatException =>
          throw IllegalArgumentException("Predifined Tables are only named/numbered 0-1-2-3-4")
      }
    }
    return false
  }
}
