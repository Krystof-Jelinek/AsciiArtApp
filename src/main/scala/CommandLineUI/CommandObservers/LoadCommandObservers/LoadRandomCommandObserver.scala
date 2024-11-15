package CommandLineUI.CommandObservers.LoadCommandObservers

import CommandLineUI.CommandObservers.CommandObserver
import Commands.LoaderCommands.LoadRandomImageCommand
import Commands.{CommandHolder, StringCommandTemplate}

class LoadRandomCommandObserver extends CommandObserver {
  override def observe(cmdTemplate: StringCommandTemplate, commandHolder: CommandHolder) : Boolean = {
    if (cmdTemplate.name == "--image-random") {
      if(commandHolder.loadCommand.isDefined){
        throw IllegalArgumentException("Only one --image argument can be specified")
      }
      commandHolder.loadCommand = Some(new LoadRandomImageCommand(cmdTemplate.value))
      return true
    }
    return false
  }
}
