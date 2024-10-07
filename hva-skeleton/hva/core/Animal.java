package hva.core;

import java.util.*;

public class Animal implements Visualiza{
    private final String _id;
    private final String _nome;
    private int _adequacao;
    private final Especie _especie;
    private List<RegistoVacina> _registoVacinacao;
    private Habitat _habitat;
            
    // Constructor
    public Animal(String id, String nome, Especie especie, Habitat habitat) {
        _id = id;
        _nome = nome;
        _especie = especie;
        _habitat = habitat;
        _registoVacinacao = new ArrayList<>();
    }

    public String getId() {
      return _id;
    }

    public String getNome() {
      return _nome;
    }

    public int getAdequacao() {
      return _adequacao;
    }

    public Especie getEspecie() {
      return _especie;
    }

    public String getRegistoVacinacao() {
      List<String> danos = new ArrayList<>();
      if (_registoVacinacao.isEmpty()) {return "VOID";}
      for (RegistoVacina rv : _registoVacinacao) {
        danos.add(rv.getDano().name());
      }
      return String.join(",", danos);
    }

    public Habitat getHabitat() {
      return _habitat;
    }

    @Override
    public String visualiza(Hotel h) {
        return String.format("ANIMAL|%s|%s|%s|%s|%s", getId(), getNome(), getEspecie().getId(), getRegistoVacinacao(), getHabitat());
    }
}
