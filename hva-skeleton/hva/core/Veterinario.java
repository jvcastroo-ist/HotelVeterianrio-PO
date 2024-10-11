package hva.core;

import hva.core.exception.CoreNoResponsibilityException;
import java.util.*;

public class Veterinario extends Funcionario{
  private List<Especie> _especies;
  private List<RegistoVacina> _registoVacinas;

  public Veterinario(String idVeterinario, String nome){
    super(idVeterinario, nome);
    _especies = new ArrayList<>();
    _registoVacinas = new ArrayList<>();
  }

  public Collection<Especie> getEspecies(){
    return _especies;
  }

  @Override
  public void operaResponsabilidade(Responsabilidade r, boolean atribui) throws CoreNoResponsibilityException{
    verifyResponsabilidade(r);
    if (atribui) {
      _especies.add((Especie)r);
    } else {
      _especies.remove((Especie)r);
    }
  }

  @Override
  public String toString() {
      return super.visualiza("VET", _especies);
  }
}