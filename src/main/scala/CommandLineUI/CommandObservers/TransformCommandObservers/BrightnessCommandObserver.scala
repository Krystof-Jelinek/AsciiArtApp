package CommandLineUI.CommandObservers.TransformCommandObservers

import CommandLineUI.CommandObservers.CommandObserver
import Commands.TransformCommands.BrightnessFilterCommand
import Commands.{CommandHolder, StringCommandTemplate}

class BrightnessCommandObserver extends CommandObserver{
  override def observe(cmdTemplate: StringCommandTemplate, commandHolder: CommandHolder) : Boolean = {
    if(cmdTemplate.name == "--brightness"){
      try {
        val arg = cmdTemplate.value.toInt
        commandHolder.transformCommands.addOne(new BrightnessFilterCommand(arg))
        return true
      } catch {
        case _: NumberFormatException =>
          throw IllegalArgumentException("Brightness filter arguments can be only whole numbers")
      }
    }
    return false
  }
}
