package hva.core.exception;

public class CoreDuplicateEmployeeKeyException extends Exception {
  private String _id;

  // Construtor que recebe uma string id e atribui a _id
  public CoreDuplicateEmployeeKeyException(String id) {
    this._id = id;
  }

  // Método get para obter o valor de _id
  public String getId() {
    return _id;
  }
}
