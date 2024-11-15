package CommandLineUI.CommandObservers

import Commands.{CommandHolder, StringCommandTemplate}

trait CommandObserver {
  def observe(cmdTemplate: StringCommandTemplate, commandHolder: CommandHolder) : Boolean
}
