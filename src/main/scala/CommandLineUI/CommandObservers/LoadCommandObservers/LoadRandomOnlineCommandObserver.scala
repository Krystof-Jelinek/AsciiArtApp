package CommandLineUI.CommandObservers.LoadCommandObservers

import CommandLineUI.CommandObservers.CommandObserver
import CommandLineUI.StringCommandTemplate
import Commands.CommandHolder
import Commands.LoaderCommands.LoadRandomOnlineImageCommand

class LoadRandomOnlineCommandObserver extends CommandObserver{
  override def observe(cmdTemplate: StringCommandTemplate, commandHolder: CommandHolder): Boolean = {
    if (cmdTemplate.name == "--image-random-online") {
      if (commandHolder.loadCommand.isDefined) {
        throw IllegalArgumentException("Only one --image argument can be specified")
      }
      commandHolder.loadCommand = Some(new LoadRandomOnlineImageCommand(cmdTemplate.value))
      return true
    }
    return false
  }
}
