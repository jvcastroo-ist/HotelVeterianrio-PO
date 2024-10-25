package hva.app.employee;

import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreUnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show the satisfaction of a given employee.
 **/
class DoShowSatisfactionOfEmployee extends Command<Hotel> {

  DoShowSatisfactionOfEmployee(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
    addStringField("idEmployee", Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
      _display.addLine((int)Math.round(_receiver.calculaSatisfacaoFuncionario(stringField("idEmployee"))));
      _display.display();
    } catch (CoreUnknownEmployeeKeyException e) {
      throw new UnknownEmployeeKeyException(e.getId());
    }
  }
}
