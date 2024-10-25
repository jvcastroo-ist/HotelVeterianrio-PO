package hva.core;

public abstract class Arvore extends HotelEntity {
  private float _idade;
  private final int _dificuldadeBase;
  private EstacaoState _estacaoState;

  /**
   * Constructs an Arvore object with the specified parameters.
   *
   * @param id the unique identifier for the tree
   * @param nome the name of the tree
   * @param idade the age of the tree
   * @param difB the base difficulty level associated with the tree
   * @param estacaoAtual the current season associated with the tree
   */
  protected Arvore(String id, String nome, int idade, int difB) {
    super(id, nome);
    _idade = idade;
    _dificuldadeBase = difB;
  }

  /**
   * Retrieves the age of the tree.
   *
   * @return the age of the tree.
   */
  public int getIdade() {
    return (int) Math.floor(_idade);
  }

  /**
   * Retrieves the base difficulty level.
   *
   * @return the base difficulty level as an integer.
   */
  public int getDificuldadeBase() {
    return _dificuldadeBase;
  }

  /**
   * Retrieves the current state of the station.
   *
   * @return the current state of the station as an {@link EstacaoState} object.
   */
  public EstacaoState getEstacaoState() {
    return _estacaoState;
  }

  /**
   * Sets the current state of the station.
   *
   * @param e the new state to be set for the station
   */
  public void setEstacaoState(EstacaoState e) {
    _estacaoState = e;
  }

  protected abstract void setEstacaoState(Estacao e);

  /**
   * Changes the current season state of the object.
   *
   * @param estacao the new season state to be set
   */
  public void mudarEstacao(EstacaoState estacao) {
    _estacaoState = estacao;
  }

  /**
   * Retrieves the biological cycle information from the current station state.
   *
   * @return A string representing the biological cycle.
   */
  public String getCicloBio() {
    return _estacaoState.getCicloBio();
  }

  /**
   * Retrieves the seasonal effort from the current state of the season.
   *
   * @return the seasonal effort as an integer.
   */
  public int getEsforcoSazonal() {
    return _estacaoState.getEsforcoSazonal();
  }

  // a versão realmente utilizada desse metodo é o override das classes filho
  public double getEsforcoLimpeza() {
    return getDificuldadeBase()*Math.log(getIdade()+1);
  }

  /**
   * Increases the age of the entity by 0.25.
   */
  public void aumentaIdade() {
    _idade += 0.25;
  }

  @Override
  public abstract String toString();

}