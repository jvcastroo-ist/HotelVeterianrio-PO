package hva.core;

import java.io.*;
import java.util.*;

public class RegistoVacina implements Serializable{
    private final Vacina _vacina;
    private final Animal _animal;
    private final Veterinario _veterinario;
    private Dano _dano;

    /**
     * Constructs a new RegistoVacina object with the specified identifiers.
     *
     * @param idVacina the identifier of the vaccine
     * @param idAnimal the identifier of the animal
     * @param idVeterinario the identifier of the veterinarian
     */
    public RegistoVacina(Vacina v, Animal a, Veterinario vet){
        _vacina = v;
        _animal = a;
        _veterinario = vet;
    }

    /**
     * Retrieves the unique identifier of the vaccine record.
     *
     * @return the unique identifier as a String.
     */
    public Vacina getVacina() {
      return _vacina;
    }

    /**
     * Retrieves the ID of the animal associated with this vaccine record.
     *
     * @return the ID of the animal as a String.
     */
    public Animal getAnimal() {
      return _animal;
    }

    /**
     * Retrieves the ID of the veterinarian.
     *
     * @return the ID of the veterinarian as a String.
     */
    public Veterinario getVeterinario() {
      return _veterinario;
    }

    /**
     * Retrieves the damage associated with this vaccine record.
     *
     * @return the damage (Dano) associated with this vaccine record.
     */
    public Dano getDano() {
      return _dano;
    }

    private int caracteresEmComum(String str1, String str2) {
      str1 = str1.toLowerCase();
      str2 = str2.toLowerCase();
      Map<Character, Integer> contador1 = new HashMap<>();

      // Conta os caracteres de str1
      for (char c : str1.toCharArray()) {
          contador1.put(c, contador1.getOrDefault(c, 0) + 1);
      }

      int totalComum = 0;

      // Conta os caracteres comuns em str2
      for (char c : str2.toCharArray()) {
          if (contador1.containsKey(c) && contador1.get(c) > 0) {
              totalComum++;
              contador1.put(c, contador1.get(c) - 1);
          }
      }

      return totalComum;
    }

    /**
     * Calculates the maximum damage based on the species names.
     * The damage is determined by the difference in length between the species names
     * and the number of common characters in the names.
     *
     * @return the maximum damage calculated.
     */
    public int calculaDano() {
      // verifica se a especie está dentro das especies da vacina
      if (_vacina.getEspecies().contains(_animal.getEspecie())){
        return 0;
      }
      int maxDano = 0;
    
      for (Especie e : _vacina.getEspecies()) {
        // verifica qual especie tem maior nome
        int tamanhoNome = Math.max(_animal.getEspecie().getNome().length(),e.getNome().length());
        // calcula dano apartir dos caracteres em comum dos nomes das especies
        int dano = tamanhoNome - caracteresEmComum(_animal.getEspecie().getNome(), e.getNome());
        // Verifica se o dano foi maior que o anterior e substitui
        if (dano > maxDano) {maxDano = dano;}
      } 
      return maxDano;
    }

    public void setDano(boolean especieIgual) {
      _dano = new Dano(calculaDano(), especieIgual);
    }

    @Override
    public String toString() {
      return String.format("REGISTO-VACINA|%s|%s|%s", _vacina.getId(), _veterinario.getId(), _animal.getEspecie().getId());
    }
}