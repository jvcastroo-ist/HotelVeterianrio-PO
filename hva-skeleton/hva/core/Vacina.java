package hva.core;

import java.io.*;
import java.util.*;

public class Vacina extends HotelEntity implements Serializable, Comparable<Vacina>{
  private List<Especie> _especies;
  private List<RegistoVacina> _registosVacina;

  /**
   * Constructs a new Vacina instance.
   *
   * @param idVacina the unique identifier for the vaccine
   * @param nome the name of the vaccine
   * @param especies the list of species that the vaccine is effective for; if null, an empty list will be initialized
   */
  public Vacina(String idVacina, String nome, List<Especie> especies){
    super(idVacina, nome);
    _especies = especies != null ? new ArrayList<>(especies) : new ArrayList<>();
    _registosVacina = new ArrayList<>();
  }

  /**
   * Retrieves the list of species associated with this vaccine.
   *
   * @return a list of {@link Especie} objects representing the species.
   */
  public List<Especie> getEspecies() {
    return sort(_especies);
  }

  /**
   * Retrieves the list of vaccine records.
   *
   * @return a list of {@link RegistoVacina} objects representing the vaccine records.
   */
  public List<RegistoVacina> getRegistosVacina() {
    return _registosVacina;
  }

  /**
   * Vaccinates an animal and registers the vaccination.
   *
   * @param a   The animal to be vaccinated.
   * @param vet The veterinarian administering the vaccine.
   * @return    A record of the vaccination.
   */
  public RegistoVacina vacinarAnimal(Animal a, Veterinario vet) {
    // Verefies if this vaccine is allowed to vaccinate the species of the animal
    boolean especieIgual = verificaEspecie(a.getEspecie());
    // Registers the vaccination
    RegistoVacina novoRV = new RegistoVacina(this, a, vet);
    // calulates the damage of the vacciantion
    novoRV.setDano(especieIgual);
    // add to the list of vaccination records
    _registosVacina.add(novoRV);
    // adds to the veterinarian records
    vet.addRegisto(novoRV);
    // adds to the animal records
    a.addRegisto(novoRV);
    // returns the record
    return novoRV;
  }

  /**
   * Checks if the given species is contained within the list of species.
   *
   * @param e the species to be checked
   * @return true if the species is contained in the list, false otherwise
   */
  private boolean verificaEspecie(Especie e) {
    return _especies.contains(e);
  }

  /**
   * Generates a string of species IDs from a list of Especie objects.
   *
   * @param e the list of Especie objects
   * @return a string containing the IDs of the species, separated by commas and prefixed with a "|".
   *         If the list is empty, returns an empty string.
   */
  public String idEspecies(List<Especie> e) {
    List<String> ids = new ArrayList<>();
    if (e.isEmpty()) {return "";}
    for (Especie especie : e) { 
      ids.add(especie.getId());
    }
    return "|" + String.join(",", ids);
  }

  @Override
  public int compareTo(Vacina v){
    return getId().compareToIgnoreCase(v.getId());
  }
 
  /**
   * Returns a string representation of the Vacina object.
   * The format of the returned string is "VACINA|<id>|<nome>|<number_of_registos>|<id_especies>".
   *
   * @return A formatted string representing the Vacina object.
   */
  @Override
  public String toString(){
    return String.format("VACINA|%s|%s|%d%s", getId(), getNome(), _registosVacina.size(), idEspecies(_especies));
  }  
}