package hva.app.habitat;

import hva.app.exception.DuplicateHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreDuplicateHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {

  DoRegisterHabitat(Hotel receiver) {
    super(Label.REGISTER_HABITAT, receiver);
    addStringField("id", Prompt.habitatKey());
    addStringField("name", Prompt.habitatName());
    addIntegerField("area", Prompt.habitatArea());
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
      _receiver.registerHabitat(stringField("id"),
              stringField("name"),
              integerField("area")
    );
    } catch(CoreDuplicateHabitatKeyException e) {
      throw new DuplicateHabitatKeyException(e.getId());
    }
  }
}
