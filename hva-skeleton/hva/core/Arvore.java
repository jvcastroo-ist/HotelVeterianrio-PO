package hva.core;

import java.io.*;

public abstract class Arvore implements Serializable{
  private final String _id;
  private final String _nome;
  private int _idade;
  private final Estacao _estacaoAtual;
  private final int _dificuldadeBase;
  // {CADUCA[estacao], PERENE[estacao]}
  private final Integer[][] _esforcoLimpeza = {{1, 2, 5, 0}, {1, 1, 1, 2}}; 
  private final String[][] _cicloBiologico = {{"GERARFOLHAS", "COMFOLHAS", "LARGARFOLHAS", "SEMFOLHAS"}, {"GERARFOLHAS", "COMFOLHAS", "COMFOLHAS", "LARGARFOLHAS"}}; 

  public Arvore(String id, String nome, int idade, int difB, Estacao estacaoAtual) {
    _id = id;
    _nome = nome;
    _idade = idade;
    _dificuldadeBase = difB;
    _estacaoAtual = estacaoAtual;
  }

  // Getter para o ID da árvore
  public String getId() {
    return _id;
  }

  // Getter para o nome da árvore
  public String getNome() {
    return _nome;
  }

  // Getter para a idade da árvore
  public int getIdade() {
    return _idade;
  }

  // Getter para a estação inicial da árvore
  public Estacao getEstacaoAtual() {
    return _estacaoAtual;
  }

  public String getCiclo(int estacao, int tipo) {
    return _cicloBiologico[tipo][estacao];
  }

  public Integer getEsforcoLimpeza(int estacao, int tipo) {
    return _esforcoLimpeza[tipo][estacao];
  }

  // Abstract method to visualize the tree, to be implemented by subclasses
  @Override
  public abstract String toString();

}