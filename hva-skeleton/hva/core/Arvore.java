package hva.core;

public abstract class Arvore implements Visualiza{
  private final String _id;
  private final String _nome;
  private int _idade;
  private final Estacao _estacaoInicial;
  // {CADUCA[estacao], PERENE[estacao]}
  private final Integer[][] _esforcoLimpeza = {{1, 2, 5, 0}, {1, 1, 1, 2}}; 
  private final String[][] _cicloBiologico = {{"GERARFOLHAS", "COMFOLHAS", "LARGARFOLHAS", "SEMFOLHAS"}, {"GERARFOLHAS", "COMFOLHAS", "COMFOLHAS", "LARGARFOLHAS"}}; 

  public Arvore(String id, String nome, int idade, Estacao estacaoInicial) {
    _id = id;
    _nome = nome;
    _idade = idade;
    _estacaoInicial = estacaoInicial;
  }

  // Getter para o ID da árvore
  public String getId() {
    return _id;
  }

  // Getter para o nome da árvore
  public String getNome() {
    return _nome;
  }

  // Getter para a idade da árvore
  public int getIdade() {
    return _idade;
  }

  // Getter para a estação inicial da árvore
  public Estacao getEstacaoInicial() {
    return _estacaoInicial;
  }

  public String getCiclo(int estacao, int tipo) {
    return _cicloBiologico[tipo][estacao];
  }

  public Integer getEsforcoLimpeza(int estacao, int tipo) {
    return _esforcoLimpeza[tipo][estacao];
  }

  // Abstract method to visualize the tree, to be implemented by subclasses
  public String visualiza(Hotel h, int tipo, String nome) {
    return String.format("ÁRVORE|%s|%s|%d|%d|%s|%s", getId(), getNome(), getIdade(), getEsforcoLimpeza(h.getEstacaoAno().ordinal(), tipo), nome, getCiclo(h.getEstacaoAno().ordinal(), tipo));
  }

}