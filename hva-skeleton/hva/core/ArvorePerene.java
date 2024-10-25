package hva.core;

public class ArvorePerene extends Arvore {
  /**
   * Constructs a new ArvorePerene object with the specified parameters.
   *
   * @param id the unique identifier for the tree
   * @param nome the name of the tree
   * @param idade the age of the tree
   * @param difB the difficulty level of the tree
   * @param estacaoInicial the initial season of the tree
   */
  public ArvorePerene(String id, String nome, int idade, int difB, Estacao estacaoInicial) {
    super(id, nome, idade, difB);
    setEstacaoState(estacaoInicial);
  }

  /**
   * Overrides the setEstacaoState method to set the state of the Estacao object
   * to a new state specific to perennials (Perene).
   *
   * @param e the Estacao object whose state is to be set
   */
  @Override
  protected void setEstacaoState(Estacao e) {
    super.setEstacaoState(e.criarEstacaoStatePerene());
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
    return String.format("ÁRVORE|%s|%s|%d|%d|%s|%s", getId(), getNome(), getIdade(), getDificuldadeBase(), "PERENE", getEstacaoState().getCicloBio());
  }
}