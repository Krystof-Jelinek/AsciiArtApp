package Commands

import Commands.LoaderCommands.{LoadCommand, LoadRandomImageCommand}
import Commands.SaverCommands.SaveCommand
import Commands.TransformCommands.TransformCommand

import scala.collection.mutable.ArrayBuffer

class CommandHolder {
  //seting default value to generate random images if no source is specified
  var loadCommand: LoadCommand = LoadRandomImageCommand("")
  var transformCommands: ArrayBuffer[TransformCommand] = ArrayBuffer.empty[TransformCommand]
  var saveCommands: ArrayBuffer[SaveCommand] = ArrayBuffer.empty[SaveCommand]
}
