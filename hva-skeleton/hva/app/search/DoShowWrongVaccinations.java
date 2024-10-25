package hva.app.search;

import hva.core.Hotel;
import hva.core.RegistoVacina;
import java.util.List;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all vaccines applied to animals belonging to an invalid species.
 **/
class DoShowWrongVaccinations extends Command<Hotel> {

  DoShowWrongVaccinations(Hotel receiver) {
    super(Label.WRONG_VACCINATIONS, receiver);
    //FIXME add command fields
  }

  @Override
  protected void execute() throws CommandException {
      List<RegistoVacina> registoVacInc = _receiver.consultaVacinaComIncuria();
      for(RegistoVacina rv : registoVacInc){
        _display.addLine(rv.toString());
      }
      _display.display();
  }
}
