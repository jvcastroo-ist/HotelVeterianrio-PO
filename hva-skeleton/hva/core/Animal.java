package hva.core;

import java.io.*;
import java.util.*;

public class Animal implements Serializable, Satisfacao, Comparable<Animal> {
  private final String _id;
  private final String _nome;
  private final Especie _especie;
  private Habitat _habitat;
  private int _adequacao;
  private List<RegistoVacina> _registoVacinacao;
            
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
   * Retrieves the habitat of the animal.
   *
   * @return the habitat of the animal.
   */
  public Habitat getHabitat() {
    return _habitat;
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
   * Calculates the satisfaction level of the animal based on various factors.
   *
   * @return the satisfaction level as a double, calculated using the following formula:
   *         (20 + 3 * especieIgual() - 2 * especieDiferente + espacoMedio + _adequacao)
   *         where:
   *         - especieIgual() is the number of animals of the same species in the habitat.
   *         - especieDiferente is the number of different species in the habitat.
   *         - espacoMedio is the average space available per animal in the habitat.
   *         - _adequacao is an additional adequacy factor.
   */
  @Override
  public double satisfacao() {
    // Numero de especies diferentes no habitat
    int especieDiferente = _habitat.getAnimals().size() - (especieIgual() + 1);
    // AREA/POPULACAO
    double espacoMedio = _habitat.getArea()/_habitat.getAnimals().size();
    // satisfacao arredondada ao inteiro mais proximo
    return (20 + 3*especieIgual() - 2*especieDiferente + espacoMedio + _adequacao);
  }

  /**
   * Calculates the number of animals of the same species in the habitat.
   *
   * @return the number of animals of the same species minus one(itself).
   */
  public int especieIgual() {
    return _habitat.getAnimals(_especie).size() - 1;
  }

  /**
   * Sets the adequacy value for the animal.
   *
   * @param valor the adequacy value to be set
   */
  public void setAdequacao(int valor) {
    _adequacao = valor;
  }

  /**
   * Adds a vaccination record to the animal's vaccination registry.
   *
   * @param rg the vaccination record to be added
   */
  public void addRegisto(RegistoVacina rg) {
    _registoVacinacao.add(rg);
  }

  /**
   * Transfers the animal to a new habitat.
   *
   * @param novoHabitat the new habitat to which the animal will be transferred
   */
  public void transfereAnimal(Habitat novoHabitat) {
    _habitat = novoHabitat;
    _adequacao = 0;
  }

  @Override
  public int compareTo(Animal a){
    return _id.compareTo(a.getId());
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
