package hva.core;

public class ArvoreCaduca extends Arvore {

  // Constructor
  public ArvoreCaduca(String id, String nome, int idade, Estacao estacaoInicial, int dificuldadeBase) {
    super(id, nome, idade, TipoArvore.CADUCA, estacaoInicial, dificuldadeBase);
  }
}