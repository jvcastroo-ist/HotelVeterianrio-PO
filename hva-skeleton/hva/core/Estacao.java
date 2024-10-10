package hva.core;

import java.io.*;

public enum Estacao implements Serializable{
  PRIMAVERA,
  VERÃO, 
  OUTONO,
  INVERNO;

  Estacao() {}

  // Método que retorna a próxima estação no ciclo anual
  public Estacao proximaEstacao() {
      int nextOrdinal = (this.ordinal() + 1) % Estacao.values().length;  // Garante que depois de INVERNO volte a PRIMAVERA
      return Estacao.values()[nextOrdinal];
  }

  public static Estacao fromNumero(int numero) {
      Estacao[] estacoes = Estacao.values();
      return estacoes[numero];
  }
}

    

