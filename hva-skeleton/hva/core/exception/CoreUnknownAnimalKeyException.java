package hva.core.exception;

public class CoreUnknownAnimalKeyException extends Exception {
  private String _id;

  // Construtor que recebe uma string id e atribui a _id
  public CoreUnknownAnimalKeyException(String id) {
    this._id = id;
  }

  // Método get para o atributo _id
  public String getId() {
    return _id;
  }
}
