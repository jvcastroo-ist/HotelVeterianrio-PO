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
  }

  public List<Especie> getEspecies(){
    return _especies;
  }

  @Override
  public void operaResponsabilidade(Responsabilidade r, boolean atribui) {
    if (atribui) {
      _especies.add((Especie)r);
    } else {
      _especies.remove((Especie)r);
    }
    r.operaFuncionario(atribui);
  }

  @Override
  public double satisfacao() {
    double sum = 0;
    for (Especie e : _especies) {
      sum += e.getAnimais().size()/e.getNumFuncionarios();
    }
    sum = 20 - sum;
    return sum;
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
      return super.visualiza("VET", _especies);
  }
}