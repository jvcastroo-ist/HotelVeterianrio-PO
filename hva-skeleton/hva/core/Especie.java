package hva.core;

import java.io.*;
import java.util.*;

public class Especie extends Responsabilidade implements Serializable{
  private final String _id;
  private final String _nome;
  private List<Animal> _animais;
  private int _numVeterinarios;

  /**
   * Constructs a new Especie object with the specified id and name.
   *
   * @param id the unique identifier for the species
   * @param nome the name of the species
   */
  public Especie(String id, String nome) {
    _id = id;
    _nome = nome;
    _animais = new ArrayList<>();    
  }

  /**
   * Retrieves the unique identifier for this instance.
   *
   * @return the unique identifier as a String.
   */
  @Override
  public String getId() {
    return _id;
  }

  /**
   * Retrieves the name of the species.
   *
   * @return the name of the species.
   */
  public String getNome() {
    return _nome;
  }

  /**
   * Retrieves the number of veterinarians associated with this species.
   *
   * @return the number of veterinarians.
   */
  public int getNumVeterinarios() {
    return _numVeterinarios;
  }

  /**
   * Adds an animal to the list of animals.
   *
   * @param a the animal to be added
   */
  public void addAnimal(Animal a){
    _animais.add(a);
  }
}