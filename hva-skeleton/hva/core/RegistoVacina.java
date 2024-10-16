package hva.core;

import java.io.*;

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
      int count = 0;
      int length = Math.min(str1.length(), str2.length());

      for (int i = 0; i < length; i++) {
          if (str1.charAt(i) == str2.charAt(i)) {
              count++;
          }
      }

      return count;
  }

    /**
     * Calculates the maximum damage based on the species names.
     * The damage is determined by the difference in length between the species names
     * and the number of common characters in the names.
     *
     * @return the maximum damage calculated.
     */
    public int calculaDano() {
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
}