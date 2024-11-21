package CommandLineUI.CommandObservers.SaveCommandObservers

import CommandLineUI.CommandObservers.CommandObserver
import CommandLineUI.StringCommandTemplate
import Commands.SaverCommands.OutputConsoleCommand
import Commands.CommandHolder

class OutputConsoleCommandObserver extends CommandObserver{
  override def observe(cmdTemplate: StringCommandTemplate, commandHolder: CommandHolder) : Boolean = {
    if(cmdTemplate.name == "--output-console"){
      commandHolder.saveCommands.addOne(new OutputConsoleCommand)
      return true
    }
    return false
  }
}
