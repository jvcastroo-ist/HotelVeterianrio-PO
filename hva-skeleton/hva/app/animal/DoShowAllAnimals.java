package hva.app.animal;

import hva.core.Animal;     // verificar estes dois imports
import hva.core.Hotel;       // verificar estes dois imports
import java.util.List;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all animals registered in this zoo hotel.
 */
class DoShowAllAnimals extends Command<Hotel> {

  DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
  }
  
  /**
   * Executes the command to display all animals.
   * This method triggers the display popup to show all animals
   * by calling the visualizaTodosAnimais method on the receiver.
   */
  @Override
  protected final void execute() {
    List<Animal> animals = _receiver.sortIds(_receiver.getAnimals());
    for (Animal a : animals) {
      _display.addLine(a.toString());
    }
    _display.display();
  }
}