package DataModels

import Commands.LoaderCommands.{LoadCommand, RandomImageCommand}
import Commands.SaverCommands.SaveCommand
import DataModels.Command

import scala.collection.mutable.ArrayBuffer

class CommandHolder {
  //seting default value to generate random images if no source is specified
  var loadCommand: LoadCommand = RandomImageCommand("") 
  var transformCommands: ArrayBuffer[Command] = ArrayBuffer.empty[Command]
  var saveCommands: ArrayBuffer[SaveCommand] = ArrayBuffer.empty[SaveCommand]
}
