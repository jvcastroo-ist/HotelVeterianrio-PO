package hva.core;

public class SatisfacaoAnimal implements Satisfacao{
  private Animal _animal;
  
  public SatisfacaoAnimal(Animal a) {
    _animal = a;
  }

  /**
   * Calculates the satisfaction level of an animal based on its habitat conditions.
   *
   * @return the satisfaction level as an integer, rounded to the nearest whole number.
   *         The calculation is based on the following factors:
   *         - Number of different species in the habitat.
   *         - Average space per animal in the habitat.
   *         - Species similarity factor.
   *         - Adequacy of the habitat for the animal.
   */
  @Override
  public int satisfacao() {
    Habitat habitat = _animal.getHabitat();
    // Numero de especies diferentes no habitat
    int especieDiferente = habitat.getAnimals().size() - (_animal.especieIgual() + 1);
    // AREA/POPULACAO
    double espacoMedio = habitat.getArea()/habitat.getAnimals().size();
    // satisfacao arredondada ao inteiro mais proximo
    return (int)Math.round(20 + 3*_animal.especieIgual() - 2*especieDiferente + espacoMedio + _animal.getAdequacao());
  }
}
