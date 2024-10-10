package hva.core;

import java.io.*;
import java.util.*;

public class Vacina implements Visualiza, Serializable{
    private final String _id;
    private final String _nome;
    private List<Especie> _especies;
    private List<RegistoVacina> _registosVacina;

    public Vacina(String idVacina, String nome, List<Especie> especies){
        _id = idVacina;
        _nome = nome;
        _especies = new ArrayList<>(especies);
    }

    public String getId() {
      return _id;
    }

    public String getNome() {
      return _nome;
    }

    public List<Especie> getEspecies() {
      return _especies;
    }

    public List<RegistoVacina> getRegistosVacina() {
      return _registosVacina;
    }

    @Override
    public String visualiza(Hotel h){
      return String.format("VACINA|%s|%s|%d%s", getId(), getNome(), _registosVacina.size(), Responsabilidade.idResponsabilidade(_especies));
    }
    
}