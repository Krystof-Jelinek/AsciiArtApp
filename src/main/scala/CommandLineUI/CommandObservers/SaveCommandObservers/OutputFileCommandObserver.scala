package CommandLineUI.CommandObservers.SaveCommandObservers

import CommandLineUI.CommandObservers.CommandObserver
import Commands.SaverCommands.OutputFileCommand
import Commands.{CommandHolder, StringCommandTemplate}

class OutputFileCommandObserver extends CommandObserver{
  override def observe(cmdTemplate: StringCommandTemplate, commandHolder: CommandHolder) : Boolean = {
    if (cmdTemplate.name == "--output-file") {
      commandHolder.saveCommands.addOne(new OutputFileCommand(cmdTemplate.value))
      return true
    }
    return false
  }
}
