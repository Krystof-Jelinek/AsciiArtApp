package CommandLineUI.CommandObservers.LoadCommandObservers

import CommandLineUI.CommandObservers.CommandObserver
import Commands.LoaderCommands.LoadPngImageCommand
import Commands.{CommandHolder, StringCommandTemplate}

class LoadPngCommandObserver extends CommandObserver{
  override def observe(cmdTemplate: StringCommandTemplate, commandHolder: CommandHolder) : Boolean = {
    if((cmdTemplate.name == "--image") && (cmdTemplate.value.endsWith(".png"))){
      if(commandHolder.loadCommand.isDefined){
        throw IllegalArgumentException("Only one --image argument can be specified")
      }
      commandHolder.loadCommand = Some(new LoadPngImageCommand(cmdTemplate.value))
      return true
    }
    return false
  }
}
