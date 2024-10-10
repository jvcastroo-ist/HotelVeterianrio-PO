package hva.core;

import java.util.*;
import java.io.*;

public class Dano implements Serializable {
  private boolean _especieCerta;
  private int _key;
  private TreeMap<Integer, String> _dano;

  public Dano(int key, boolean especieCerta) {
    _key = key;
    _especieCerta = especieCerta;
    _dano = new TreeMap<>();
    _dano.put(0, "NADA");
    _dano.put(1, "ACIDENTE");
    _dano.put(5, "ERRO");
  }

  public String toString() {
    return (_key == 0 && !_especieCerta) ? "ACIDENTE" : _dano.floorEntry(_key).getValue();  
  }
}