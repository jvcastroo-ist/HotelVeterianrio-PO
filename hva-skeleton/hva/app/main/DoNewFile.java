package hva.app.main;

import hva.core.HotelManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for creating a new zoo hotel.
 **/
class DoNewFile extends Command<HotelManager> {
  DoNewFile(HotelManager receiver) {
    super(Label.NEW_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    if (_receiver.getHotel().isModified() && Form.confirm(Prompt.saveBeforeExit())) {
      // Save before creating a new hotel
     DoSaveFile ds = new DoSaveFile(_receiver);
      ds.execute();
    }
    _receiver.newHotel();  // Reset the hotel manager's state
  }
}
