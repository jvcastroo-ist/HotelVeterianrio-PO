package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreUnknownHabitatKeyException;
import hva.core.exception.CoreUnknownSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {

  DoChangeHabitatInfluence(Hotel receiver) {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    addStringField("idHabitat", Prompt.habitatKey());
    addStringField("idSpecies", hva.app.animal.Prompt.speciesKey());
    addOptionField("habitatInfluence", Prompt.habitatInfluence(), "NEG", "NEU", "POS");
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
      _receiver.alteraInfluenciaEspecie(stringField("idHabitat"), stringField("idSpecies"), stringField("habitatInfluence"));
    } catch (CoreUnknownHabitatKeyException h) {
      throw new UnknownHabitatKeyException(h.getId());
    } catch(CoreUnknownSpeciesKeyException e){
      throw new UnknownSpeciesKeyException(e.getId());
    }
  }
}
