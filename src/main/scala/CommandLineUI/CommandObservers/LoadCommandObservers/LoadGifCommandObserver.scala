package CommandLineUI.CommandObservers.LoadCommandObservers

import CommandLineUI.CommandObservers.CommandObserver
import Commands.LoaderCommands.LoadGifImageCommand
import Commands.{CommandHolder, StringCommandTemplate}

class LoadGifCommandObserver extends CommandObserver {
  override def observe(cmdTemplate: StringCommandTemplate, commandHolder: CommandHolder): Boolean = {
    if((cmdTemplate.name == "--image") && (cmdTemplate.value.endsWith(".gif"))){
      if(commandHolder.loadCommand.isDefined){
        throw IllegalArgumentException("Only one --image argument can be specified")
      }
      commandHolder.loadCommand = Some(new LoadGifImageCommand(cmdTemplate.value))
      return true
    }
    return false
  }
}
