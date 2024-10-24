package hva.app.main;

import hva.core.HotelManager;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Command for advancing the season of the system.
 **/
class DoAdvanceSeason extends Command<HotelManager> {
  DoAdvanceSeason(HotelManager receiver) {
    super(Label.ADVANCE_SEASON, receiver);
    //FIXME add command fields
  }

  @Override
  protected final void execute() {
    int numEstacao = _receiver.getHotel().avancaEstacao();
    _display.popup(numEstacao);
  }
}
