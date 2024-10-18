package hva.core;

import java.util.*;
import java.io.*;

public abstract class Responsabilidade implements Serializable{
  private final String _id;
  private final String _nome;
  private int _numFuncionarios;

  protected Responsabilidade(String id, String nome) {
    _id = id;
    _nome = nome;
  }

  /**
   * Retrieves the unique identifier for the responsibility
   *
   * @return the unique identifier as a String.
   */
  public String getId() {
    return _id;
  }

   /**
   * Retrieves the name of the responsibility
   *
   * @return the name of the responsibility.
   */
  public String getNome() {
    return _nome;
  }

  /**
   * Retrieves the number of employees associated with this responsability.
   *
   * @return the number of employees.
   */
  public int getNumFuncionarios() {
    return _numFuncionarios;
  }

   
  /**
   * Updates the number of employees based on the given parameter.
   *
   * @param adiciona If true, increments the number of employees by 1; 
   *                 if false, decrements the number of employees by 1.
   */
  public void operaFuncionario(boolean adiciona) {
    _numFuncionarios += (adiciona) ? 1 : -1;
  }
}
