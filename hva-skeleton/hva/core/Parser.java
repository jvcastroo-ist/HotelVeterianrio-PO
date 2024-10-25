package hva.core;

import hva.core.exception.CoreDuplicateAnimalKeyException;
import hva.core.exception.CoreDuplicateEmployeeKeyException;
import hva.core.exception.CoreDuplicateHabitatKeyException;
import hva.core.exception.CoreDuplicateSpeciesKeyException;
import hva.core.exception.CoreDuplicateSpeciesNameException;
import hva.core.exception.CoreDuplicateTreeKeyException;
import hva.core.exception.CoreDuplicateVaccineKeyException;
import hva.core.exception.CoreNoResponsibilityException;
import hva.core.exception.CoreUnknownEmployeeKeyException;
import hva.core.exception.CoreUnknownHabitatKeyException;
import hva.core.exception.CoreUnknownSpeciesKeyException;
import hva.core.exception.CoreUnknownTreeKeyException;
import hva.core.exception.UnrecognizedEntryException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Esta solução assume que a classe Hotel já tem a seguinte funcionalidade

public class Hotel {
  public void registerAnimal(animalId, name, habitatId, speciesId) throws OneOrMoreCoreExceptions { ... }
  public void registerSpecies(speciesId, name) throws OneOrMoreCoreExceptions { ... }
  public void registerEmployee(employeeId, name, empType) throws OneOrMoreCoreExceptions { ... }
  public void addResponsibility(employeeId, responsibility) throws OneOrMoreCoreExceptions { ... }
  public void registerVaccine(vaccineId, name, String[] speciesIds) throws someCoreExceptionsOneOrMoreCoreExceptions { ... }
  public void createTree(TreeId, name, type, age, diff) throws OneOrMoreCoreExceptions { ... }
  public Habitat registerHabitat(habitatId, name, area) throws OneOrMoreCoreExceptions { ... }

Note-se que esta funcionalidade pode ser utilizada na concretização de alguns dos comandos.
Caso Hotel não tenha esta funcionalidade, então deverão substituir a invocação destes métodos
na classe Parser por uma ou mais linhas com uma funcionalidade semelhante.
Cada um destes métodos pode lançar uma ou mais excepções que irão corresponder aos erros que
podem acontecer ao nível do domínio surante a concretização da funcionalidade em causa.
**/

public class Parser {
  private Hotel _hotel;

  /**
   * Constructs a Parser object with the specified Hotel instance.
   *
   * @param h the Hotel instance to be associated with this Parser
   */
  Parser(Hotel h) {
    _hotel = h;
  }

  /**
   * Parses the specified file and processes each line using the parseLine method.
   *
   * @param filename the name of the file to be parsed
   * @throws IOException if an I/O error occurs while reading the file
   * @throws UnrecognizedEntryException if a line in the file cannot be recognized or processed
   */
  public void parseFile(String filename) throws IOException, UnrecognizedEntryException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;

      while ((line = reader.readLine()) != null)
        parseLine(line);

    }
  }

  /**
   * Parses a line of input and delegates to the appropriate parsing method based on the entry type.
   *
   * @param line the line of input to be parsed
   * @throws UnrecognizedEntryException if the entry type is not recognized
   */
  private void parseLine(String line) throws UnrecognizedEntryException {
    String[] components = line.split("\\|");
    switch(components[0]) {
    case "ESPÉCIE" -> parseSpecies(components);
    case "ANIMAL" -> parseAnimal(components);
    case "ÁRVORE" -> parseTree(components);
    case "HABITAT" -> parseHabitat(components);
    case "TRATADOR" -> parseEmployee(components, "TRT");
    case "VETERINÁRIO" -> parseEmployee(components, "VET");
    case "VACINA" -> parseVaccine(components);
    default -> throw new UnrecognizedEntryException ("tipo de entrada inválido: " + components[0]);
    }
  }

  // Parse a line with format ANIMAL|id|nome|idEspécie|idHabitat
  /**
   * Parses the animal information from the given components array and registers the animal in the hotel.
   *
   * @param components An array of strings containing the animal information. The expected order is:
   *                   [0] - some identifier (not used in this method)
   *                   [1] - animal ID
   *                   [2] - animal name
   *                   [3] - species ID
   *                   [4] - habitat ID
   * @throws UnrecognizedEntryException If there is an error during the registration process, such as a duplicate animal key,
   *                                    unknown species key, or unknown habitat key.
   */
  private void parseAnimal(String[] components) throws UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];
      String speciesId = components[3];
      String habitatId = components[4];

      _hotel.registerAnimal(id, name, speciesId, habitatId);
    } catch (CoreDuplicateAnimalKeyException | CoreUnknownSpeciesKeyException | CoreUnknownHabitatKeyException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  // Parse a line with format ESPÉCIE|id|nome
  /**
   * Parses the species information from the given components array and registers the species in the hotel.
   *
   * @param components An array of strings containing the species information. 
   *                   The first element is expected to be the species ID, and the second element is the species name.
   * @throws UnrecognizedEntryException If the species entry is unrecognized or if there is a duplicate species key.
   */
  private void parseSpecies(String[] components) throws UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];

      _hotel.registerSpecies(id, name);
    } catch (CoreDuplicateSpeciesKeyException | CoreDuplicateSpeciesNameException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }
  
  // Parse a line with format TRATADOR|id|nome|idHabitat1,...,idHabitatN or
  // VETERINÁRIO|id|nome|idEspécie1,...,idEspécieN
  /**
   * Parses the employee information from the given components and registers the employee in the hotel system.
   * If responsibilities are provided, they are added to the employee.
   *
   * @param components An array of strings containing the employee information. The expected format is:
   *                   [0] - Unused
   *                   [1] - Employee ID
   *                   [2] - Employee Name
   *                   [3] - (Optional) Comma-separated list of responsibilities
   * @param empType    The type of the employee.
   * @throws UnrecognizedEntryException If there is an error during the parsing or registration process.
   */
  private void parseEmployee(String[] components, String empType) throws UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];

      _hotel.registerEmployee(id, name, empType);

      if (components.length == 4) {
        for(String responsibility : components[3].split(",")){
            _hotel.addResponsibility(id, responsibility);
        }
      }
    } catch (CoreDuplicateEmployeeKeyException | CoreUnknownEmployeeKeyException |CoreNoResponsibilityException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  // Parse a line with format VACINA|id|nome|idEspécie1,...,idEspécieN
  /**
   * Parses the vaccine information from the given components array and registers the vaccine in the hotel.
   *
   * @param components An array of strings containing the vaccine information. 
   *                   The expected format is:
   *                   - components[1]: Vaccine ID
   *                   - components[2]: Vaccine name
   *                   - components[3] (optional): Comma-separated list of species IDs
   * @throws UnrecognizedEntryException If there is an error during the registration of the vaccine,
   *                                    such as a duplicate vaccine key or unknown species key.
   */
  private void parseVaccine(String[] components) throws UnrecognizedEntryException{
    try {
      String id = components[1];
      String name = components[2];
      String[] speciesIds = components.length == 4 ? components[3].split(",") : new String[0];
      _hotel.registerVaccine(id, name, speciesIds);
    } catch (CoreDuplicateVaccineKeyException | CoreUnknownSpeciesKeyException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  // Parse a line with format ÁRVORE|id|nome|idade|dificuldade|tipo
  /**
   * Parses the components array to extract tree information and creates a tree in the hotel.
   *
   * @param components An array of strings containing tree information. Expected format:
   *                   [unused, id, name, age, diff, type]
   * @throws UnrecognizedEntryException If the entry is invalid or a tree with the same ID already exists.
   */
  private void parseTree(String[] components) throws UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];
      int age = Integer.parseInt(components[3]);
      int diff = Integer.parseInt(components[4]);
      String type = components[5];

      _hotel.createTree(id, name, age, diff, type);
    } catch (CoreDuplicateTreeKeyException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  // Parse a line with format HABITAT|id|nome|área|idÁrvore1,...,idÁrvoreN
  /**
   * Parses the habitat information from the given components array and registers it in the hotel system.
   * 
   * @param components An array of strings containing habitat information. The expected format is:
   *                   components[1] - Habitat ID
   *                   components[2] - Habitat name
   *                   components[3] - Habitat area (as an integer)
   *                   components[4] - (Optional) Comma-separated list of tree IDs
   * @throws UnrecognizedEntryException If the habitat information is invalid or if there are issues with registering the habitat.
   */
  private void parseHabitat(String[] components) throws UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];
      int area = Integer.parseInt(components[3]);

      _hotel.registerHabitat(id, name, area);

      if (components.length == 5) {
        String[] listOfTree = components[4].split(",");
        for (String tree : listOfTree) {
          _hotel.registerTree(id, tree);
        }
      }
    } catch (CoreDuplicateHabitatKeyException | CoreUnknownHabitatKeyException | CoreUnknownTreeKeyException e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }
}

/**
 * Nota: O bloco catch presente nos vários métodos parse desta classe deverá ter em conta
 * as várias excepções que podem acontecer no contexto do bloco try em questão.
 * Estas excepções do domínio têm que ser definidas por cada grupo e devem representar situações
 * de erro específicas do domínio.
 **/