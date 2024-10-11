package hva.core;

import hva.core.exception.CoreNoResponsibilityException;
import java.io.*;
import java.util.*;

public abstract class Funcionario implements Serializable{
  private final String _id;
  private final String _nome;

  // Constructor
  public Funcionario(String idFuncionario, String nome) {
    _id = idFuncionario;
    _nome = nome;
  }

  public String getId(){
    return _id;
  }

  public String getNome(){
    return _nome;
  }

  protected void verifyResponsabilidade(Responsabilidade r) throws CoreNoResponsibilityException {
    if(r == null)
      throw new CoreNoResponsibilityException();
  }

  public abstract void operaResponsabilidade(Responsabilidade r, boolean atribui) throws CoreNoResponsibilityException;

  public String visualiza(String tipo, List<? extends Responsabilidade> r){
    return String.format("%s|%s|%s%s", tipo, getId(), getNome(), Responsabilidade.idResponsabilidade(r));
  }



  
    /*
    + satisfaçao(): int
    + visualizaFuncionario(): void
    + operaResponsabilidade<R>(<R>, lista: ArrayList<R>,  atribui: boolean): void

     */

}