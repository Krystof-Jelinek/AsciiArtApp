package CommandLineUI.Parser

import CommandLineUI.CommandObservers.CommandObserver
import CommandLineUI.CommandObservers.LoadCommandObservers.{LoadGifCommandObserver, LoadJpgCommandObserver, LoadPngCommandObserver, LoadRandomCommandObserver, LoadRandomOnlineCommandObserver}
import CommandLineUI.CommandObservers.SaveCommandObservers.{OutputConsoleCommandObserver, OutputFileCommandObserver}
import CommandLineUI.CommandObservers.TransformCommandObservers.{BrightnessCommandObserver, InvertCommandObserver, NonLinearTableCommandObserver, ScaleCommandObserver, SetCustomTableCommandObserver, SetPredefinedTableCommandObserver}

//this class only resposibility is to set all the observer/commands that we wanna use
//it has its own class since if we wanna add another command we only need to make the command & his observer and than only add the observer here
class ObserversCreator {
  def createObservers(): List[CommandObserver] = {
    List[CommandObserver](
      LoadGifCommandObserver(),
      LoadJpgCommandObserver(),
      LoadPngCommandObserver(),
      LoadRandomCommandObserver(),
      LoadRandomOnlineCommandObserver(),
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