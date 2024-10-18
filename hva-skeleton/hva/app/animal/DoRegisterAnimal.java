package hva.app.animal;

import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.DuplicateHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreDuplicateAnimalKeyException;
import hva.core.exception.CoreUnknownHabitatKeyException;
import hva.core.exception.CoreUnknownSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {

  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
    addStringField("id", Prompt.animalKey());
    addStringField("name", Prompt.animalName());
    addStringField("specie", Prompt.speciesKey());
    addStringField("habitat", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      _receiver.registerAnimal(
                stringField("id"),
                stringField("name"),
                stringField("specie"),
                stringField("habitat")
      );
    } catch (CoreDuplicateAnimalKeyException e) {
      throw new DuplicateAnimalKeyException(e.getId());
    } catch (CoreUnknownSpeciesKeyException e){
      System.out.println("DuplicateSpeciesId");
    } catch (CoreUnknownHabitatKeyException e){
      throw new DuplicateHabitatKeyException(e.getId());
    }
  }
}
