package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Arvore;
import hva.core.Habitat;
import hva.core.Hotel;
import hva.core.exception.CoreUnknownHabitatKeyException;
import java.util.List;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

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
      Habitat h = _receiver.getHabitat(stringField("id"));
      List<Arvore> arvores = h.getArvores();
      for(Arvore a : arvores){
        _display.addLine(a.toString());
      }
      _display.display();
    } catch (CoreUnknownHabitatKeyException e) {
      throw new UnknownHabitatKeyException(e.getId());
    }
  }
}
