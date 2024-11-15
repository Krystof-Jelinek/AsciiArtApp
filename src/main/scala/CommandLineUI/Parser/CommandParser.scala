package CommandLineUI.Parser

import CommandLineUI.CommandObservers.CommandObserver
import Commands.{CommandHolder, StringCommandTemplate}
import Commands.LoaderCommands.{LoadGifImageCommand, LoadJpgImageCommand, LoadPngImageCommand, LoadRandomImageCommand}
import Commands.SaverCommands.{OutputConsoleCommand, OutputFileCommand}
import Commands.TransformCommands.{BrightnessFilterCommand, InvertFilterCommand, NonLinearTableCommand, ScaleFilterCommand, SetCustomTableCommand, SetPredefinedTableCommand}

//this class handels with the user input and creates the inner commands that are used to share informations to the handelers
class CommandParser {
  private val observersCreator = ObserversCreator()

  private var observers = List[CommandObserver]()

  def parse(commands: Seq[String]) : CommandHolder = {
    var sourceFlag = false // reseting this so one parser can parse multiple commands
    observers = observersCreator.createObservers()
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
    if(commandHolder.loadCommand.isEmpty){
      throw IllegalArgumentException("You are missing --image argument - exactly one must be specified")
    }
    return commandHolder
  }

  private def storeCommand(command: StringCommandTemplate, commandHolder: CommandHolder): Unit = {
    var observedFlag : Boolean = false
    observers.foreach { observer =>
      if(observer.observe(command, commandHolder)){
        observedFlag = true
      }
    }
    if(!observedFlag){
      throwCorrectError(command)
    }
  }

  private def throwCorrectError(command: StringCommandTemplate): Unit = {
    if(command.name == "--image"){
      throw IllegalArgumentException("This --image command or input file type is not supported")
    }
    else{
      throw IllegalArgumentException(s"This command : ${command.name} is not supported")
    }
  }
}
