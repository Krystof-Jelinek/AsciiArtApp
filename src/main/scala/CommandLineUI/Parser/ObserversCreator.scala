package CommandLineUI.Parser

import CommandLineUI.CommandObservers.CommandObserver

class ObserversCreator {
  private val observerClasses = List(
    "CommandLineUI.CommandObservers.LoadCommandObservers.LoadGifCommandObserver",
    "CommandLineUI.CommandObservers.LoadCommandObservers.LoadJpgCommandObserver",
    "CommandLineUI.CommandObservers.LoadCommandObservers.LoadPngCommandObserver",
    "CommandLineUI.CommandObservers.LoadCommandObservers.LoadRandomCommandObserver",
    "CommandLineUI.CommandObservers.SaveCommandObservers.OutputConsoleCommandObserver",
    "CommandLineUI.CommandObservers.SaveCommandObservers.OutputFileCommandObserver",
    "CommandLineUI.CommandObservers.TransformCommandObservers.BrightnessCommandObserver",
    "CommandLineUI.CommandObservers.TransformCommandObservers.InvertCommandObserver",
    "CommandLineUI.CommandObservers.TransformCommandObservers.NonLinearTableCommandObserver",
    "CommandLineUI.CommandObservers.TransformCommandObservers.ScaleCommandObserver",
    "CommandLineUI.CommandObservers.TransformCommandObservers.SetCustomTableCommandObserver",
    "CommandLineUI.CommandObservers.TransformCommandObservers.SetPredefinedTableCommandObserver",
  )

  def createObservers(): List[CommandObserver] = {
    observerClasses.flatMap { observerName =>
      try {
        val observerClass = Class.forName(observerName)
        val observer = observerClass.getDeclaredConstructor().newInstance()
        Some(observer.asInstanceOf[CommandObserver])
      } catch {
        case e: Exception =>
          throw RuntimeException(s"Could not load observer $observerName: ${e.getMessage}")
      }
    }
  }
}
