package CommandLineUI.CommandObservers.TransformCommandObservers

import CommandLineUI.CommandObservers.CommandObserver
import Commands.TransformCommands.ScaleFilterCommand
import Commands.{CommandHolder, StringCommandTemplate}

class ScaleCommandObserver extends CommandObserver{
  override def observe(cmdTemplate: StringCommandTemplate, commandHolder: CommandHolder) : Boolean = {
    if(cmdTemplate.name == "--scale"){
      try {
        val arg = cmdTemplate.value.toFloat
        commandHolder.transformCommands.addOne(new ScaleFilterCommand(arg))
        return true
      } catch {
        case _: NumberFormatException =>
          throw IllegalArgumentException("Scale filter arguments can be only float numbers")
      }
    }
    return false
  }
}
