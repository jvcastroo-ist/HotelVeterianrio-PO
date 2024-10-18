package hva.core;

import java.io.*;
import java.util.*;

public class Vacina implements Serializable{
    private final String _id;
    private final String _nome;
    private List<Especie> _especies;
    private List<RegistoVacina> _registosVacina;

    /**
     * Constructs a new Vacina instance.
     *
     * @param idVacina the unique identifier for the vaccine
     * @param nome the name of the vaccine
     * @param especies the list of species that the vaccine is effective for; if null, an empty list will be initialized
     */
    public Vacina(String idVacina, String nome, List<Especie> especies){
      _id = idVacina;
      _nome = nome;
      _especies = especies != null ? new ArrayList<>(especies) : new ArrayList<>();
      _registosVacina = new ArrayList<>();
    }

    /**
     * Retrieves the unique identifier of the vaccine.
     *
     * @return the unique identifier of the vaccine.
     */
    public String getId() {
      return _id;
    }

    /**
     * Retrieves the name of the vaccine.
     *
     * @return the name of the vaccine as a String.
     */
    public String getNome() {
      return _nome;
    }

    /**
     * Retrieves the list of species associated with this vaccine.
     *
     * @return a list of {@link Especie} objects representing the species.
     */
    public List<Especie> getEspecies() {
      return _especies;
    }

    /**
     * Retrieves the list of vaccine records.
     *
     * @return a list of {@link RegistoVacina} objects representing the vaccine records.
     */
    public List<RegistoVacina> getRegistosVacina() {
      return _registosVacina;
    }

    public void vacinarAnimal(Animal a, Veterinario vet, boolean especieIgual) {
      RegistoVacina novoRV = new RegistoVacina(this, a, vet);
      novoRV.setDano(especieIgual);
      _registosVacina.add(novoRV);
    }

    /**
     * Generates a string of species IDs from a list of Especie objects.
     *
     * @param e the list of Especie objects
     * @return a string containing the IDs of the species, separated by commas and prefixed with a "|".
     *         If the list is empty, returns an empty string.
     */
    public String idEspecies(List<Especie> e) {
      List<String> ids = new ArrayList<>();
      if (e.isEmpty()) {return "";}
      for (Especie especie : e) { 
        ids.add(especie.getId());
      }
      return "|" + String.join(",", ids);
    }

    
    /**
     * Returns a string representation of the Vacina object.
     * The format of the returned string is "VACINA|<id>|<nome>|<number_of_registos>|<id_especies>".
     *
     * @return A formatted string representing the Vacina object.
     */
    @Override
    public String toString(){
      return String.format("VACINA|%s|%s|%d%s", getId(), getNome(), _registosVacina.size(), idEspecies(_especies));
    }  
}