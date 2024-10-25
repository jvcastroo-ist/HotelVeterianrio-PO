package hva.core;

import java.util.*;

public class Especie extends Responsabilidade implements Comparable<Especie>{
  private List<Animal> _animais;

  /**
   * Constructs a new Especie object with the specified id and name.
   *
   * @param id the unique identifier for the species
   * @param nome the name of the species
   */
  public Especie(String id, String nome) {
    super(id, nome);
    _animais = new ArrayList<>();    
  }

  @Override
  public String getType(){
    return "ESP";
  }

  /**
   * Adds an animal to the list of animals.
   *
   * @param a the animal to be added
   */
  public void addAnimal(Animal a){
    _animais.add(a);
  }

  /**
   * Retrieves the list of animals associated with this species.
   *
   * @return a list of {@link Animal} objects.
   */
  public List<Animal> getAnimais() {
    return _animais;
  }

  @Override
  public String toString(){
    return getId();
  }

  @Override
  public int compareTo(Especie e){
    return getId().compareToIgnoreCase(e.getId());
  }
}