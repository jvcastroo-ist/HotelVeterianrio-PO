package hva.core;

public abstract class Arvore implements Visualiza {
  private final String _id;
  private final String _nome;
  private int _idade;
  private final TipoArvore _tipo;
  private final Estacao _estacaoInicial;
  private final int _dificuldadeBase;

  public Arvore(String id, String nome, int idade, TipoArvore tipo, Estacao estacaoInicial, int dificuldadeBase) {
    _id = id;
    _nome = nome;
    _idade = idade;
    _tipo = tipo;
    _estacaoInicial = estacaoInicial;
    _dificuldadeBase = dificuldadeBase;
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

  // Getter para o tipo da árvore
  public TipoArvore getTipo() {
    return _tipo;
  }

  // Getter para a estação inicial da árvore
  public Estacao getEstacaoInicial() {
    return _estacaoInicial;
  }

  // Getter para o nível de dificuldade base da árvore
  public int getDificuldadeBase() {
    return _dificuldadeBase;
  }

  // Abstract method to visualize the tree, to be implemented by subclasses
  public String visualiza(Hotel h) {
    return String.format("ÁRVORE|%s|%s|%s|%s|%s|%s", getId(), getNome(), getIdade(), getDificuldadeBase(), getTipo(), h.getEstacaoAno().getCicloBiologico(getTipo()));
  }

}