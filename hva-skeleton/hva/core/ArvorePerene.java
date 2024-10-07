package hva.core;

public class ArvorePerene extends Arvore {
  // Constructor
  public ArvorePerene(String id, String nome, int idade, Estacao estacaoInicial) {
    super(id, nome, idade, estacaoInicial);
  }

  public String visualiza(Hotel h) {
    return super.visualiza(h, 1, "PERENE");
  }
}