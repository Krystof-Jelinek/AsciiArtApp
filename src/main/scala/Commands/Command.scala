package Commands

abstract class Command[T]{
  def applyCommand(handeler : T) : Unit
}
