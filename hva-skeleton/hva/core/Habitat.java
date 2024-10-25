package hva.core;

import java.util.*;
import java.util.stream.*;

public class Habitat extends Responsabilidade implements Comparable<Habitat> {
  private int _area;
  private List<Arvore> _arvores;
  private List<Animal> _animais;
  private Map<Especie, Integer> _adequacao;

  /**
   * Constructs a new Habitat with the specified ID, name, and area.
   *
   * @param idHabitat the unique identifier for the habitat
   * @param nomeHabitat the name of the habitat
   * @param area the area of the habitat in square meters
   */
  public Habitat(String id, String nome, int area){
    super(id, nome);
    _area = area;
    _arvores = new ArrayList<>();
    _animais = new ArrayList<>();
    _adequacao = new HashMap<>();
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
   * Retrieves a list of trees (Arvore) in the habitat.
   * The list is sorted before being returned.
   *
   * @return a sorted list of Arvore objects.
   */
  public List<Arvore> getArvores() {
    return sort(_arvores);
  } 

  /**
   * Retrieves the list of animals in the habitat.
   *
   * @return a list of Animal objects representing the animals in the habitat.
   */
  public List<Animal> getAnimals() {
    return sort(_animais);
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
   * Alters the influence of a given species in the habitat.
   *
   * @param e the species whose influence is to be altered
   * @param inf the new influence value to be set
   */
  public void alteraInfluencia(Especie e, int inf) {
    if (_adequacao.containsKey(e))
      // If the species is inside the map, it replaces the influence 
      _adequacao.replace(e, inf);
    // puts the new species influence  
    _adequacao.put(e, inf);
    // changes the influence of each animal of species e 
    for (Animal a : getAnimals(e)) {
      a.setAdequacao(inf);
    }
  }

  /**
   * Removes the specified animal from the habitat.
   *
   * @param a the animal to be removed
   */
  public void removeAnimal(Animal a){
    _animais.remove(a);
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
   * Adds an animal to the habitat. If the animal's species is present in the adequacy map,
   * the animal's adequacy is set according to the map. Otherwise, the adequacy is set to neutral (0).
   *
   * @param a the animal to be added to the habitat
   */
  public void addAnimal(Animal a){
    // animal's species
    Especie e = a.getEspecie();
    if (_adequacao.containsKey(e)){
      // set the animal's influence if the species is inside the map
      a.setAdequacao(_adequacao.get(e));
    } else {
      // set the influence to neutral
      a.setAdequacao(0);
    }
    // add the animal to the habitat  
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

  /**
   * Calculates the total cleaning effort required for all trees in the habitat.
   *
   * @return the sum of the cleaning efforts for all trees.
   */
  public double esforcoLimpezaArvores() {
    return _arvores.stream().mapToDouble(Arvore::getEsforcoLimpeza).sum();
  }

  /**
   * Calculates the total work required in the habitat.
   * 
   * The calculation is based on the area of the habitat, the number of animals,
   * and the effort required for cleaning trees.
   * 
   * @return the total work required in the habitat.
   */
  public double trabalhoNoHabitat() {
    return _area + 3*_animais.size() + esforcoLimpezaArvores();  
  }

  @Override
  public int compareTo(Habitat h){
    return getId().compareToIgnoreCase(h.getId());
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
    String hab = String.format("HABITAT|%s|%s|%d|%d", getId(), getNome(), getArea(), _arvores.size());
    for(Arvore a : getArvores()) {
      hab += ("\n" + a.toString());
    }
    return hab;
  }
}