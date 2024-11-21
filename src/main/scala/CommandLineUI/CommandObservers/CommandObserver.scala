package CommandLineUI.CommandObservers

import CommandLineUI.StringCommandTemplate
import Commands.CommandHolder

trait CommandObserver {
  def observe(cmdTemplate: StringCommandTemplate, commandHolder: CommandHolder) : Boolean
}
