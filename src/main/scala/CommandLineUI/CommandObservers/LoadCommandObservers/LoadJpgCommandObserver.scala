package CommandLineUI.CommandObservers.LoadCommandObservers

import CommandLineUI.CommandObservers.CommandObserver
import Commands.LoaderCommands.LoadJpgImageCommand
import Commands.{CommandHolder, StringCommandTemplate}

class LoadJpgCommandObserver extends CommandObserver{
  override def observe(cmdTemplate: StringCommandTemplate, commandHolder: CommandHolder): Boolean = {
    if ((cmdTemplate.name == "--image") && (cmdTemplate.value.endsWith(".jpg"))) {
      if (commandHolder.loadCommand.isDefined) {
        throw IllegalArgumentException("Only one --image argument can be specified")
      }
      commandHolder.loadCommand = Some(new LoadJpgImageCommand(cmdTemplate.value))
      return true
    }
    return false
  }
}
