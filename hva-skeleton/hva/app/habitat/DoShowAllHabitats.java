package hva.app.habitat;

import hva.core.Habitat;
import hva.core.Hotel;
import java.util.List;
import pt.tecnico.uilib.menus.Command;

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
    List<Habitat> habitats = _receiver.sortIds(_receiver.getHabitats());
    for (Habitat h : habitats) {
      _display.addLine(h.toString());
    }
    _display.display();
  }
}
