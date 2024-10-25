package hva.core;

import java.util.*;

public abstract class Funcionario extends HotelEntity implements Comparable<Funcionario>{
  private Satisfacao _satisfacao;

  /**
   * Constructs a new Funcionario with the specified ID and name.
   *
   * @param idFuncionario the unique identifier for the Funcionario
   * @param nome the name of the Funcionario
   */
  protected Funcionario(String id, String nome) {
   super(id, nome);
  }
  
  public abstract void atribuiResponsabilidade(Responsabilidade r);

  public abstract void retiraResponsabilidade(Responsabilidade r);
  
  public abstract boolean isResponsabilidadeAtribuida(Responsabilidade r);
  

  /**
   * Retrieves the satisfaction level of the employee.
   *
   * @return the satisfaction level of the employee as a {@link Satisfacao} object.
   */
  public Satisfacao getSatisfacao(){
    return _satisfacao;
  }

  /**
   * Sets the satisfaction level of the employee.
   *
   * @param s the satisfaction level to be set
   */
  public void setSatisfacao(Satisfacao s) {
    _satisfacao = s;
  }

  /**
   * Generates a concatenated string of IDs from a collection of Responsabilidade objects.
   *
   * @param R a collection of objects that extend the Responsabilidade class
   * @return a string containing the IDs of the Responsabilidade objects, separated by commas and prefixed with a "|". 
   *         Returns an empty string if the collection is empty.
   */
  private String idResponsabilidade(Collection<? extends Responsabilidade> R) {
    List<String> ids = new ArrayList<>();
    if (R.isEmpty()) {return "";}
    for (Responsabilidade r : R) { 
      ids.add(r.getId());
    }
    return "|" + String.join(",", ids);
  }

  @Override
  public int compareTo(Funcionario f){
    return getId().compareToIgnoreCase(f.getId());
  }

  /**
   * Generates a formatted string representation of the employee's details and their responsibilities.
   *
   * @param tipo A string representing the type of the employee.
   * @param r A list of responsibilities associated with the employee.
   * @return A formatted string containing the type, ID, name, and responsibilities of the employee.
   */
  public String visualiza(String tipo, List<? extends Responsabilidade> r){
    return String.format("%s|%s|%s%s", tipo, getId(), getNome(), idResponsabilidade(r));
  }
}