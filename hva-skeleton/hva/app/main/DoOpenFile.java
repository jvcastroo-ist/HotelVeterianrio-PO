package hva.app.main;

import hva.app.exception.FileOpenFailedException;
import hva.core.HotelManager;
import hva.core.exception.UnavailableFileException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
    addStringField("fileName", Prompt.openFile());
  }

  /**
   * Executes the command to open a file.
   * 
   * This method retrieves the file name from the input field and attempts to load
   * the file using the receiver. If the file is unavailable, it throws a 
   * FileOpenFailedException.
   * 
   * @throws CommandException if there is an error executing the command
   */
  @Override
  protected final void execute() throws CommandException {
    String fileName = stringField("fileName");
      try {
        _receiver.load(fileName);
      } catch (UnavailableFileException efe) {
        throw new FileOpenFailedException(efe);
      }
  }
}

//efe.getFilename()