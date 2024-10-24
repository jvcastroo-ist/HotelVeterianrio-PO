package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;

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
   * The Hotel class represents a veterinary hotel 
   * It provides methods to register, retrieve, and manipulate these entities, as well as to manage the current season and track modifications.
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

  /* ************************************* *
   * ************ MENU PRINCIPAL ********* *
   * ************************************* */

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
  
  /**
   * Advances the current season to the next one.
   * Updates the internal state to reflect the change and marks the object as modified.
   *
   * @return the ordinal value of the new season.
   */
  public int avancaEstacao() {
    // Avança a estaçao global
    _estacaoAno = _estacaoAno.proximaEstacao();
    // aumenta a idade de todas arvores e configura estacaoAtual
    aumentaIdades();
    dirty();
    
    return _estacaoAno.ordinal();
  }

  public static void main(String[] args) {
    Hotel h = new Hotel();
    Arvore ac1 = new ArvoreCaduca("a1", "lol", 2, 3, h._estacaoAno);
    h._arvores.put("a1", ac1);
    int e = h._estacaoAno.ordinal(); 
    System.out.println(e);    
    e = h.avancaEstacao();
    System.out.println(e);
    System.out.println(ac1);
  }

  /**
   * Increases the age of all trees in the collection and sets their season to the current season.
   * This method iterates over all trees in the collection and performs two actions on each tree:
   * 1. Increases the age of the tree.
   * 2. Sets the season of the tree to the current season of the year.
   */
  private void aumentaIdades() {
    for (Arvore a : _arvores.values()) {
      a.aumentaIdade();
      a.mudarEstacao(a._estacaoState.mudarEstacao());
      // a.setEstacao(_estacaoAno);
    }
  }

  /**
   * Calculates the total satisfaction score by summing the satisfaction scores 
   * of animals and employees, and then rounding the result to the nearest integer.
   *
   * @return the total satisfaction score as an integer.
   */
  public long satisfacaoTotal() {
    double animais = satisfacaoEntity(_animais.values());
    double func = satisfacaoEntity(_funcionarios.values());
    return Math.round(animais+func);
  }

  /**
   * Calculates the total satisfaction from a collection of objects that implement the Satisfacao interface.
   *
   * @param lst a collection of objects that implement the Satisfacao interface
   * @return the sum of satisfaction values from all objects in the collection
   */
  private double satisfacaoEntity(Collection<? extends Satisfacao> lst) {
    double sum = 0;
    for (Satisfacao s : lst) {
       sum += s.satisfacao();
    }
    return sum;
  }

  /**
   * Converts the given string to lowercase.
   *
   * @param str the string to be converted to lowercase
   * @return the lowercase version of the input string
   */
  private String lowerCase(String str){
    return str.toLowerCase();
  }

  /* ************************************* *
   * ************ MENU ANIMAL ************ *
   * ************************************* */

  
  
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
    // Throws exception if species does not exist
    Especie e = getEspecie(speciesId); 
    // Throws exception if habitat does not exist
    Habitat h = getHabitat(habitatId); 
    // Verifies Exceptions
    if (_animais.containsKey(lowerCase(animalId))) 
      throw new CoreDuplicateAnimalKeyException(animalId); // Se o animal já existir, lança exceção
    // Create new animal 
    Animal novoAnimal = new Animal(animalId, nome, e, h);
    // Adds the animal to the hashmap
    _animais.put(lowerCase(animalId), novoAnimal); 
    // Adds the animal to the species
    e.addAnimal(novoAnimal);  
    // Adds the animal to the habitat
    h.addAnimal(novoAnimal);  

    dirty();
  } 

  /**
   * Transfers an animal to a new habitat.
   *
   * @param animalId the identifier of the animal to be transferred
   * @param habitatId the identifier of the habitat to which the animal will be transferred
   * @throws CoreUnknownAnimalKeyException if the animal with the specified identifier does not exist
   * @throws CoreUnknownHabitatKeyException if the habitat with the specified identifier does not exist
   */
  public void transfereAnimal(String animalId, String habitatId) throws CoreUnknownAnimalKeyException, CoreUnknownHabitatKeyException{
    // Throws exception if animal does not exist
    Animal a = getAnimal(animalId);
    // Throws exception if habitat does not exist
    Habitat h = getHabitat(habitatId);
    // remover o animal do habitat onde está
    h.removeAnimal(a);
    // Transfere o animal para o habitat
    a.transfereAnimal(h);
    // Adiciona animal ao novo habitat
    h.addAnimal(a);
    
    dirty();
  }

  /**
   * Calculates the satisfaction level of an animal.
   *
   * @param animalId the ID of the animal whose satisfaction level is to be calculated.
   * @return the rounded satisfaction level of the animal.
   * @throws CoreUnknownAnimalKeyException if the animal with the given ID does not exist.
   */
  public long calculaSatisfacaoAnimal(String animalId) throws CoreUnknownAnimalKeyException{
    // Throws exception if animal does not exist
    Animal a = getAnimal(animalId);
    
    return Math.round(a.satisfacao());
  }

  /* ************************************* *
   * ********* MENU FUNCIONÁRIO ********** *
   * ************************************* */
  
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
    _funcionarios.put(lowerCase(employeeIdKey), f);

    dirty();
  }

  /**
   * Verifies if the given responsibility is not null.
   *
   * @param r the responsibility to be verified
   * @throws CoreNoResponsibilityException if the responsibility is null
   */
  private void verifyResponsabilidade(String employeeId, String responsabilityId, Responsabilidade r) throws CoreNoResponsibilityException {
    if(r == null)
      throw new CoreNoResponsibilityException(employeeId, responsabilityId);
  }

  /**
   * Adds a responsibility to a given employee.
   *
   * @param f the employee to whom the responsibility will be assigned
   * @param responsibilityId the ID of the responsibility to be assigned
   * @throws CoreNoResponsibilityException if the responsibility does not exist
   */
  public void AUXaddResponsibility(Funcionario f, String responsibilityId) throws CoreNoResponsibilityException {
    String responsibilityIdKey = lowerCase(responsibilityId);
    // Verifies if the responsibility exists (either species or habitat)
    Responsabilidade r = (_especies.containsKey(responsibilityIdKey)) ? _especies.get(responsibilityIdKey) : _habitats.get(responsibilityIdKey);
    verifyResponsabilidade(f.getId(), responsibilityId, r);
    // Calls the method of the employee that assigns the responsibility
    f.operaResponsabilidade(r, true);
    
    dirty();
  }


  // // verificar se é preciso esta funcao, secalhar ja vem tudo direitinho
  // private boolean verifyResponsibility(Funcionario f, Responsabilidade r) {
  //   String typeF = f.getType();
  //   String typeR = r.getType();

  //   // verifica se Funcionario recebe Responsabilidade do seu tipo
  //   // VET -> ESP | TRT -> HAB
  //   return (typeF.equals("VET") && typeR.equals("ESP")) || (typeF.equals("TRT") && typeR.equals("HAB"));
  // }

  public void addResponsibility(Funcionario f, String responsibilityId) throws CoreNoResponsibilityException{
    String responsibilityIdKey = lowerCase(responsibilityId);
    //String funcionarioIdKey = lowerCase(f.getId()); MUDAR ISTO RECEBER O STRING SO -> MUDAR NO PARSE
    try {
      Responsabilidade r = (_especies.containsKey(responsibilityIdKey)) ? getEspecie(responsibilityId) : getHabitat(responsibilityId);
      //if(verifyResponsibility(f, r))
      f.atribuiResponsabilidade(r);
      dirty();
    } catch (CoreUnknownSpeciesKeyException | CoreUnknownHabitatKeyException e) {
      throw new CoreNoResponsibilityException(f.getId(), responsibilityId);
    }
  }

  


  /////////////////////////////////// DESCONTINUADO....
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
    Responsabilidade r = (_especies.containsKey(responsibilityIdKey)) ? _especies.get(responsibilityId) : _habitats.get(responsibilityId);
    verifyResponsabilidade(f.getId(), responsibilityId, r);
    // Calls the method of the employee that removes the responsibility
    f.operaResponsabilidade(r, false);

    dirty();
  }
  ///////////////////////////////77

  public void removeResponsibility(String funcionarioId, String responsibilityId) throws CoreUnknownEmployeeKeyException, CoreNoResponsibilityException{
    String funcionarioIdKey = lowerCase(funcionarioId);
    String responsibilityIdKey = lowerCase(responsibilityId);

    Funcionario f = getFuncionario(funcionarioIdKey);
    try {
      Responsabilidade r = (_especies.containsKey(responsibilityIdKey)) ? getEspecie(responsibilityId) : getHabitat(responsibilityId);

      if(!(f.isResponsabilidadeAtribuida(r))){
        throw new CoreNoResponsibilityException(funcionarioId, responsibilityId);
      }

      f.retiraResponsabilidade(r);
      dirty();

    } catch (CoreUnknownSpeciesKeyException | CoreUnknownHabitatKeyException e) {
      throw new CoreNoResponsibilityException(funcionarioId, responsibilityId);
    }
  }

  /**
   * Calculates the satisfaction level of an employee.
   *
   * @param employeeId The ID of the employee whose satisfaction level is to be calculated.
   * @return The rounded satisfaction level of the employee.
   * @throws CoreUnknownEmployeeKeyException If the employee with the given ID does not exist.
   */
  public long calculaSatisfacaoFuncionario(String employeeId) throws CoreUnknownEmployeeKeyException{
    // Throws exception if employee does not exist
    Funcionario f = getFuncionario(employeeId);
    
    return Math.round(f.satisfacao());
  }

  /* ************************************* *
   * *********** MENU HABITAT ************ *
   * ************************************* *
    
   
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
    // Verify if the habitat already exists
    if (_habitats.containsKey(habitatIdKey)) 
      throw new CoreDuplicateHabitatKeyException(habitatId);
    // Create the new habitat
    Habitat novoHabitat = new Habitat(habitatId, nome, area);
    // Puts it in the hashmap
    _habitats.put(lowerCase(habitatIdKey), novoHabitat);

    dirty();
  }

  /**
   * Changes the area of a specified habitat.
   *
   * @param habitatId The identifier of the habitat whose area is to be changed.
   * @param area The new area value to be set for the habitat.
   * @throws CoreUnknownHabitatKeyException if the habitat with the specified ID does not exist.
   */
  public void alteraAreaHabitat(String habitatId, int area) throws CoreUnknownHabitatKeyException{
    // Throws exception if habitat does not exist
    Habitat h = getHabitat(habitatId);
    // Changes the area of the habitat
    h.setArea(area);

    dirty();
  }




  // public void alteraInfluenciaEspecie(String habitatId, String especiesId, String ) {}





  /**
   * Registers a tree to a specified habitat.
   *
   * @param habitatId The ID of the habitat to which the tree will be associated.
   * @param idsTree An array of tree IDs to be registered to the habitat.
   * @throws CoreUnknownHabitatKeyException If the habitat ID is not found.
   */
  public void registerTree(String habitatId, String treeId) throws CoreUnknownHabitatKeyException, CoreUnknownTreeKeyException {
    // Associates the trees to the habitat
    Habitat hab = getHabitat(habitatId);
    // Adds the tree to the habitat
    Arvore a = getArvore(treeId);
    hab.addArvore(a);
    // Print the new tree
    a.toString();

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
    // Verefies if the tree already exists
    if(_arvores.containsKey(treeIdKey))
      throw new CoreDuplicateTreeKeyException(treeId);
    // Verifies the type of tree and creates the object
    Arvore a = (type.equals("CADUCA")) ? new ArvoreCaduca(treeId, name, age, dificuldadeBase, _estacaoAno) : new ArvorePerene(treeId, name, age, dificuldadeBase, _estacaoAno);
    // Puts it in the hashmap
    _arvores.put(lowerCase(treeIdKey), a);

    dirty();
  }

  public List<Animal> mostraAnimaisPorHabitat(String habitatId) throws CoreUnknownHabitatKeyException{
    Habitat h = getHabitat(habitatId);
    return h.getAnimals();
  }

  /**
   * Retrieves a list of trees (Arvore) for a given habitat.
   *
   * @param habitatId The identifier of the habitat.
   * @return A list of trees (Arvore) associated with the specified habitat, sorted by their ID.
   * @throws CoreUnknownHabitatKeyException If the habitat with the specified ID does not exist.
   */
  public List<Arvore> mostraArvorePorHabitat(String habitatId) throws CoreUnknownHabitatKeyException{
    // Verefies if the habitat exists
    Habitat h = getHabitat(habitatId);
    // returns the list of trees sorted by id.
    return h.getArvores();
  }

  /* ************************************* *
   * *********** MENU VACINAS ************ *
   * ************************************* */

 
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
    // Check if the vaccine already exists
    if (_vacinas.containsKey(vaccineIdKey)) 
      throw new CoreDuplicateVaccineKeyException(vaccineId);
    for (String id : speciesIds) {
      // Verefies if the species exists
      Especie e = getEspecie(id);  
      // adds it to the list 
      species.add(e);
    }
    // Criates a new vaccine
    Vacina v = new Vacina(vaccineId, nome, species);
    // Adds the vaccine to the hashmap
    _vacinas.put(lowerCase(vaccineIdKey), v);

    dirty();
  }

  /**
   * Vaccinates an animal with a specified vaccine by a veterinarian.
   *
   * @param vaccineId The ID of the vaccine to be administered.
   * @param vetId The ID of the veterinarian administering the vaccine.
   * @param animalId The ID of the animal to be vaccinated.
   * @throws CoreUnknownVaccineKeyException If the vaccine ID is unknown.
   * @throws CoreUnknownVeterinarianKeyException If the veterinarian ID is unknown or the employee is not a veterinarian.
   * @throws CoreUnknownAnimalKeyException If the animal ID is unknown.
   * @throws CoreVeterinarianNotAuthorizedException If the veterinarian is not authorized to vaccinate the species of the animal.
   * @throws CoreWrongVaccineException If the vaccine causes an abnormal reaction in the animal.
   */
  public void vacinarAnimal(String vaccineId, String vetId,String animalId) throws CoreUnknownVaccineKeyException, CoreUnknownVeterinarianKeyException, CoreUnknownAnimalKeyException, CoreVeterinarianNotAuthorizedException, CoreWrongVaccineException {
    Vacina v = getVacina(vaccineId);
    Animal a = getAnimal(animalId);
    try {
      // Verefies if the veterinarian exists 
      Funcionario f = getFuncionario(vetId);
      // Checks if f is of type Veterinario
      verificaVeterinario(f);
      // Cast to Veterinario
      Veterinario vet = (Veterinario)f;
      // Verefies if the vet can vaccinate the species
      verificaPermissaoVacina(vet, a.getEspecie()); // throws CoreVeterinarianNotAuthorizedException
      // creates the record
      RegistoVacina rv = v.vacinarAnimal(a, vet); 
      // adds to the hashmap
      _registoVacinas.add(rv);
      // vaccinate the animal, throws exception if damage is not "NORMAL"
      dirty();
      if (!(rv.getDano().toString().equals("NORMAL"))){
        throw new CoreWrongVaccineException(vaccineId, animalId);
      }
    } catch(CoreUnknownEmployeeKeyException e) {throw new CoreUnknownVeterinarianKeyException(vetId);}

  }

  // VERIFICAR ESTE GETID - NULL POINT
  private void verificaVeterinario(Funcionario f) throws CoreUnknownVeterinarianKeyException {
    if (!(f instanceof Veterinario)) 
      throw new CoreUnknownVeterinarianKeyException(f.getId());
  }

  /**
   * Verifies if the veterinarian has permission to vaccinate the given species.
   *
   * @param vet the veterinarian whose permissions are being checked
   * @param e the species that needs to be vaccinated
   * @throws CoreVeterinarianNotAuthorizedException if the veterinarian is not authorized to vaccinate the given species
   */
  private void verificaPermissaoVacina(Veterinario vet, Especie e) throws CoreVeterinarianNotAuthorizedException{
    if (!(vet.getEspecies().contains(e))) {
      throw new CoreVeterinarianNotAuthorizedException(vet.getId(), e.getId());
    }
  }

  /* ************************************* *
   * ********** MENU CONSULTAS *********** *
   * ************************************* */

  /**
   * Consults and retrieves a list of animals residing in a specified habitat.
   *
   * @param habitatId The identifier of the habitat to be queried.
   * @return A list of animals residing in the specified habitat, sorted by their ID.
   * @throws CoreUnknownHabitatKeyException If the specified habitat ID does not exist.
   */
  public List<Animal> consultaAnimaisPorHabitat(String habitatId) throws CoreUnknownHabitatKeyException {
    // Verefies if the habitat exists
    Habitat h = getHabitat(habitatId);
    // returns the list of animals sorted by the id
    return h.getAnimals();
  }

  /**
   * Retrieves the list of vaccination records for a specific animal.
   *
   * @param animalId The unique identifier of the animal.
   * @return A list of vaccination records associated with the specified animal.
   * @throws CoreUnknownAnimalKeyException If the animal with the given ID does not exist.
   */
  public List<RegistoVacina> consultaRegistoPorAnimal(String animalId) throws CoreUnknownAnimalKeyException {
    // Verefies if the animal exists
    Animal a = getAnimal(animalId);
    // returns the list of records of the animal
    return a.getRegistos();
  }

  /**
   * Consults the medical records of a veterinarian.
   *
   * @param vetId The identifier of the veterinarian.
   * @return A list of medical records associated with the veterinarian.
   * @throws CoreUnknownEmployeeKeyException If the employee key is unknown.
   * @throws CoreUnknownVeterinarianKeyException If the veterinarian key is unknown.
   */
  public List<RegistoVacina> consultaAtosMedicos(String vetId) throws CoreUnknownEmployeeKeyException, CoreUnknownVeterinarianKeyException{
    try {
      // Verefies if the veterinarian exists 
      Funcionario f = getFuncionario(vetId);
      // Checks if f is of type Veterinario
      verificaVeterinario(f);
      // Cast to Veterinario
      Veterinario vet = (Veterinario)f;
      // returns the list of records of the veterinarian
      return vet.getRegistos();
    } catch(CoreUnknownEmployeeKeyException e) {throw new CoreUnknownVeterinarianKeyException(vetId);}
  }

  /**
   * Retrieves a list of vaccine records that were administered carelessly.
   * 
   * This method iterates through the list of vaccine records and checks if the 
   * vaccine has caused any damage to the animal. If the damage is not classified 
   * as "NORMAL", the vaccine record is added to the list of careless vaccines.
   * 
   * @return a list of {@link RegistoVacina} objects that represent vaccines 
   *         given carelessly.
   */
  public List<RegistoVacina> consultaVacinaComIncuria() {
    // a list that will hold all vaccines given carelessly
    List<RegistoVacina> incuria = new ArrayList<>();
    for (RegistoVacina rv : _registoVacinas) 
      // if the vaccine has caused damage to the animal its added to the list
      if (!(rv.getDano().toString().equals("NORMAL")))
        incuria.add(rv);
    return incuria;
  }
  
  /* ************************************* *
   * ********* AUXILIAR METHODS ********** *
   * ************************************* */

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
    _especies.put(lowerCase(speciesIdKey), e);

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





  // NOT BEING USED

  // private <T>List <T>sortIds(Map<String, T> map) {
  //   List<String> idList = new ArrayList<>(map.keySet()); // Faz uma lista das keys do map
  //   idList.sort(String.CASE_INSENSITIVE_ORDER); // ordena os ids pela ordem lexicografica
  //   return idList.stream().map(map::get).collect(Collectors.toList()); // transforma os ids em seus proprios objetos
  // }




  /**
   * Sorts a collection of items that implement the {@link Comparable} interface.
   * The items are sorted by their ids, as defined by their {@code compareTo} method.
   * The sorted list is returned as an unmodifiable list.
   *
   * @param <T> the type of elements in the collection, which must extend {@link Comparable}
   * @param items the collection of items to be sorted
   * @return an unmodifiable list of the sorted items
   * @throws NullPointerException if the specified collection is null
   */
  public <T extends Comparable<? super T>> List<T> sortIds(Collection<T> items) {
    List<T> sortedItems = new ArrayList<>(items);
    Collections.sort(sortedItems); // Usa o método compareTo da interface Comparable
    return Collections.unmodifiableList(sortedItems);
  }



  // // Visualiza todas arvores
  // /**
  //  * Retrieves a collection of all tree IDs in the hotel, sorted in a specific order.
  //  *
  //  * @return A collection of sorted tree IDs.
  //  */
  // public Collection<String> visualizaTodasArvores() {
  //   return visualiza(sortIds(_arvores));
  // }


  /**
   * Retrieves the current season of the year.
   *
   * @return the current season of the year as an {@link Estacao} enum.
   */
  public Estacao getEstacaoAno() {
    return _estacaoAno;
  }
  
  /**
   * Retrieves a Funcionario (employee) from the hotel system based on the provided key.
   *
   * @param key The unique identifier for the employee.
   * @return The Funcionario object associated with the given key.
   * @throws CoreUnknownEmployeeKeyException If no employee is found with the provided key.
   */
  public Funcionario getFuncionario(String key) throws CoreUnknownEmployeeKeyException{
    Funcionario f = _funcionarios.get(lowerCase(key));
    if (f == null)
      throw new CoreUnknownEmployeeKeyException(key); // Se o funcionario não existir, lança exceção 
    return f;  
  }

  
  /**
   * Retrieves an Arvore (tree) object associated with the given key.
   *
   * @param key the key associated with the desired Arvore object
   * @return the Arvore object associated with the given key
   */
  public Arvore getArvore(String key) throws CoreUnknownTreeKeyException {
    Arvore a = _arvores.get(lowerCase(key));
    if( a == null){
      throw new CoreUnknownTreeKeyException(key);
    }
    return a;
  }

  /**
   * Retrieves a Habitat object based on the provided key.
   *
   * @param key the unique identifier for the habitat
   * @return the Habitat object associated with the given key
   * @throws CoreUnknownHabitatKeyException if no habitat is found for the given key
   */
  public Habitat getHabitat(String key) throws CoreUnknownHabitatKeyException{
    Habitat h = _habitats.get(lowerCase(key));
    if (h == null)
      throw new CoreUnknownHabitatKeyException(key); // Se o habitat não existir, lança exceção 
    return h;
  }

  /**
   * Retrieves an animal from the hotel using the provided key.
   *
   * @param key the unique identifier for the animal.
   * @return the Animal object associated with the given key.
   * @throws CoreUnknownAnimalKeyException if no animal is found with the provided key.
   */
  public Animal getAnimal(String key) throws CoreUnknownAnimalKeyException{
    Animal a = _animais.get(lowerCase(key));
    if (a == null)
      throw new CoreUnknownAnimalKeyException(key); // Se o animal não existir, lança exceção 
    return a;
  }

  /**
   * Retrieves the species associated with the given key.
   *
   * @param key the key associated with the species to retrieve
   * @return the species associated with the given key
   * @throws CoreUnknownSpeciesKeyException if no species is found for the given key
   */
  public Especie getEspecie(String key) throws CoreUnknownSpeciesKeyException{
    Especie e = _especies.get(lowerCase(key));
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
  public Vacina getVacina(String key) throws CoreUnknownVaccineKeyException {
    Vacina v = _vacinas.get(lowerCase(key));
    if(v == null) {
      throw new CoreUnknownVaccineKeyException(key); // Se a vacina não existir, lança exceção que cria a especie
    }
    return v;
  }


  /**
   * Retrieves a map of animals in the hotel.
   *
   * @return a map where the keys are animal identifiers (as Strings) and the values are Animal objects.
   */
  public Collection<Animal> getAnimals(){
    return _animais.values();
  }

  /**
   * Retrieves a map of all the employees (Funcionarios) in the hotel.
   *
   * @return a Map where the keys are employee identifiers (String) and the values are Funcionario objects.
   */
  public Collection<Funcionario> getEmployees(){
    return _funcionarios.values();
  }

  /**
   * Retrieves the map of habitats in the hotel.
   *
   * @return a map where the keys are habitat names and the values are Habitat objects.
   */
  public Collection<Habitat> getHabitats(){
    return _habitats.values();
  }

  /**
   * Retrieves the map of vaccines.
   *
   * @return a map where the keys are vaccine names and the values are Vacina objects.
   */
  public Collection<Vacina> getVaccines(){
    return _vacinas.values();
  }

  public List<RegistoVacina> getRegistoVacinas(){
    return _registoVacinas;
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
