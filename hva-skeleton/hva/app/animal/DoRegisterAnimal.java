package hva.app.animal;

import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreDuplicateAnimalKeyException;
import hva.core.exception.CoreDuplicateSpeciesKeyException;
import hva.core.exception.CoreDuplicateSpeciesNameException;
import hva.core.exception.CoreUnknownHabitatKeyException;
import hva.core.exception.CoreUnknownSpeciesKeyException;
import pt.tecnico.uilib.forms.Form;
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
    addStringField("idSpecie", Prompt.speciesKey());
    addStringField("idHabitat", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String idAnimal = stringField("id");
    String nameAnimal = stringField("name");
    String idSpecie = stringField("idSpecie");
    String idHabitat = stringField("idHabitat");
    try {
      _receiver.registerAnimal(idAnimal, nameAnimal, idSpecie, idHabitat);
    } catch (CoreDuplicateAnimalKeyException a) {
      throw new DuplicateAnimalKeyException(a.getId());
    } catch (CoreUnknownSpeciesKeyException e){
      
      String nameSpecie = Form.requestString(Prompt.speciesName());
     
      try {
        _receiver.registerSpecies(idSpecie, nameSpecie);
        _receiver.registerAnimal(idAnimal, nameAnimal, idSpecie, idHabitat);
      } catch (CoreDuplicateSpeciesKeyException | CoreDuplicateSpeciesNameException |  CoreDuplicateAnimalKeyException|
              CoreUnknownSpeciesKeyException | CoreUnknownHabitatKeyException excecao) {
        System.out.println("NOME ESPÉCIE REPETIDA");
      }  
    } catch (CoreUnknownHabitatKeyException h){
      throw new UnknownHabitatKeyException(h.getId());
    }
  }
}
