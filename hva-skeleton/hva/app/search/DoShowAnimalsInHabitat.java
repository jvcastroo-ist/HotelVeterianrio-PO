package hva.app.search;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Animal;
import hva.core.Hotel;
import hva.core.exception.CoreUnknownHabitatKeyException;
import java.util.List;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all animals of a given habitat.
 **/
class DoShowAnimalsInHabitat extends Command<Hotel> {

  DoShowAnimalsInHabitat(Hotel receiver) {
    super(Label.ANIMALS_IN_HABITAT, receiver);
    addStringField("id", hva.app.habitat.Prompt.habitatKey());
  }

  @Override
  protected void execute() throws CommandException {
    try {
        List<Animal> animals = _receiver.mostraAnimaisPorHabitat(stringField("id"));
         for(Animal a : animals){
          _display.addLine(a.toString());
         }
         _display.display();
    } catch (CoreUnknownHabitatKeyException e) {
      throw new UnknownHabitatKeyException(e.getId());
    }
  }
}
