package hva.core.exception;

public class CoreDuplicateSpeciesNameException extends Exception{
  private String _name;

  // Construtor que recebe uma string id e atribui a _id
  public CoreDuplicateSpeciesNameException(String name) {
    this._name = name;
  }

  // Método get para o atributo _id
  public String getName() {
    return _name;
  }
}
