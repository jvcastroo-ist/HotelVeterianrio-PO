package hva.app.search;

import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.core.Hotel;
import hva.core.RegistoVacina;
import hva.core.exception.CoreUnknownEmployeeKeyException;
import hva.core.exception.CoreUnknownVeterinarianKeyException;
import java.util.List;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * Show all medical acts of a given veterinarian.
 **/
class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

  DoShowMedicalActsByVeterinarian(Hotel receiver) {
    super(Label.MEDICAL_ACTS_BY_VET, receiver);
    addStringField("idVet", hva.app.employee.Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
      List<RegistoVacina> vetRegistos = _receiver.consultaAtosMedicos(stringField("idVet"));
      for(RegistoVacina rv : vetRegistos){
        _display.addLine(rv.toString());
      }
      _display.display();
    } catch (CoreUnknownEmployeeKeyException e) {
      throw new UnknownEmployeeKeyException(e.getId());
    } catch(CoreUnknownVeterinarianKeyException v){
      throw new UnknownVeterinarianKeyException(v.getId());
    }
  }
}
