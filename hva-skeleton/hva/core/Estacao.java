package hva.core;

public enum Estacao {
  PRIMAVERA,
  VERÃO, 
  OUTONO,
  INVERNO;

  Estacao() {}

  // Método que retorna a próxima estação no ciclo anual
  /**
   * Returns the next season in the sequence. The sequence follows the order of the enum constants.
   * If the current season is the last one in the sequence, it wraps around to the first season.
   *
   * @return the next season in the sequence
   */
  public Estacao proximaEstacao() {
      int nextOrdinal = (this.ordinal() + 1) % Estacao.values().length;  // Garante que depois de INVERNO volte a PRIMAVERA
      return Estacao.values()[nextOrdinal];
  }

  /**
   * Converts an integer to its corresponding Estacao enum value.
   *
   * @param numero the integer representing the ordinal of the Estacao enum.
   * @return the Estacao enum value corresponding to the given integer.
   * @throws ArrayIndexOutOfBoundsException if the provided integer is out of the range of the enum values.
   */
  public static Estacao fromNumero(int numero) {
      Estacao[] estacoes = Estacao.values();
      return estacoes[numero];
  }
}

    

