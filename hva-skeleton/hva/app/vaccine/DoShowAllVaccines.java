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
  
  @Override
  protected final void execute() {
    _display.popup(_receiver.visualizaTodasVacinas());
  }
}
