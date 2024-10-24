package hva.core;

public class SatisfacaoVeterinario implements Satisfacao{
  private Veterinario _vet;

  public SatisfacaoVeterinario(Veterinario vet) {
    _vet = vet;
  }

  @Override
  public int satisfacao() {
    double sum = 0;
    for (Especie e : _vet.getEspecies()) {
      sum += e.getAnimais().size()/e.getNumFuncionarios();
    }
    sum = 20 - sum;
    return (int)Math.round(sum);
  }
}
