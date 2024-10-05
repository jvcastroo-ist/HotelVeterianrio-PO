package hva.core;

import java.util.*;

public class Especie {
  private final String _id;
  private final String _nome;
  private List<Animal> _animais;
  private int _numVeterinarios;

  // Constructor
  public Especie(String id, String nome) {
    _id = id;
    _nome = nome;
  }

  public String getId() {
    return _id;
  }

  public String getNome() {
    return _nome;
  }

  public int getNumVeterinarios() {
    return _numVeterinarios;
  }
}