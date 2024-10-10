package hva.core;
import java.util.*;

import hva.core.exception.CoreNoResponsibilityException;

public class Tratador extends Funcionario{
  private List<Habitat> _habitats;

  public Tratador(String idTratador, String nome){
    super(idTratador, nome);
    _habitats = new ArrayList<>();
  }

  public Collection<Habitat> getHabitats(){
    return _habitats;
  }

  @Override
  public void operaResponsabilidade(Responsabilidade r, boolean atribui) throws CoreNoResponsibilityException{
    verifyResponsabilidade(r);
    if (atribui) {
      _habitats.add((Habitat)r);
    } else {
      _habitats.remove((Habitat)r);
    }
  }

   // Fazer derivado da classe abstrata
  @Override
  public String visualiza(Hotel h) {
      return super.visualiza(h, "TRATADOR", _habitats);
  }
}