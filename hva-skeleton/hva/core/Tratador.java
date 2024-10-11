package hva.core;
import hva.core.exception.CoreNoResponsibilityException;
import java.util.*;

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
  public String toString() {
      return super.visualiza("TRT", _habitats);
  }
}