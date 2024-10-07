package hva.core;

public class ArvorePerene extends Arvore {
  private Integer[] _dificuldadeLimpeza = {1, 2, 5, 0};
  private String[] _cicloBiologico = {"GERARFOLHAS", "COMFOLHAS", "LARGARFOLHAS", "SEMFOLHAS"};
  

  // Constructor
  public ArvorePerene(String id, String nome, int idade, Estacao estacaoInicial, int dificuldadeBase) {
    super(id, nome, idade, TipoArvore.PERENE, estacaoInicial, dificuldadeBase);
  }
}