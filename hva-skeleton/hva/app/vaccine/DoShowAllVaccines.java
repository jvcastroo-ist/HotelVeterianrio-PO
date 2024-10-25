package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.Vacina;
import java.util.List;
import pt.tecnico.uilib.menus.Command;


/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
  }
  
  /**
   * Executes the action to display all vaccines.
   * This method triggers the display of all vaccines by calling the 
   * visualizaTodasVacinas method on the receiver and showing the result in a popup.
   */
  @Override
  protected final void execute() {
    List<Vacina> vacinas = _receiver.sortIds(_receiver.getVaccines());
    for (Vacina v: vacinas) {
      _display.addLine(v.toString());
    }
    _display.display();
  }
}
