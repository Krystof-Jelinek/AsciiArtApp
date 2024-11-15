package Commands

//this class has nothing to do with UI - its only created inside the parser
// but can be used the same way if obtained from GUI/http request and so on...
//for example if GUI user clicks on invertFilter button --> it will just add invertFilterCommand into the CommandHolder
//same as if CL user adds parametr --invert
abstract class Command[T]{
  def applyCommand(handeler : T) : Unit
}
