package hva.core;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Habitat extends Responsabilidade implements Serializable, Visualiza{
  private final String _id;
  private final String _nome;
  private int _area;
  private List<Arvore> _arvores;
  private List<Animal> _animais;
  private int _numTratador;

  public Habitat(String idHabitat, String nomeHabitat, int area){
    _id = idHabitat;
    _nome = nomeHabitat;
    _area = area;
    _arvores = new ArrayList<>();
    _animais = new ArrayList<>();
  }

  @Override
  public String getId(){
    return _id;
  }

  public void setArea(int area){
    _area = area;
  }

  public List<Arvore> getArvores(){
    return sort();
  } 
  
  public List<Arvore> sort() {
    return _arvores.stream().sorted((a1, a2) -> a1.getId().compareToIgnoreCase(a2.getId())).collect(Collectors.toList());
  }

  public void addAnimal(Animal a){
    _animais.add(a);
  }

  public void addArvore(Arvore a){
    _arvores.add(a);
  }

  @Override
  public String visualiza(Hotel h){    
    String hab = String.format("HABITAT|%s|%s|%d|%d", _id, _nome, _area, _arvores.size());
    for(Arvore a : _arvores) {
      hab += ("\n" + a.visualiza(h));
    }
    return hab;
  }
}