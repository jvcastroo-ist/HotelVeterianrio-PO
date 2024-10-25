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
    setSatisfacao(new SatisfacaoTratador(this));
  }

  public List<Habitat> getHabitats(){
    return sort(_habitats);
  }



  //  DESCONTINUADO - DESCONTINUADO - DESCONTINUADO
  @Override
  public void operaResponsabilidade(Responsabilidade r, boolean atribui) {
    if (atribui) {
      _habitats.add((Habitat)r);
    } else {
      _habitats.remove((Habitat)r);
    }
    r.operaFuncionario(atribui);
  }

  @Override
  public String getType(){
    return "TRT";
  }

  /**
   * Checks if the given responsibility is assigned to this handler.
   *
   * @param r the responsibility to check
   * @return true if the responsibility is assigned, false otherwise
   */
  @Override
  public boolean isResponsabilidadeAtribuida(Responsabilidade r){
    return _habitats.contains((Habitat)r);
  }

  /**
   * Assigns a responsibility to the handler if it has not already been assigned.
   * If the responsibility is not already assigned, it adds the habitat to the list
   * of habitats and updates the responsibility status.
   *
   * @param r the responsibility to be assigned
   */
  @Override
  public void atribuiResponsabilidade(Responsabilidade r){
    if(!(isResponsabilidadeAtribuida(r))){
      _habitats.add((Habitat)r);
      r.operaFuncionario(true); // add +1 to responsability TRT
    }
  }

  /**
   * Removes the given responsibility from the handler.
   * 
   * @param r the responsibility to be removed
   */
  @Override
  public void retiraResponsabilidade(Responsabilidade r){
    _habitats.remove((Habitat)r);  
    r.operaFuncionario(false); // removes -1 to responsability TRT
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