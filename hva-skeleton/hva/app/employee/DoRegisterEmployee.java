package hva.app.employee;

import hva.app.exception.DuplicateEmployeeKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreDuplicateEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {

  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
    addStringField("id", Prompt.employeeKey());
    addStringField("name", Prompt.employeeName());
    addOptionField("employeeType", Prompt.employeeType(), "VET", "TRT");
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
        _receiver.registerEmployee(stringField("id"),
                  stringField("name"),
                  stringField("employeeType")
        );
    } catch (CoreDuplicateEmployeeKeyException e) {
      throw new DuplicateEmployeeKeyException(e.getId());
    }
  }
}
