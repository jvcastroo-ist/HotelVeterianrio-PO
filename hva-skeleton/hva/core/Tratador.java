package hva.core;
import hva.core.exception.CoreNoResponsibilityException;
import java.util.*;

public class Tratador extends Funcionario{
  private List<Habitat> _habitats;

  /**
   * Constructs a new Tratador (handler) with the specified ID and name.
   *
   * @param idTratador the unique identifier for the handler
   * @param nome the name of the handler
   */
  public Tratador(String idTratador, String nome){
    super(idTratador, nome);
    _habitats = new ArrayList<>();
  }

  /**
   * Retrieves the collection of habitats associated with this handler.
   *
   * @return a collection of Habitat objects.
   */
  public Collection<Habitat> getHabitats(){
    return _habitats;
  }

  /**
   * Operates on the given responsibility by either assigning or removing it.
   *
   * @param r the responsibility to be operated on
   * @param atribui if true, the responsibility will be assigned; if false, it will be removed
   * @throws CoreNoResponsibilityException if the responsibility is not valid
   */
  @Override
  public void operaResponsabilidade(Responsabilidade r, boolean atribui) throws CoreNoResponsibilityException{
    verifyResponsabilidade(r);
    if (atribui) {
      _habitats.add((Habitat)r);
    } else {
      _habitats.remove((Habitat)r);
    }
  }

  /**
   * Returns a string representation of the Tratador object.
   * This method overrides the default toString method to provide
   * a custom string format that includes the identifier "TRT" and
   * the habitats associated with the Tratador.
   *
   * @return A string representation of the Tratador object.
   */
  @Override
  public String toString() {
      return super.visualiza("TRT", _habitats);
  }
}