package CommandLineUI.CommandObservers.TransformCommandObservers

import CommandLineUI.CommandObservers.CommandObserver
import Commands.TransformCommands.NonLinearTableCommand
import Commands.{CommandHolder, StringCommandTemplate}

class NonLinearTableCommandObserver extends CommandObserver{
  override def observe(cmdTemplate: StringCommandTemplate, commandHolder: CommandHolder) : Boolean = {
    if(cmdTemplate.name == "--table-non-linear"){
      commandHolder.transformCommands.addOne(new NonLinearTableCommand())
      return true
    }
    return false
  }
}
