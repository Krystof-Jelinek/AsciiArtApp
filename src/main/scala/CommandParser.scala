
class CommandParser {
  def parse(commands: Seq[String]) : CommandHolder = {
    var commandHolder = new CommandHolder()
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
        val in = Command(cmdName, cmdValue)
        storeCommand(in, commandHolder)
        cmdValue = ""
        cmdName = ""
      }
    }
    return commandHolder
  }

  private def storeCommand(command: Command, commandHolder: CommandHolder): Unit = {
    if(command.name.startsWith("--image")){
      commandHolder.loadCommand = command
    }
    else if (command.name.startsWith("--output")) {
      commandHolder.saveCommands.addOne(command)
    }
    else{
      commandHolder.transformCommands.addOne(command)
    }
  }




}
