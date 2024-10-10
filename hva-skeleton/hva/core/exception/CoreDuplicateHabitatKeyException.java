package hva.core.exception;

public class CoreDuplicateHabitatKeyException extends Exception {
  private String _id;

  // Construtor que recebe uma string id e atribui a _id
  public CoreDuplicateHabitatKeyException(String id) {
    this._id = id;
  }

  // Método get para retornar o valor de _id
  public String getId() {
    return _id;
  }
}
