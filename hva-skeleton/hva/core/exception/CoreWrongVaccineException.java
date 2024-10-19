package hva.core.exception;

public class CoreWrongVaccineException extends Exception{
  private String _vaccineId;
  private String _animalId;

  public CoreWrongVaccineException(String vaccineId, String animalId) {
    _vaccineId = vaccineId;
    _animalId = animalId;
  }

  public String getVaccine() {
    return _vaccineId;
  }

  public String getAnimal() {
    return _animalId;
  }
}
