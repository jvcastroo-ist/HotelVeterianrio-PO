package hva.app.employee;

import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreNoResponsibilityException;
import hva.core.exception.CoreUnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Remove a given responsability from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel> {

  DoRemoveResponsibility(Hotel receiver) {
    super(Label.REMOVE_RESPONSABILITY, receiver);
    addStringField("idEmployee", Prompt.employeeKey());
    addStringField("idResponsibility", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
      String employeeId = stringField("idEmployee");
      String responsibilityId = stringField("idResponsibility");
      _receiver.removeResponsibility(employeeId, responsibilityId);
    } catch(CoreUnknownEmployeeKeyException e){
      throw new UnknownEmployeeKeyException(e.getId());
    }catch(CoreNoResponsibilityException e){
      String employeeId = stringField("idEmployee");
      String responsibilityId = stringField("idResponsibility");
      throw new NoResponsibilityException(employeeId, responsibilityId);
    }
  }
}
