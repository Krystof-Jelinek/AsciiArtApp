package CommandLineUI.CommandObservers.TransformCommandObservers

import CommandLineUI.CommandObservers.CommandObserver
import CommandLineUI.StringCommandTemplate
import Commands.TransformCommands.SetCustomTableCommand
import Commands.CommandHolder

class SetCustomTableCommandObserver extends CommandObserver{
  override def observe(cmdTemplate: StringCommandTemplate, commandHolder: CommandHolder) : Boolean = {
    if(cmdTemplate.name == "--custom-table"){
      commandHolder.transformCommands.addOne(new SetCustomTableCommand(cmdTemplate.value))
      return true
    }
    return false
  }
}
