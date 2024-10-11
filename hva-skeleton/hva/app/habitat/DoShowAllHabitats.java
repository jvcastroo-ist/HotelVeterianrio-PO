package hva.app.habitat;

import hva.core.Habitat;  // adicionei caso mudarmos tirar
import hva.core.Hotel;
import java.util.List;
import java.util.Map;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Show all habitats of this zoo hotel.
 **/
class DoShowAllHabitats extends Command<Hotel> {

  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
  }
  
  /**
   * Executes the action to display all habitats.
   * This method triggers the display popup to show all habitats
   * by calling the visualizaTodosHabitats method on the receiver.
   */
  @Override
  protected void execute() {
    _display.popup(_receiver.visualizaTodosHabitats()); // Mostra todos os habitats 
  }
}
