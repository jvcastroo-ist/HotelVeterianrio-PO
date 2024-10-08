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
  
  @Override
  protected void execute() {
    //FIXME implement command
    // Obtém todos os habitats do hotel
    Map<String, Habitat> habitatMap = _receiver.getHabitats();

    // Ordena os habits usando o método sortIds
    List<Habitat> sortedHabitat = (List<Habitat>) _receiver.sortIds(habitatMap);

    for (Habitat habitat : sortedHabitat) {
      _display.addLine(habitat.visualiza(_receiver)); // Adiciona cada habitat em uma nova linha
    }
  
    _display.display(); // Mostra todos os habitats
  }
}
