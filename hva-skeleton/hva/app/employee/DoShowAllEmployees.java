package hva.app.employee;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

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
    _display.popup(_receiver.visualizaTodosFuncionarios()); // Mostra todos os funcionários
  }
}
