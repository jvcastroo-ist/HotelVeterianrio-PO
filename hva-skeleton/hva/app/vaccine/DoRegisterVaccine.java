package hva.app.vaccine;

import hva.app.exception.DuplicateVaccineKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreDuplicateVaccineKeyException;
import hva.core.exception.CoreUnknownSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {

  DoRegisterVaccine(Hotel receiver) {
    super(Label.REGISTER_VACCINE, receiver);
    addStringField("id", Prompt.vaccineKey());
    addStringField("name", Prompt.vaccineName());
    addStringField("speciesIds", Prompt.listOfSpeciesKeys());
  }

  @Override
  protected final void execute() throws CommandException {
    try {
        String speciesIdsString = stringField("speciesIds");
        String[] speciesIds = speciesIdsString.split(",");
        _receiver.registerVaccine(stringField("id"),
                  stringField("name"), 
                  speciesIds);
    } catch (CoreDuplicateVaccineKeyException e) {
        throw new DuplicateVaccineKeyException(e.getId());
    } catch (CoreUnknownSpeciesKeyException e){
      throw new UnknownSpeciesKeyException(e.getId());
    }
  }
}
