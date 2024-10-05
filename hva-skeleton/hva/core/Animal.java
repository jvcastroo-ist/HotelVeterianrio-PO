package hva.core;

import java.util.*;

public class Animal {
    private String _id;
    private String _nome;
    private int _adequacao;
    private Especie _especie;
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

    // Calcula a satisfação do animal
    public int satisfacao() {
        return 0;
    }

      

}
