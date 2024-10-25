package hva.core;

public class SatisfacaoVeterinario implements Satisfacao{
  private Veterinario _vet;

  public SatisfacaoVeterinario(Veterinario vet) {
    _vet = vet;
  }

  /**
   * Calculates the satisfaction level of the veterinarian.
   * The satisfaction is determined by the number of animals per species
   * divided by the number of employees for each species, summed up and
   * subtracted from 20. The result is then rounded to the nearest integer.
   *
   * @return the satisfaction level as an integer.
   */
  @Override
  public double satisfacao() {
    double sum = 0;
    for (Especie e : _vet.getEspecies()) {
      sum += e.getAnimais().size()/e.getNumFuncionarios();
    }
    sum = 20 - sum;
    return sum;
  }
}
