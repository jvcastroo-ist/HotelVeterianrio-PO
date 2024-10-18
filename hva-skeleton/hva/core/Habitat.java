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
   * Sets the area of the habitat.
   *
   * @param area the area to set for the habitat
   */
  public void setArea(int area){
    _area = area;
  }

  /**
   * Retrieves a list of trees (Arvore) in the habitat.
   * The list is sorted before being returned.
   *
   * @return a sorted list of Arvore objects.
   */
  public List<Arvore> getArvores(){
    return sort();
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