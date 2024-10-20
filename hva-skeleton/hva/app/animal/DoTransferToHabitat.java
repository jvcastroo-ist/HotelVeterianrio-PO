package hva.app.animal;

import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreUnknownAnimalKeyException;
import hva.core.exception.CoreUnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {

  DoTransferToHabitat(Hotel hotel) {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
    addStringField("id", Prompt.animalKey());
    addStringField("habitat", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
        _receiver.transfereAnimal(stringField("id"),
        stringField("habitat")
      );
    } catch (CoreUnknownAnimalKeyException e) {
      throw new UnknownAnimalKeyException(e.getId());
    } catch (CoreUnknownHabitatKeyException e){
      throw new UnknownHabitatKeyException(e.getId());
    }
  }
}
