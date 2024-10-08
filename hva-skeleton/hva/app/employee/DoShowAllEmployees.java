package hva.app.employee;

import hva.core.Funcionario;
import hva.core.Hotel;
import java.util.List;
import java.util.Map;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Show all employees of this zoo hotel.
 **/
class DoShowAllEmployees extends Command<Hotel> {

  DoShowAllEmployees(Hotel receiver) {
    super(Label.SHOW_ALL_EMPLOYEES, receiver);
  }
  
  @Override
  protected void execute() {
    //FIXME implement command

    // Obtém todos os funcionários do hotel
    Map<String, Funcionario> funcionarioMap = _receiver.getFuncionarios();

    // Ordena os animais usando o método sortIds
    List<Funcionario> sortedFuncionarios = (List<Funcionario>) _receiver.sortIds(funcionarioMap);

    for (Funcionario funcionario : sortedFuncionarios) {
      _display.addLine(funcionario.visualiza(_receiver)); // Adiciona cada funcionário em uma nova linha
    }
  
    _display.display(); // Mostra todos os funcionários
  }
}
