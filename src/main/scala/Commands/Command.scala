package Commands

//this class has nothing to do with UI - its only generated inside the parses but can be used the same way if obtained from GUI/http request and so on...
abstract class Command[T]{
  def applyCommand(handeler : T) : Unit
}
