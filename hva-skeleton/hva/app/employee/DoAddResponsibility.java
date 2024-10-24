package hva.app.employee;

import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Funcionario;
import hva.core.Hotel;
import hva.core.exception.CoreNoResponsibilityException;
import hva.core.exception.CoreUnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new responsability to an employee: species to veterinarians and 
 * habitats to zookeepers.
 **/
class DoAddResponsibility extends Command<Hotel> {

  DoAddResponsibility(Hotel receiver) {
    super(Label.ADD_RESPONSABILITY, receiver);
    addStringField("idEmployee", Prompt.employeeKey());
    addStringField("idResponsibility", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
      Funcionario employee = _receiver.getFuncionario(stringField("idEmployee"));
      String responsibilityId =  stringField("idResponsibility");
      _receiver.addResponsibility(employee, responsibilityId);
    } catch (CoreUnknownEmployeeKeyException e) {
      throw new UnknownEmployeeKeyException(e.getId());
    } catch(CoreNoResponsibilityException e){
      throw new NoResponsibilityException(e.getEmployeeKey(), e.getResponsibilityKey());  // confirmar este throw
    }
  }
}
