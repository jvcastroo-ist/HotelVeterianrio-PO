package hva.core;

public class ArvoreCaduca extends Arvore {
  /**
   * Constructs a new ArvoreCaduca object with the specified parameters.
   *
   * @param id the unique identifier for the tree
   * @param nome the name of the tree
   * @param idade the age of the tree
   * @param difB the difficulty level of the tree
   * @param estacaoInicial the initial season of the tree
   */
  public ArvoreCaduca(String id, String nome, int idade, int difB, Estacao estacaoInicial) {
    super(id, nome, idade, difB,estacaoInicial);
  }

  @Override
  public String toString() {
    return String.format("ÁRVORE|%s|%s|%d|%d|%s|%s", getId(), getNome(), getIdade(), getEsforcoLimpeza(getEstacaoAtual().ordinal(), 0), "CADUCA", getCiclo(getEstacaoAtual().ordinal(), 0));
  }
}




