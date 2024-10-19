package Parser

import Commands.LoaderCommands.{LoadGifImageCommand, LoadJpgImageCommand, LoadPngImageCommand, RandomImageCommand}
import Commands.SaverCommands.{OutputConsoleCommand, OutputFileCommand}
import DataModels.CommandHolder
import DataModels.StringCommandTemplate

class CommandParser {
  private var sourceBool : Boolean = false

  def parse(commands: Seq[String]) : CommandHolder = {
    sourceBool = false // reseting this so one parser can parse multiple commands
    val commandHolder = new CommandHolder()
    var i = 0
    //creating new command string
    var cmdName = ""
    var cmdValue = ""
    while(i < commands.size){
      if(commands(i).startsWith("--")){
        cmdName += commands(i)
        i += 1
        while((i < commands.size) && (!commands(i).startsWith("--"))){
          cmdValue += commands(i)
          i += 1
        }
        val in = StringCommandTemplate(cmdName, cmdValue)
        storeCommand(in, commandHolder)
        cmdValue = ""
        cmdName = ""
      }
    }
    if(!sourceBool){
      println("Exactly one --image command with source should be specified! but we are kind and generated random image for you")
    }
    return commandHolder
  }

  private def storeCommand(command: StringCommandTemplate, commandHolder: CommandHolder): Unit = {
    if(command.name.startsWith("--image")){
      if(sourceBool){
        throw IllegalArgumentException("Only one --image* argument cant be specified")
      }
      storeLoadCommand(command, commandHolder)
      sourceBool = true
    }
    else if (command.name.startsWith("--output")) {
      storeSaveCommand(command, commandHolder)
    }
    else{
      commandHolder.transformCommands.addOne(command)
    }
  }

  private def storeSaveCommand(command: StringCommandTemplate, commandHolder: CommandHolder) : Unit = {
    if(command.name == "--output-console"){
      commandHolder.saveCommands.addOne(new OutputConsoleCommand)
    }
    else if (command.name == "--output-file"){
      commandHolder.saveCommands.addOne(new OutputFileCommand(command.value))
    }
    else{
      throw IllegalArgumentException("This --output command is not supported")
    }
  }

  private def storeLoadCommand(command : StringCommandTemplate, commandHolder: CommandHolder) : Unit = {
    if (command.name == "--image-random") {
      commandHolder.loadCommand = RandomImageCommand(command.value)
    }
    else if (command.name == "--image") {
      command.value match {
        case filename if filename.endsWith(".png") =>
          commandHolder.loadCommand = LoadPngImageCommand(command.value)
        case filename if filename.endsWith(".jpg") =>
          commandHolder.loadCommand = LoadJpgImageCommand(command.value)
        case filename if filename.endsWith(".gif") =>
          commandHolder.loadCommand = LoadGifImageCommand(command.value)
        case _ =>
          throw IllegalArgumentException("this filetype is not supported")
      }
    }
    else {
      throw IllegalArgumentException("This --image command is not supported")
    }
  }




}
