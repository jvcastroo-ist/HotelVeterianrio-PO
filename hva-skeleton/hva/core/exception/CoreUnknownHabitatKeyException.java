package hva.core.exception;

public class CoreUnknownHabitatKeyException extends Exception {
  private String _id;

  // Construtor que recebe uma string id e atribui a _id
  public CoreUnknownHabitatKeyException(String id) {
    this._id = id;
  }

  // Método get para o atributo _id
  public String getId() {
    return _id;
  }
}
