package DataModels

import Commands.SaverCommands.SaveCommand
import DataModels.Command

import scala.collection.mutable.ArrayBuffer

class CommandHolder {
  var loadCommand: Command = new Command
  var transformCommands: ArrayBuffer[Command] = ArrayBuffer.empty[Command]
  var saveCommands: ArrayBuffer[SaveCommand] = ArrayBuffer.empty[SaveCommand]
}
