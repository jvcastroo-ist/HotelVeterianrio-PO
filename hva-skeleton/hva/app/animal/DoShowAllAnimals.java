package hva.app.animal;

import hva.core.Hotel;
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
    _display.popup(_receiver.visualizaTodosAnimais()); // Mostra todos os animais
  }
}