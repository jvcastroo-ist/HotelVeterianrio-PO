package hva.core;

import java.util.*;
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
  }

  @Override
  public String getId(){
    return _id;
  }

  public void setArea(int area){
    _area = area;
  }

  public List<Arvore> getArvores(){
    return _arvores;
  } 

  public void addAnimal(Animal a){
    _animais.add(a);
  }

  @Override
  public String visualiza(Hotel h){    
    return String.format("HABITAT|%s|%s|%d|%d", _id, _nome, _area, _arvores.size());
  }
}