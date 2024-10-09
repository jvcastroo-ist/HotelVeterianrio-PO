package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.Vacina;
import java.util.List; //adicionei caso mudarmos tirar
import java.util.Map;
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
    //FIXME implement command

    // Obtém todos as vacinas do hotel
    Map<String, Vacina> vacinaMap = _receiver.getVacinas();

    // Ordena as vacinas usando o método sortIds
    List<Vacina> sortedVacinas = (List<Vacina>) _receiver.sortIds(vacinaMap);

    for (Vacina vacina : sortedVacinas) {
      _display.addLine(vacina.visualiza(_receiver)); // Adiciona cada vacina numa nova linha
    }
  
    _display.display(); // Mostra todos as vacinas

  }
}
