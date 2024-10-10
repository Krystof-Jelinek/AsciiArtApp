package DataModels

import DataModels.Command

import scala.collection.mutable.ArrayBuffer

class CommandHolder {
  var loadCommand: Command = new Command
  var transformCommands: ArrayBuffer[Command] = ArrayBuffer.empty[Command]
  var saveCommands: ArrayBuffer[Command] = ArrayBuffer.empty[Command]
}
