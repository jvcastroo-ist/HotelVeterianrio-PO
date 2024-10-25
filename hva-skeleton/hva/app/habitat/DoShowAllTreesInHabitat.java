package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Arvore;
import hva.core.Hotel;
import hva.core.exception.CoreUnknownHabitatKeyException;
import java.util.List;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    addStringField("id", Prompt.habitatKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
      List<Arvore> arvores = _receiver.mostraArvorePorHabitat(stringField("id"));
      for(Arvore a : arvores){
        _display.addLine(a.toString());
      }
      _display.display();
    } catch (CoreUnknownHabitatKeyException e) {
      throw new UnknownHabitatKeyException(e.getId());
    }
  }
}
