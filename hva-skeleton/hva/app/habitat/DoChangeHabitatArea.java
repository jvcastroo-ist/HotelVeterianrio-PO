package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreUnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  DoChangeHabitatArea(Hotel receiver) {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    addStringField("id", Prompt.habitatKey());
    addIntegerField("area", Prompt.habitatArea());
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
        _receiver.alteraAreaHabitat(stringField("id"),
                  integerField("area")
        );
    } catch (CoreUnknownHabitatKeyException e) {
      throw new UnknownHabitatKeyException(e.getId());
    }
  }
}
