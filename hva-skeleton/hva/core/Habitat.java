package hva.core;

import java.util.*;
import java.util.stream.*;

public class Habitat extends Responsabilidade implements Comparable<Habitat> {
  private final String _id;
  private final String _nome;
  private int _area;
  private List<Arvore> _arvores;
  private List<Animal> _animais;
  private int _numTratador;

  /**
   * Constructs a new Habitat with the specified ID, name, and area.
   *
   * @param idHabitat the unique identifier for the habitat
   * @param nomeHabitat the name of the habitat
   * @param area the area of the habitat in square meters
   */
  public Habitat(String idHabitat, String nomeHabitat, int area){
    _id = idHabitat;
    _nome = nomeHabitat;
    _area = area;
    _arvores = new ArrayList<>();
    _animais = new ArrayList<>();
  }

  /**
   * Retrieves the unique identifier of the habitat.
   *
   * @return the unique identifier of the habitat as a String.
   */
  @Override
  public String getId(){
    return _id;
  }

  /**
   * Retrieves the name of the habitat.
   *
   * @return the name of the habitat.
   */
  public String getNome() {
    return _nome;
  }

  /**
   * Retrieves the area of the habitat.
   *
   * @return the area of the habitat.
   */
  public int getArea() {
    return _area;
  }

  /**
   * Alters the influence of a given species by setting the adequacy value for each animal of that species.
   *
   * @param e   the species whose influence is to be altered
   * @param inf the new adequacy value to be set for each animal of the specified species
   */
  public void alteraInfluencia(Especie e, int inf) {
    getAnimals(e).forEach(a -> a.setAdequacao(inf));
  }

  /**
   * Retrieves a list of trees (Arvore) in the habitat.
   * The list is sorted before being returned.
   *
   * @return a sorted list of Arvore objects.
   */
  public List<Arvore> getArvores() {
    return sort();
  } 

  /**
   * Retrieves the list of animals in the habitat.
   *
   * @return a list of Animal objects representing the animals in the habitat.
   */
  public List<Animal> getAnimals() {
    return _animais;
  }

  /**
   * Retrieves a list of animals that belong to the specified species.
   *
   * @param e the species to filter animals by
   * @return a list of animals that belong to the specified species
   */
  public List<Animal> getAnimals(Especie e) {
    return _animais.stream().filter(a -> (a.getEspecie() == e)).collect(Collectors.toList());
  }

  /**
   * Sets the area of the habitat.
   *
   * @param area the area to set for the habitat
   */
  public void setArea(int area){
    _area = area;
  }

  /**
   * Sorts the list of Arvore objects by their IDs in a case-insensitive manner.
   *
   * @return A sorted list of Arvore objects based on their IDs.
   */
  public List<Arvore> sort() {
    return _arvores.stream().sorted((a1, a2) -> a1.getId().compareToIgnoreCase(a2.getId())).collect(Collectors.toList());
  }

  /**
   * Adds an animal to the habitat.
   *
   * @param a the animal to be added
   */
  public void addAnimal(Animal a){
    _animais.add(a);
  }

  /**
   * Adds a tree to the habitat.
   *
   * @param a the tree to be added
   */
  public void addArvore(Arvore a){
    _arvores.add(a);
  }

  @Override
  public int compareTo(Habitat h){
    return _id.compareTo(h.getId());
  }

  /**
   * Modifies the number of tratadores in the habitat.
   *
   * @param adiciona if true, increments the number of handlers by 1; 
   *                 if false, decrements the number of handlers by 1.
   */
  public void operaTratador(boolean adiciona) {
    _numTratador += (adiciona) ? 1 : -1;
  }

  /**
   * Retrieves the number of tratadores assigned to the habitat.
   *
   * @return the number of tratadores.
   */
  public int getNumTratador() {
    return _numTratador;
  }

  /**
   * Returns a string representation of the Habitat object.
   * The format includes the habitat's ID, name, area, and the number of trees.
   * Each tree's string representation is appended on a new line.
   *
   * @return A formatted string representing the habitat and its trees.
   */
  @Override
  public String toString(){    
    String hab = String.format("HABITAT|%s|%s|%d|%d", _id, _nome, _area, _arvores.size());
    for(Arvore a : _arvores) {
      hab += ("\n" + a.toString());
    }
    return hab;
  }
}