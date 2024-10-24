package hva.core;

public abstract class Responsabilidade extends HotelEntity {
  private int _numFuncionarios;

  protected Responsabilidade(String id, String nome) {
    super(id, nome);
  }

  /**
   * Retrieves the number of employees associated with this responsability.
   *
   * @return the number of employees.
   */
  public int getNumFuncionarios() {
    return _numFuncionarios;
  }

   
  /**
   * Updates the number of employees based on the given parameter.
   *
   * @param adiciona If true, increments the number of employees by 1; 
   *                 if false, decrements the number of employees by 1.
   */
  public void operaFuncionario(boolean adiciona) {
    _numFuncionarios += (adiciona) ? 1 : -1;
  }

  public abstract String getType();
}
