package hva.core.exception;

public class CoreDuplicateSpeciesNameException extends Exception{
  private String _name;

  // Construtor que recebe uma string nome e atribui a _nome
  public CoreDuplicateSpeciesNameException(String name) {
    this._name = name;
  }

  // Método get para o atributo _nome
  public String getName() {
    return _name;
  }
}
