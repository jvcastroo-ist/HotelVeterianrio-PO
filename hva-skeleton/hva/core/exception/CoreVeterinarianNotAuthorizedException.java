package hva.core.exception;

public class CoreVeterinarianNotAuthorizedException extends Exception {
  private String _vetId;
  private String _speciesId;

  public CoreVeterinarianNotAuthorizedException(String vet, String e) {
    _vetId = vet;
    _speciesId = e;
  }

  public String getVet() {
    return _vetId;
  }

  public String getSpecies() {
    return _speciesId;
  }
}
