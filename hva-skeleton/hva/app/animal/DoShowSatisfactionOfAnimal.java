package hva.app.animal;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreUnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {

  DoShowSatisfactionOfAnimal(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
    addStringField("idAnimal", Prompt.animalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
        _display.addLine((int)Math.round(_receiver.calculaSatisfacaoAnimal(stringField("idAnimal"))));
        _display.display();
    } catch (CoreUnknownAnimalKeyException a) {
      throw new UnknownAnimalKeyException(a.getId());
    }
  }
}
