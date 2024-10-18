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

  /**
   * Calculates the cleaning effort for the current season.
   * This method overrides the superclass method to include seasonal effort adjustment.
   *
   * @return the adjusted cleaning effort based on the current season.
   */
  @Override
  public double getEsforcoLimpeza() {
    return super.getEsforcoLimpeza()*getEsforcoSazonal(getEstacaoAtual().ordinal(), 0);
  }

  @Override
  public String toString() {
    return String.format("ÁRVORE|%s|%s|%d|%d|%s|%s", getId(), getNome(), getIdade(), getDificuldadeBase(), "CADUCA", getCiclo(getEstacaoAtual().ordinal(), 0));
  }
}




