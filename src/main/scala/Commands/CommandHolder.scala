package Commands

import Commands.LoaderCommands.LoadCommand
import Commands.SaverCommands.SaveCommand
import Commands.TransformCommands.TransformCommand

import scala.collection.mutable.ArrayBuffer

class CommandHolder {
  var loadCommand: Option[LoadCommand] = None
  var transformCommands: ArrayBuffer[TransformCommand] = ArrayBuffer.empty[TransformCommand]
  var saveCommands: ArrayBuffer[SaveCommand] = ArrayBuffer.empty[SaveCommand]
}
