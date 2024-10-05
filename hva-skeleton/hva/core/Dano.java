package hva.core;

public enum Dano {
  NORMAL,
  CONFUSÃO,
  ACIDENTE,
  ERRO;

  public static Dano determinarDano(int valor, boolean especieCerta) {
      if (valor == 0) {
          return especieCerta ? NORMAL : CONFUSÃO;
      } else if (valor >= 1 && valor <= 4) {
          return ACIDENTE;
      } else if (valor >= 5) {
          return ERRO;
      } else {
          throw new IllegalArgumentException("Valor de dano inválido");
      }
  }
}
