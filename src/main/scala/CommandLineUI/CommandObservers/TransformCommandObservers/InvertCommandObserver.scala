package CommandLineUI.CommandObservers.TransformCommandObservers

import CommandLineUI.CommandObservers.CommandObserver
import CommandLineUI.StringCommandTemplate
import Commands.TransformCommands.InvertFilterCommand
import Commands.CommandHolder

class InvertCommandObserver extends CommandObserver {
  override def observe(cmdTemplate: StringCommandTemplate, commandHolder: CommandHolder) : Boolean = {
    if (cmdTemplate.name == "--invert") {
      commandHolder.transformCommands.addOne(new InvertFilterCommand())
      return true
    }
    return false
  }
}
