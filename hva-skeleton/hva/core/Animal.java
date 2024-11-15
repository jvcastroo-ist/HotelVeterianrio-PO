package hva.core;

import java.util.*;

public class Animal extends HotelEntity implements Comparable<Animal> {
  private final Especie _especie;
  private Habitat _habitat;
  private int _adequacao;
  private Satisfacao _satisfacao;
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
    super(id, nome);
    _especie = especie;
    _habitat = habitat;
    _adequacao = 0;
    _registoVacinacao = new ArrayList<>();
    _satisfacao = new SatisfacaoAnimal(this);
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
  * Retrieves the adequacy value of the animal.
  *
  * @return the adequacy value as an integer.
  */
  public int getAdequacao() {
    return _adequacao;
  }

  /**
   * Retrieves the satisfaction level of the animal.
   *
   * @return the current satisfaction level of the animal.
   */
  public Satisfacao getSatisfacao(){
    return _satisfacao;
  }

  /**
   * Retrieves the list of vaccination records for the animal.
   *
   * @return a list of {@link RegistoVacina} representing the vaccination records.
   */
  public List<RegistoVacina> getRegistos() {
    return _registoVacinacao;
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

  @Override
  public int compareTo(Animal a){
    return getId().compareToIgnoreCase(a.getId());
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
