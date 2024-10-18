package hva.core;

import hva.core.exception.CoreNoResponsibilityException;
import java.io.*;
import java.util.*;

public abstract class Funcionario implements Serializable, Satisfacao {
  private final String _id;
  private final String _nome;

  /**
   * Constructs a new Funcionario with the specified ID and name.
   *
   * @param idFuncionario the unique identifier for the Funcionario
   * @param nome the name of the Funcionario
   */
  public Funcionario(String idFuncionario, String nome) {
    _id = idFuncionario;
    _nome = nome;
  }

  /**
   * Retrieves the unique identifier of the employee.
   *
   * @return the unique identifier of the employee.
   */
  public String getId(){
    return _id;
  }

  /**
   * Retrieves the name of the employee.
   *
   * @return the name of the employee.
   */
  public String getNome(){
    return _nome;
  }

  /**
   * Operates on a given responsibility for the employee.
   *
   * @param r the responsibility to be operated on
   * @param atribui a boolean indicating whether to assign (true) or remove (false) the responsibility
   * @throws CoreNoResponsibilityException if the operation cannot be performed due to lack of responsibility
   */
  public abstract void operaResponsabilidade(Responsabilidade r, boolean atribui);
  
  @Override
  public abstract double satisfacao();

  /**
   * Generates a concatenated string of IDs from a collection of Responsabilidade objects.
   *
   * @param R a collection of objects that extend the Responsabilidade class
   * @return a string containing the IDs of the Responsabilidade objects, separated by commas and prefixed with a "|". 
   *         Returns an empty string if the collection is empty.
   */
  public String idResponsabilidade(Collection<? extends Responsabilidade> R) {
    List<String> ids = new ArrayList<>();
    if (R.isEmpty()) {return "";}
    for (Responsabilidade r : R) { 
      ids.add(r.getId());
    }
    return "|" + String.join(",", ids);
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