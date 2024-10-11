package hva.core;

import hva.core.exception.CoreNoResponsibilityException;
import java.util.*;

public class Veterinario extends Funcionario{
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

  /**
   * Retrieves the collection of species associated with the veterinarian.
   *
   * @return a collection of Especie objects representing the species.
   */
  public Collection<Especie> getEspecies(){
    return _especies;
  }

  /**
   * Manages the responsibility of a veterinarian by either assigning or removing a responsibility.
   *
   * @param r the responsibility to be managed
   * @param atribui if true, the responsibility is assigned; if false, the responsibility is removed
   * @throws CoreNoResponsibilityException if the responsibility is not valid
   */
  @Override
  public void operaResponsabilidade(Responsabilidade r, boolean atribui) throws CoreNoResponsibilityException{
    verifyResponsabilidade(r);
    if (atribui) {
      _especies.add((Especie)r);
    } else {
      _especies.remove((Especie)r);
    }
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