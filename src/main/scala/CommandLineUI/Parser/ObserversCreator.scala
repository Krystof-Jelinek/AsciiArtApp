package CommandLineUI.Parser

import CommandLineUI.CommandObservers.CommandObserver
import CommandLineUI.CommandObservers.LoadCommandObservers.{LoadGifCommandObserver, LoadJpgCommandObserver, LoadPngCommandObserver, LoadRandomCommandObserver}
import CommandLineUI.CommandObservers.SaveCommandObservers.{OutputConsoleCommandObserver, OutputFileCommandObserver}
import CommandLineUI.CommandObservers.TransformCommandObservers.{BrightnessCommandObserver, InvertCommandObserver, NonLinearTableCommandObserver, ScaleCommandObserver, SetCustomTableCommandObserver, SetPredefinedTableCommandObserver}

class ObserversCreator {
  def createObservers(): List[CommandObserver] = {
    List[CommandObserver](
      LoadGifCommandObserver(),
      LoadJpgCommandObserver(),
      LoadPngCommandObserver(),
      LoadRandomCommandObserver(),
      OutputConsoleCommandObserver(),
      OutputFileCommandObserver(),
      BrightnessCommandObserver(),
      InvertCommandObserver(),
      NonLinearTableCommandObserver(),
      ScaleCommandObserver(),
      SetCustomTableCommandObserver(),
      SetPredefinedTableCommandObserver())
  }
}