package hva.core;

public class ArvorePerene extends Arvore {

  // Constructor
  public ArvorePerene(String id, String nome, int idade, Estacao estacaoInicial, int dificuldadeBase) {
    super(id, nome, idade, TipoArvore.PERENE, estacaoInicial, dificuldadeBase);
  }
}