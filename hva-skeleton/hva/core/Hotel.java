package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * Whether the Hotel is in a dirty state, that is, if it was modified
   * since the last time it was saved (or created).
   */
  private boolean _modified = false;

  private Estacao _estacaoAno;
  private Map<String, Arvore> _arvores;
  private Map<String, Funcionario> _funcionarios;
  private Map<String, Habitat> _habitats;
  private Map<String, Animal> _animais;
  private Map<String, Especie> _especies; 
  private Map<String, Vacina> _vacinas;
  private List<RegistoVacina> _registoVacinas;

  /**
   * Constructs a new Hotel instance.
   * 
   * <p>This constructor initializes the hotel with the following default settings:
   * <ul>
   *   <li>Sets the initial season to SPRING.</li>
   *   <li>Initializes the maps for trees, employees, habitats, animals, species, and vaccines.</li>
   *   <li>Initializes the list for vaccine records.</li>
   * </ul>
   */
  public Hotel() {
    // Set the initial season
    _estacaoAno = Estacao.PRIMAVERA;
    
    // Initialize the maps
    _arvores = new HashMap<>();
    _funcionarios = new HashMap<>();
    _habitats = new HashMap<>();
    _animais = new HashMap<>();
    _especies = new HashMap<>(); 
    _vacinas = new HashMap<>();
    _registoVacinas = new ArrayList<>();
  }

  /**
   * Get whether the Hotel has been modified since it was last cleaned. The
   * warehouse is cleaned when it is saved to disk.
   *
   * @return the value of the dirty flag
   */
  public boolean isModified() {
    return this._modified;
  }

  /**
   * Turn the dirty flag off to indicate the warehouse state has been saved.
   */
  public void clean() {
    this._modified = false;
  }


  /**
   * Turn the dirty flag on to indicate a modification has occurred since last
   * clean-up (i.e. saved).
   */
  private void dirty() {
    this._modified = true;
  }
  
  /*
   * 
   */
  /**
   * Advances the current season to the next one.
   * Updates the internal state to reflect the change and marks the object as modified.
   *
   * @return the ordinal value of the new season.
   */
  public int avancaEstacao() {
    _estacaoAno = _estacaoAno.proximaEstacao();
    dirty();
    return _estacaoAno.ordinal();
  }

  private String lowerCase(String str){
    return str.toLowerCase();
  }

  // Regista um novo animal
  /**
   * Registers a new animal in the hotel system.
   *
   * @param animalId the unique identifier of the animal
   * @param nome the name of the animal
   * @param speciesId the unique identifier of the species
   * @param habitatId the unique identifier of the habitat
   * @throws CoreDuplicateAnimalKeyException if an animal with the given ID already exists
   * @throws CoreUnknownSpeciesKeyException if the species with the given ID does not exist
   * @throws CoreUnknownHabitatKeyException if the habitat with the given ID does not exist
   */
  public void registerAnimal(String animalId, String nome, String speciesId, String habitatId) throws CoreDuplicateAnimalKeyException, CoreUnknownSpeciesKeyException, CoreUnknownHabitatKeyException {  
    String animalIdKey = lowerCase(animalId);
    String speciesIdKey = lowerCase(speciesId);
    String habitatIdKey = lowerCase(habitatId);
    Especie e = getEspecie(speciesIdKey); // Throes exception if species does not exist
    Habitat h = getHabitat(habitatIdKey); // hrows exception if habitat does not exist

    // Verifies Exceptions
    if (_animais.containsKey(animalIdKey)) 
      throw new CoreDuplicateAnimalKeyException(animalId); // Se o animal já existir, lança exceção
    
    // Create new animal 
    Animal novoAnimal = new Animal(animalId, nome, e, h);
    // Adds the animal to the hashmap
    _animais.put(animalIdKey, novoAnimal); 
    // Adds the animal to the species
    e.addAnimal(novoAnimal);  
    // Adds the animal to the habitat
    h.addAnimal(novoAnimal);  

    dirty();
  } 

  // Regista um funcionário
  /**
   * Registers a new employee in the system.
   *
   * @param employeeId The unique identifier for the employee.
   * @param name The name of the employee.
   * @param empType The type of the employee, either "VET" for Veterinario or any other value for Tratador.
   * @throws CoreDuplicateEmployeeKeyException if an employee with the given employeeId already exists.
   */
  public void registerEmployee(String employeeId, String name, String empType) throws CoreDuplicateEmployeeKeyException { 
    String employeeIdKey = lowerCase(employeeId);
    // Verify if the employee already exists
    if(_funcionarios.containsKey(employeeIdKey))
      throw new CoreDuplicateEmployeeKeyException(employeeId);

    // Verifies the type of employee and creates the object
    Funcionario f = (empType.equals("VET")) ? new Veterinario(employeeId, name) : new Tratador(employeeId, name);
    // Adds the employee to the hashmap
    _funcionarios.put(employeeIdKey, f);

    dirty();
  }

  /**
   * Adds a responsibility to a given employee.
   *
   * @param f the employee to whom the responsibility will be assigned
   * @param responsibilityId the ID of the responsibility to be assigned
   * @throws CoreNoResponsibilityException if the responsibility does not exist
   */
  public void addResponsibility(Funcionario f, String responsibilityId) throws CoreNoResponsibilityException {
    String responsibilityIdKey = lowerCase(responsibilityId);
    // Verifies if the responsibility exists (either species or habitat)
    Responsabilidade r = (_especies.containsKey(responsibilityIdKey)) ? _especies.get(responsibilityIdKey) : _habitats.get(responsibilityIdKey);
    // Calls the method of the employee that assigns the responsibility
    f.operaResponsabilidade(r, true);

    dirty();
  }

  //Atribui uma responsabilidade a um funcionario
  /**
   * Removes a responsibility from a given employee.
   *
   * @param f the employee from whom the responsibility will be removed
   * @param responsibilityId the ID of the responsibility to be removed
   * @throws CoreNoResponsibilityException if the responsibility does not exist
   */
  public void removeResponsibilidade(Funcionario f, String responsibilityId) throws CoreNoResponsibilityException {
    String responsibilityIdKey = lowerCase(responsibilityId);
    // Verifies if the responsibility exists (either species or habitat)
    Responsabilidade r = (_especies.containsKey(responsibilityIdKey)) ? _especies.get(responsibilityIdKey) : _habitats.get(responsibilityIdKey);
    // Calls the method of the employee that removes the responsibility
    f.operaResponsabilidade(r, false);
    
    dirty();
  }

  // Regista uma especie 
  /**
   * Registers a new species in the hotel system.
   *
   * @param speciesId The unique identifier for the species.
   * @param name The name of the species.
   * @throws CoreDuplicateSpeciesKeyException if a species with the given ID already exists.
   */
  public void registerSpecies(String speciesId, String name) throws CoreDuplicateSpeciesKeyException {
    String speciesIdKey = lowerCase(speciesId);
    // Verifies if the species already exists
    if (_especies.containsKey(speciesIdKey)) 
      // Throws exception if it already exists
      throw new CoreDuplicateSpeciesKeyException(speciesId);
    
    Especie e = new Especie(speciesId, name);
    _especies.put(speciesIdKey, e);

    dirty();
  }

  // Regista um habitat
  /**
   * Registers a new habitat in the hotel.
   *
   * @param habitatId The unique identifier for the habitat.
   * @param nome The name of the habitat.
   * @param area The area of the habitat.
   * @throws CoreDuplicateHabitatKeyException If a habitat with the given ID already exists.
   */
  public void registerHabitat(String habitatId, String nome, int area/*adicionar vetor arvores */) throws CoreDuplicateHabitatKeyException {
    String habitatIdKey = lowerCase(habitatId);
    if (_habitats.containsKey(habitatIdKey)) 
      throw new CoreDuplicateHabitatKeyException(habitatId);

    Habitat novoHabitat = new Habitat(habitatId, nome, area);
    
    _habitats.put(habitatIdKey, novoHabitat);

    dirty();
  }

  
  /**
   * Registers a new vaccine in the system.
   *
   * @param vaccineId The unique identifier for the vaccine.
   * @param nome The name of the vaccine.
   * @param speciesIds An array of species identifiers that the vaccine is applicable to.
   * @throws CoreDuplicateVaccineKeyException If a vaccine with the same identifier already exists.
   * @throws CoreUnknownSpeciesKeyException If any of the provided species identifiers are unknown.
   */
  public void registerVaccine(String vaccineId, String nome, String[] speciesIds) throws CoreDuplicateVaccineKeyException, CoreUnknownSpeciesKeyException {
    // Criates a list of species
    List<Especie> species = new ArrayList<>();
    
    String vaccineIdKey = lowerCase(vaccineId);
    if (_vacinas.containsKey(vaccineIdKey)) 
      throw new CoreDuplicateVaccineKeyException(vaccineId);
    // Check if the vaccine already exists
    for (String id : speciesIds) {
      id = lowerCase(id);
      Especie e = getEspecie(id);  
      species.add(e);
    }
    // Criates a new vaccine
    Vacina v = new Vacina(vaccineId, nome, species);
    // Adds the vaccine to the hashmap
    _vacinas.put(vaccineIdKey, v);

    dirty();
  }

  // Cria uma arvore **** NAO ESQUECER QUE A ARVORE PRECISA SER PRINTADA DEPOIS Q É FEITA  
  /**
   * Registers a tree to a specified habitat.
   *
   * @param habitatId The ID of the habitat to which the tree will be associated.
   * @param idsTree An array of tree IDs to be registered to the habitat.
   * @throws CoreUnknownHabitatKeyException If the habitat ID is not found.
   */
  public void registerTree(String habitatId, String[] idsTree) throws CoreUnknownHabitatKeyException {
    habitatId = lowerCase(habitatId);
    // Associates the trees to the habitat
    Habitat hab = getHabitat(habitatId);

    // Adds the trees to the habitat
    for (String id : idsTree) {
      id = lowerCase(id);
      Arvore a = getArvore(id);
      hab.addArvore(a);
    }

    dirty();
  }

  /**
   * Creates a tree and adds it to the collection of trees.
   *
   * @param treeId The unique identifier for the tree.
   * @param name The name of the tree.
   * @param age The age of the tree.
   * @param dificuldadeBase The base difficulty level associated with the tree.
   * @param type The type of the tree, either "CADUCA" or "PERENE".
   * @throws CoreDuplicateTreeKeyException If a tree with the given treeId already exists.
   */
  public void createTree(String treeId, String name, int age, int dificuldadeBase, String type) throws CoreDuplicateTreeKeyException {
    String treeIdKey = lowerCase(treeId);
    if(_arvores.containsKey(treeIdKey))
      throw new CoreDuplicateTreeKeyException(treeId);

    // Verifies the type of tree and creates the object
    Arvore a = (type.equals("CADUCA")) ? new ArvoreCaduca(treeId, name, age, dificuldadeBase, _estacaoAno) : new ArvorePerene(treeId, name, age, dificuldadeBase, _estacaoAno);
    _arvores.put(treeIdKey, a);

    dirty();
  }

  // Recebe uma coleção de objetos e devolve uma lista de strings(visualizaçoes) 
  /**
   * Converts a collection of items to a collection of their string representations.
   *
   * @param items the collection of items to be visualized
   * @return an unmodifiable list of string representations of the items
   */
  public Collection<String> visualiza(Collection<?> items) {
    List<String> view = new ArrayList<>();
    for (Object item : items) {
      view.add(item.toString());
    }
    return Collections.unmodifiableList(view);
  }

  // Ordena uma lista de ids e retorna uma lista de objetos ordenados pelo id
  /**
   * Sorts the keys of the given map in a case-insensitive lexicographical order and returns a list of the corresponding values.
   *
   * @param <T> the type of the values in the map
   * @param map the map whose keys are to be sorted
   * @return a list of values sorted according to the lexicographical order of their keys
   */
  public <T>List <T>sortIds(Map<String, T> map) {
    List<String> idList = new ArrayList<>(map.keySet()); // Faz uma lista das keys do map
    idList.sort(String.CASE_INSENSITIVE_ORDER); // ordena os ids pela ordem lexicografica
    return idList.stream().map(map::get).collect(Collectors.toList()); // transforma os ids em seus proprios objetos
  }

  // Visualiza todos animais
  /**
   * Retrieves a collection of all animal IDs in the hotel, sorted in ascending order.
   *
   * @return a collection of sorted animal IDs.
   */
  public Collection<String> visualizaTodosAnimais() {
    return visualiza(sortIds(_animais));
  }

  // Visualiza todas arvores
  /**
   * Retrieves a collection of all tree IDs in the hotel, sorted in a specific order.
   *
   * @return A collection of sorted tree IDs.
   */
  public Collection<String> visualizaTodasArvores() {
    return visualiza(sortIds(_arvores));
  }

  // Visualiza todos funcionarios
  /**
   * Retrieves a collection of all employee IDs in the hotel.
   * The IDs are sorted before being returned.
   *
   * @return a collection of sorted employee IDs.
   */
  public Collection<String> visualizaTodosFuncionarios() {
    return visualiza(sortIds(_funcionarios)); 
  }

  // Visualiza todos habitats
  /**
   * Retrieves a collection of all habitat IDs in the hotel.
   * The habitat IDs are sorted before being returned.
   *
   * @return a sorted collection of habitat IDs.
   */
  public Collection<String> visualizaTodosHabitats() {
    return visualiza(sortIds(_habitats));
  }

  // Visualiza todas vacinas
  /**
   * Retrieves a collection of all vaccine IDs, sorted in a specific order.
   *
   * @return A collection of sorted vaccine IDs.
   */
  public Collection<String> visualizaTodasVacinas() {
    return visualiza(sortIds(_vacinas));
  }

  /**
   * Retrieves the current season of the year.
   *
   * @return the current season of the year as an {@link Estacao} enum.
   */
  public Estacao getEstacaoAno() {
    return _estacaoAno;
  }

  /**
   * Retrieves a Funcionario (employee) from the collection based on the provided key.
   *
   * @param key the unique identifier for the Funcionario to be retrieved.
   * @return the Funcionario associated with the given key, or null if no such Funcionario exists.
   */
  public Funcionario getFuncionario(String key) {
    return _funcionarios.get(key);
  }

  /**
   * Retrieves an Arvore object from the collection based on the provided key.
   *
   * @param key the key associated with the Arvore object to be retrieved
   * @return the Arvore object corresponding to the specified key, or null if no such object exists
   */
  public Arvore getArvore(String key) {
    return _arvores.get(key);
  }

  // Recebe um Id e devolve um habitat do hashmap _habitats 
  /**
   * Retrieves a Habitat object based on the provided key.
   *
   * @param key the unique identifier for the habitat
   * @return the Habitat object associated with the given key
   * @throws CoreUnknownHabitatKeyException if no habitat is found for the given key
   */
  public Habitat getHabitat(String key) throws CoreUnknownHabitatKeyException{
    Habitat h = _habitats.get(key);
    if (h == null)
      throw new CoreUnknownHabitatKeyException(key); // Se o habitat não existir, lança exceção 
    return h;
  }

  public Animal getAnimal(String key) {
    return _animais.get(key);
  }

  // Recebe um Id e devolve uma especie do hashmap _especies 
  /**
   * Retrieves the species associated with the given key.
   *
   * @param key the key associated with the species to retrieve
   * @return the species associated with the given key
   * @throws CoreUnknownSpeciesKeyException if no species is found for the given key
   */
  public Especie getEspecie(String key) throws CoreUnknownSpeciesKeyException{
    Especie e = _especies.get(key);
    if(e == null) {
      throw new CoreUnknownSpeciesKeyException(key); // Se a espécie não existir, lança exceção que cria a especie
    }
    return e;
  }


  /**
   * Retrieves a Vacina object associated with the specified key.
   *
   * @param key the key associated with the desired Vacina object
   * @return the Vacina object corresponding to the specified key, or null if no such object exists
   */
  public Vacina getVacina(String key) {
    return _vacinas.get(key);
  }


  /**
   * Retrieves a map of animals in the hotel.
   *
   * @return a map where the keys are animal identifiers (as Strings) and the values are Animal objects.
   */
  public Map<String, Animal> getAnimals(){
    return _animais;
  }

  /**
   * Retrieves a map of all the employees (Funcionarios) in the hotel.
   *
   * @return a Map where the keys are employee identifiers (String) and the values are Funcionario objects.
   */
  public Map<String, Funcionario> getFuncionarios(){
    return _funcionarios;
  }

  /**
   * Retrieves the map of habitats in the hotel.
   *
   * @return a map where the keys are habitat names and the values are Habitat objects.
   */
  public Map<String, Habitat> getHabitats(){
    return _habitats;
  }

  /**
   * Retrieves the map of vaccines.
   *
   * @return a map where the keys are vaccine names and the values are Vacina objects.
   */
  public Map<String, Vacina> getVacinas(){
    return _vacinas;
  }

  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    new Parser(this).parseFile(filename);
    }
}
