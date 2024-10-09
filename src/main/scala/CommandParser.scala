class CommandParser {
  def parse(commands: Seq[String]) : CommandHolder = {
    var commandHolder = new CommandHolder()
    var i = 0
    //creating new command string
    var resString = ""
    while(i < commands.size){
      if(commands(i).startsWith("--")){
        resString += commands(i)
        i += 1
        while((i < commands.size) && (!commands(i).startsWith("--"))){
          resString += " " + commands(i)
          i += 1
        }
        println(resString)
        resString = ""
      }
    }

    return commandHolder
  }




}
