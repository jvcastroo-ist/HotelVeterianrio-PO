package hva.core.exception;

public class CoreNoResponsibilityException extends Exception {
  private String _employeeKey;
  private String _responsibilityKey;

  /**
   * Exception thrown when an employee does not have the required responsibility.
   *
   * @param employeeKey The key identifying the employee.
   * @param responsibilityKey The key identifying the responsibility.
   */
  public CoreNoResponsibilityException(String employeeKey, String responsibilityKey) {
    _employeeKey = employeeKey;
    _responsibilityKey = responsibilityKey;
  }

  /**
   * Retrieves the key associated with the employee.
   *
   * @return the employee key as a String.
   */
  public String getEmployeeKey() {
    return _employeeKey;
  }

  /**
   * Retrieves the responsibility key associated with this exception.
   *
   * @return the responsibility key as a String.
   */
  public String getResponsibilityKey() {
    return _responsibilityKey;
  }
}
