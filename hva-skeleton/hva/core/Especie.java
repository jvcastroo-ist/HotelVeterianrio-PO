package hva.core;

import java.io.*;
import java.util.*;

public class Especie extends Responsabilidade implements Serializable{
  private final String _id;
  private final String _nome;
  private List<Animal> _animais;
  private int _numVeterinarios;

  // Constructor
  public Especie(String id, String nome) {
    _id = id;
    _nome = nome;
    _animais = new ArrayList<>();    
  }

  @Override
  public String getId() {
    return _id;
  }

  public String getNome() {
    return _nome;
  }

  public int getNumVeterinarios() {
    return _numVeterinarios;
  }

  public void addAnimal(Animal a){
    _animais.add(a);
  }
}