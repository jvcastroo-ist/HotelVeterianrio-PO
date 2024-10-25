package hva.app.search;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.Hotel;
import hva.core.RegistoVacina;
import hva.core.exception.CoreUnknownAnimalKeyException;
import java.util.List;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all medical acts applied to a given animal.
 **/
class DoShowMedicalActsOnAnimal extends Command<Hotel> {

  DoShowMedicalActsOnAnimal(Hotel receiver) {
    super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
    addStringField("idAnimal", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected void execute() throws CommandException {
    try {
      List<RegistoVacina> animalRegistos = _receiver.consultaRegistoPorAnimal(stringField("idAnimal"));
      for(RegistoVacina rv : animalRegistos){
        _display.addLine(rv.toString());
      }
      _display.display();
    } catch (CoreUnknownAnimalKeyException a) {
      throw new UnknownAnimalKeyException(a.getId());
    }
  }
}
