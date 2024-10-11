package hva.app.main;

import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import java.io.IOException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
  }

  /**
   * Executes the save file command.
   * Attempts to save the current state using the receiver's save method.
   * If the file association is missing, it prompts to save as a new file.
   * Catches and handles IOExceptions that may occur during the process.
   *
   * @throws CommandException if an error occurs during command execution.
   */
  @Override
  protected final void execute() throws CommandException{
    try {
        try {
          _receiver.save();
        } catch (MissingFileAssociationException e) {
          saveAs();
        }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Saves the current state to a file specified by the user.
   * If the file association is missing, it will prompt the user again.
   * 
   * @throws IOException if an I/O error occurs during the save operation.
   */
  private void saveAs() throws IOException {
    try {
        _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
    } catch (MissingFileAssociationException e) {
      saveAs();
    }
  }

}
