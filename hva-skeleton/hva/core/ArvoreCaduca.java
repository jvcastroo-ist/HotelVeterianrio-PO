package hva.core;

public class ArvoreCaduca extends Arvore {
  // Constructor
  public ArvoreCaduca(String id, String nome, int idade, int difB, Estacao estacaoInicial) {
    super(id, nome, idade, difB,estacaoInicial);
  }

  @Override
  public String toString() {
    return String.format("ÁRVORE|%s|%s|%d|%d|%s|%s", getId(), getNome(), getIdade(), getEsforcoLimpeza(getEstacaoAtual().ordinal(), 0), "CADUCA", getCiclo(getEstacaoAtual().ordinal(), 0));
  }
}




