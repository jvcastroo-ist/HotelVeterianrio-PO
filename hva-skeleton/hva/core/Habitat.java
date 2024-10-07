package hva.core;

import java.util.*;


public class Habitat implements Responsabilidade{
  private String _id;
  private String _nome;
  private int _area;
  private List<Arvore> _arvores;
  private List<Animal> _animais;
  private int _numTratador;

  public Habitat(String idHabitat, String nomeHabitat, int area){
    _id = idHabitat;
    _nome = nomeHabitat;
    _area = area;
  }

  /*verificar de precisa implementar outra classe @override error?! */
  @Override
  public String getId(){
    return _id;
  }

  public void setArea(int area){
    _area = area;
  }

  /* rever qual a melhor forma de passar a String gigante */
  public String visualiza(Hotel h){
    StringBuilder viewHabitat = new StringBuilder();
    
    viewHabitat.append(String.format("HABITAT|%s|%s|%d|%d\n", _id, _nome, _area, _arvores.size()));
    for(Arvore a : _arvores){
      viewHabitat.append(a.visualiza(h));
      viewHabitat.append("\n");
    }
    return viewHabitat.toString();
  }




}