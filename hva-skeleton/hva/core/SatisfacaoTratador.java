package hva.core;

public class SatisfacaoTratador implements Satisfacao{
  private Tratador _t;

  public SatisfacaoTratador(Tratador t) {
    _t = t;
  }

  /**
   * Calculates the satisfaction level of the handler based on the workload in each habitat.
   * 
   * @return the satisfaction level as an integer, where a higher value indicates higher satisfaction.
   */
  @Override
  public double satisfacao() {
    double sum = 0;
    for(Habitat h : _t.getHabitats()) {
      sum += h.trabalhoNoHabitat()/h.getNumFuncionarios();
    }
    sum = 300 - sum;
    return sum;
  }
}
