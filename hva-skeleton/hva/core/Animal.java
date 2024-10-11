package hva.core;

import java.io.*;
import java.util.*;

public class Animal implements Serializable {
  private final String _id;
  private final String _nome;
  private final Especie _especie;
  private Habitat _habitat;
  private List<RegistoVacina> _registoVacinacao;
  private int _adequacao;
            
  /**
  * Constructs a new Animal with the specified id, name, species, and habitat.
  *
  * @param id the unique identifier for the animal
  * @param nome the name of the animal
  * @param especie the species of the animal
  * @param habitat the habitat of the animal
  */
  public Animal(String id, String nome, Especie especie, Habitat habitat) {
    _id = id;
    _nome = nome;
    _especie = especie;
    _habitat = habitat;
    _registoVacinacao = new ArrayList<>();
    }

  /**
  * Retrieves the unique identifier of the animal.
  *
  * @return the unique identifier (_id) of the animal.
  */
  public String getId() {
    return _id;
  }

  /**
  * Retrieves the name of the animal.
  *
  * @return the name of the animal.
  */
  public String getNome() {
    return _nome;
  }

  /**
  * Retrieves the adequacy value of the animal.
  *
  * @return the adequacy value as an integer.
  */
  public int getAdequacao() {
    return _adequacao;
  }

  /**
   * Retrieves the species of the animal.
   *
   * @return the species of the animal.
   */
  public Especie getEspecie() {
    return _especie;
  }

  /**
   * Retrieves the vaccination record of the animal.
   *
   * @return A string representation of the vaccination record. If the record is empty, returns "VOID".
   *         Otherwise, returns a comma-separated list of damages from the vaccination records.
   */
  public String getRegistoVacinacao() {
    List<String> danos = new ArrayList<>();
    if (_registoVacinacao.isEmpty()) {return "VOID";}
    for (RegistoVacina rv : _registoVacinacao) {
      danos.add(rv.getDano().toString());
    }
      return String.join(",", danos);
  }

  /**
   * Retrieves the habitat of the animal.
   *
   * @return the habitat of the animal.
   */
  public Habitat getHabitat() {
    return _habitat;
  }

  /**
   * Returns a string representation of the Animal object.
   * The format of the returned string is "ANIMAL|id|name|speciesId|vaccinationRecord|habitatId".
   *
   * @return A string representation of the Animal object.
   */
  @Override
  public String toString() {
    return String.format("ANIMAL|%s|%s|%s|%s|%s", getId(), getNome(), getEspecie().getId(), getRegistoVacinacao(), getHabitat().getId());
  }
}
