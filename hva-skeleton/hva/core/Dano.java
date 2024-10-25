package hva.core;

import java.io.*;
import java.util.*;

public class Dano implements Serializable {
  private boolean _especieCerta;
  private int _valor;
  private TreeMap<Integer, String> _dano;

  /**
   * Constructs a Dano object with the specified key and species correctness flag.
   *
   * @param key the unique identifier for the Dano object
   * @param especieCerta a boolean indicating if the species is correct
   */
  public Dano(int valor, boolean especieCerta) {
    _valor = valor;
    _especieCerta = especieCerta;
    _dano = new TreeMap<>();
    _dano.put(0, "NORMAL");
    _dano.put(1, "ACIDENTE");
    _dano.put(5, "ERRO");
  }

  /**
   * Returns a string representation of the damage.
   * If the key is 0 and the species is not correct, it returns "CONFUSÂO".
   * Otherwise, it returns the value associated with the floor entry of the key in the damage map.
   *
   * @return a string representation of the damage.
   */
  @Override
  public String toString() {
    return (_valor == 0 && !_especieCerta) ? "CONFUSÃO" : _dano.floorEntry(_valor).getValue();  
  }
}