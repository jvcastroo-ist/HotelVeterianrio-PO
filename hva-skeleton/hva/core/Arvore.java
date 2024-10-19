package hva.core;

import java.io.*;

public abstract class Arvore extends HotelEntity implements Serializable{
  private float _idade;
  private  Estacao _estacaoAtual;
  private final int _dificuldadeBase;
  // {CADUCA[estacao], PERENE[estacao]}
  private final Integer[][] _esforcoSazonal = {{1, 2, 5, 0}, {1, 1, 1, 2}}; 
  private final String[][] _cicloBiologico = {{"GERARFOLHAS", "COMFOLHAS", "LARGARFOLHAS", "SEMFOLHAS"}, {"GERARFOLHAS", "COMFOLHAS", "COMFOLHAS", "LARGARFOLHAS"}}; 

  /**
   * Constructs an Arvore object with the specified parameters.
   *
   * @param id the unique identifier for the tree
   * @param nome the name of the tree
   * @param idade the age of the tree
   * @param difB the base difficulty level associated with the tree
   * @param estacaoAtual the current season associated with the tree
   */
  public Arvore(String id, String nome, int idade, int difB, Estacao estacaoAtual) {
    super(id, nome);
    _idade = idade;
    _dificuldadeBase = difB;
    _estacaoAtual = estacaoAtual;
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
   * Retrieves the current season associated with the tree.
   *
   * @return the current season.
   */
  public Estacao getEstacaoAtual() {
    return _estacaoAtual;
  }

  /**
   * Retrieves the biological cycle of a tree based on the given season and type.
   *
   * @param estacao the season index
   * @param tipo the type index
   * @return the biological cycle corresponding to the specified season and type
   */
  public String getCiclo(int tipo) {
    return _cicloBiologico[tipo][_estacaoAtual.ordinal()];
  }

  /**
   * Retrieves the cleaning effort required for a specific type and season.
   *
   * @param estacao the season index
   * @param tipo the type index
   * @return the cleaning effort for the specified type and season
   */
  public Integer getEsforcoSazonal(int tipo) {
    return _esforcoSazonal[tipo][_estacaoAtual.ordinal()];
  }

  // a versão realmente utilizada desse metodo é o override das classes filho
  public double getEsforcoLimpeza() {
    return getDificuldadeBase()*Math.log(getIdade()+1);
  }

  public void aumentaIdade() {
    _idade += 0.25;
  }

  public void setEstacao(Estacao e) {
    _estacaoAtual = e;
  }

  @Override
  public abstract String toString();

}