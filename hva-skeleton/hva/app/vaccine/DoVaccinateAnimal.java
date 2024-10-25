package hva.app.vaccine;

import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownVaccineKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.app.exception.VeterinarianNotAuthorizedException;
import hva.core.Hotel;
import hva.core.exception.CoreUnknownAnimalKeyException;
import hva.core.exception.CoreUnknownVaccineKeyException;
import hva.core.exception.CoreUnknownVeterinarianKeyException;
import hva.core.exception.CoreVeterinarianNotAuthorizedException;
import hva.core.exception.CoreWrongVaccineException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Vaccinate by a given veterinarian a given animal with a given vaccine.
 **/
class DoVaccinateAnimal extends Command<Hotel> {
  DoVaccinateAnimal(Hotel receiver) {
    super(Label.VACCINATE_ANIMAL, receiver);
    addStringField("idVaccine", Prompt.vaccineKey());
    addStringField("idVet", Prompt.veterinarianKey());
    addStringField("idAnimal", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    try{
      String idVaccine = stringField("idVaccine");
      String idVet = stringField("idVet");
      String idAnimal = stringField("idAnimal");

      _receiver.vacinarAnimal(idVaccine, idVet, idAnimal);
      
    } catch(CoreWrongVaccineException v){
      _display.popup(Message.wrongVaccine(v.getVaccine(), v.getAnimal()));
    } catch(CoreUnknownVaccineKeyException v){
      throw new UnknownVaccineKeyException(v.getId());
    } catch(CoreUnknownVeterinarianKeyException v){
      throw new UnknownVeterinarianKeyException(v.getId());
    } catch(CoreUnknownAnimalKeyException a){
      throw new UnknownAnimalKeyException(a.getId());
    } catch(CoreVeterinarianNotAuthorizedException v){
      throw new VeterinarianNotAuthorizedException(v.getVet(), v.getSpecies());
    }
  }
}
