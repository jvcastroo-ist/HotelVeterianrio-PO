package hva.core;

import hva.core.exception.CoreNoResponsibilityException;
import java.io.*;
import java.util.*;

public abstract class Funcionario implements Serializable, Comparable<Funcionario>{
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
   * Verifies if the given responsibility is not null.
   *
   * @param r the responsibility to be verified
   * @throws CoreNoResponsibilityException if the responsibility is null
   */
  protected void verifyResponsabilidade(Responsabilidade r) throws CoreNoResponsibilityException {
    if(r == null)
      throw new CoreNoResponsibilityException();
  }

  /**
   * Operates on a given responsibility for the employee.
   *
   * @param r the responsibility to be operated on
   * @param atribui a boolean indicating whether to assign (true) or remove (false) the responsibility
   * @throws CoreNoResponsibilityException if the operation cannot be performed due to lack of responsibility
   */
  public abstract void operaResponsabilidade(Responsabilidade r, boolean atribui) throws CoreNoResponsibilityException;

  @Override
  public int compareTo(Funcionario f){
    return _id.compareTo(f.getId());
  }

  /**
   * Generates a formatted string representation of the employee's details and their responsibilities.
   *
   * @param tipo A string representing the type of the employee.
   * @param r A list of responsibilities associated with the employee.
   * @return A formatted string containing the type, ID, name, and responsibilities of the employee.
   */
  public String visualiza(String tipo, List<? extends Responsabilidade> r){
    return String.format("%s|%s|%s%s", tipo, getId(), getNome(), Responsabilidade.idResponsabilidade(r));
  }
}