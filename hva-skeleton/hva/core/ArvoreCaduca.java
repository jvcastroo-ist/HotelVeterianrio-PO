package hva.core;

public class ArvoreCaduca extends Arvore {
  // Constructor
  public ArvoreCaduca(String id, String nome, int idade, Estacao estacaoInicial) {
    super(id, nome, idade, estacaoInicial);
  }

  public String visualiza(Hotel h) {
    return super.visualiza(h, 0, "CADUCA");
  }
}