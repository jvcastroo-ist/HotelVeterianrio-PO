package hva.core;
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

  public List<Habitat> getHabitats(){
    return sort(_habitats);
  }

  @Override
  public void operaResponsabilidade(Responsabilidade r, boolean atribui) {
    if (atribui) {
      _habitats.add((Habitat)r);
    } else {
      _habitats.remove((Habitat)r);
    }
    r.operaFuncionario(atribui);
  }

  /**
   * Calculates the satisfaction level of the handler based on the work done in each habitat.
   * The satisfaction is computed by summing the ratio of work done in each habitat to the number of employees,
   * subtracting this sum from 300, and then rounding the result to the nearest whole number.
   *
   * @return the satisfaction level as a long value.
   */
  @Override
  public double satisfacao() {
    double sum = 0;
    for(Habitat h : _habitats) {
      sum += h.trabalhoNoHabitat()/h.getNumFuncionarios();
    }
    sum = 300 - sum;
    return sum;
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
      return super.visualiza("TRT", getHabitats());
  }
}