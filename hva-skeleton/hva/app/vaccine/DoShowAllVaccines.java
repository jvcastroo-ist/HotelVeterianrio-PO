package hva.app.vaccine;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
    //FIXME add command fields
  }
  
  /**
   * Executes the action to display all vaccines.
   * This method triggers the display of all vaccines by calling the 
   * visualizaTodasVacinas method on the receiver and showing the result in a popup.
   */
  @Override
  protected final void execute() {
    _display.popup(_receiver.visualizaTodasVacinas());
  }
}
