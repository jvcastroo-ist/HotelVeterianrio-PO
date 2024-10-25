package hva.core;

import java.util.*;

public class Veterinario extends Funcionario {
  private List<Especie> _especies;
  private List<RegistoVacina> _registoVacinas;

  /**
   * Constructs a new Veterinario object with the specified ID and name.
   *
   * @param idVeterinario the unique identifier for the veterinarian
   * @param nome the name of the veterinarian
   */
  public Veterinario(String idVeterinario, String nome){
    super(idVeterinario, nome);
    _especies = new ArrayList<>();
    _registoVacinas = new ArrayList<>();
    setSatisfacao(new SatisfacaoVeterinario(this));
  }

  /**
   * Retrieves a sorted list of species.
   *
   * @return a sorted list of species.
   */
  public List<Especie> getEspecies(){
    return sort(_especies);
  }

  /**
   * Retrieves the list of vaccine records.
   *
   * @return a list of {@link RegistoVacina} objects representing the vaccine records.
   */
  public List<RegistoVacina> getRegistos() {
    return _registoVacinas;
  }

  /**
   * Checks if the given responsibility is assigned to this veterinarian.
   *
   * @param r the responsibility to check
   * @return true if the responsibility is assigned, false otherwise
   */
  @Override
  public boolean isResponsabilidadeAtribuida(Responsabilidade r){
    return _especies.contains((Especie)r);
  }

  /**
   * Assigns a responsibility to the veterinarian if it has not already been assigned.
   * If the responsibility is not already assigned, it adds the responsibility to the list of species
   * and increments the responsibility count for the veterinarian.
   *
   * @param r the responsibility to be assigned
   */
  @Override
  public void atribuiResponsabilidade(Responsabilidade r){
    if(!(isResponsabilidadeAtribuida(r))){
      _especies.add((Especie)r);
      r.operaFuncionario(true); // add +1 to responsability VET
    }
  }

  /**
   * Removes the given responsibility from the veterinarian.
   * 
   * @param r the responsibility to be removed
   */
  @Override
  public void retiraResponsabilidade(Responsabilidade r){
    _especies.remove((Especie)r); 
    r.operaFuncionario(false);  // removes -1 to responsability VET
  }

  /**
   * Adds a new vaccination record to the list of vaccination records.
   *
   * @param rv the vaccination record to be added
   */
  public void addRegisto(RegistoVacina rv) {
    _registoVacinas.add(rv);
  }

  /**
   * Returns a string representation of the Veterinario object.
   * This method overrides the default toString method to provide a custom
   * string representation that includes the "VET" prefix and the species
   * associated with the Veterinario.
   *
   * @return A string representation of the Veterinario object.
   */
  @Override
  public String toString() {
      return super.visualiza("VET", getEspecies());
  }
}