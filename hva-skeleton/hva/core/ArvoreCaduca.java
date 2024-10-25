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
    super(id, nome, idade, difB);
    setEstacaoState(estacaoInicial);
  }

  /**
   * Overrides the setEstacaoState method to set the state of the season
   * for a deciduous tree. This method calls the superclass's setEstacaoState
   * method with a new state created specifically for deciduous trees.
   *
   * @param e the Estacao object representing the current season
   */
  @Override
  protected void setEstacaoState(Estacao e) {
    super.setEstacaoState(e.criarEstacaoStateCaduca());;
  }

  /**
   * Calculates the cleaning effort for the current season.
   * This method overrides the superclass method to include seasonal effort adjustment.
   *
   * @return the adjusted cleaning effort based on the current season.
   */
  @Override
  public double getEsforcoLimpeza() {
    return super.getEsforcoLimpeza()*getEstacaoState().getEsforcoSazonal();
  }

  @Override
  public String toString() {
    return String.format("ÁRVORE|%s|%s|%d|%d|%s|%s", getId(), getNome(), getIdade(), getDificuldadeBase(), "CADUCA", getEstacaoState().getCicloBio());
  }
}




