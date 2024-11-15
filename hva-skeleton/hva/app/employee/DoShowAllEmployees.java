package hva.app.employee;

import hva.core.Funcionario;
import hva.core.Hotel;
import java.util.List;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all employees of this zoo hotel.
 **/
class DoShowAllEmployees extends Command<Hotel> {

  DoShowAllEmployees(Hotel receiver) {
    super(Label.SHOW_ALL_EMPLOYEES, receiver);
  }
  
  /**
   * Executes the action to display all employees.
   * This method triggers the display of all employees by calling the 
   * visualizaTodosFuncionarios method on the receiver and showing the result 
   * in a popup.
   */
  @Override
  protected void execute() {
    List<Funcionario> employees = _receiver.sortIds(_receiver.getEmployees());
    for (Funcionario f : employees) {
      _display.addLine(f.toString());
    }
    _display.display();
  }
}
