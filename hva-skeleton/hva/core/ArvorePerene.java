package hva.core;

public class ArvorePerene extends Arvore {
  // Constructor
  public ArvorePerene(String id, String nome, int idade, int difB, Estacao estacaoInicial) {
    super(id, nome, idade, difB, estacaoInicial);
  }

  @Override
  public String toString() {
    return String.format("ÁRVORE|%s|%s|%d|%d|%s|%s", getId(), getNome(), getIdade(), getEsforcoLimpeza(getEstacaoAtual().ordinal(), 1), "PERENE", getCiclo(getEstacaoAtual().ordinal(), 1));
  }
}