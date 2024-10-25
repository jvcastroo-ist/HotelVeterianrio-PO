package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.RegistoVacina;
import java.util.List;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all applied vacines by all veterinarians of this zoo hotel.
 **/
class DoShowVaccinations extends Command<Hotel> {

  DoShowVaccinations(Hotel receiver) {
    super(Label.SHOW_VACCINATIONS, receiver);
  }
  
  @Override
  protected final void execute() {
    List<RegistoVacina> registoVacinas = _receiver.getRegistoVacinas();

    for(RegistoVacina rv : registoVacinas){
      _display.addLine(rv.toString());
    }
    _display.display();
  }
}
