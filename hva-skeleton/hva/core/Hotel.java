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

  // Constructor
  public Hotel() {
    _estacaoAno = Estacao.PRIMAVERA; // Definição da estação global
    // CRIAÇÃO DAS HASHS PARA CADA ENTIDADE 
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


  ///////////////////////////////////////77
  ///////////////////////////////////////////77
  //////////////////////////////////////////////////77
  ////////////////////////////////////////////////////77
  ///////////////////////////////////////////////////////////


  /**
   * Turn the dirty flag on to indicate a modification has occurred since last
   * clean-up (i.e. saved).
   */
  private void dirty() {
    this._modified = true;
  }


  // FIXME define more methods
  
  // Avança a estação, devolve o número
  public int avancaEstacao() {
    _estacaoAno = _estacaoAno.proximaEstacao();
    return _estacaoAno.ordinal();
  }

  // Regista um novo animal
  public void registerAnimal(String animalId, String nome, String speciesId, String habitatId) throws CoreDuplicateAnimalKeyException, CoreUnknownSpeciesKeyException, CoreUnknownHabitatKeyException {  
    Especie e = getEspecie(speciesId); // Pode dar throw se nao existir a especie
    Habitat h = getHabitat(habitatId); // Pode dar throw se nao existir o habitat
    // Verificar exceções
    if (_animais.containsKey(animalId)) 
      throw new CoreDuplicateAnimalKeyException(animalId); // Se o animal já existir, lança exceção
    // Cria o animal, após confirmação dos dados
    Animal novoAnimal = new Animal(animalId, nome, e, h);
    // Adiciona ao HashMap geral
    _animais.put(animalId, novoAnimal); 
    // Associa novoAnimal à espécie
    e.addAnimal(novoAnimal);  
    // Associa novoAnimal ao Habitat
    h.addAnimal(novoAnimal);  
  } 

  // Regista um funcionário
  public void registerEmployee(String empType, String employeeId, String name, String[] idResponsabilidades) throws CoreDuplicateEmployeeKeyException, CoreNoResponsibilityException{ 
    // Verificar se o funcionário já existe
    if(_funcionarios.containsKey(employeeId))
      throw new CoreDuplicateEmployeeKeyException(employeeId);
    // Constroi o funcionário
    Funcionario f = (empType.equals("VET")) ? new Veterinario(employeeId, name) : new Tratador(employeeId, name);
    // Adiciona na HashMap
    _funcionarios.put(employeeId, f);
    // Atribui as responsabilidades ao funcionário
      for (String id : idResponsabilidades) {
        addResponsibilidade(f, id);
      }
  }

  //Atribui uma responsabilidade a um funcionario
  public void addResponsibilidade(Funcionario f, String responsibilityId) throws CoreNoResponsibilityException {
    // Verifica se a responsabilidade existe(tanto especie como habitat)
    Responsabilidade r = (_especies.containsKey(responsibilityId)) ? _especies.get(responsibilityId) : _habitats.get(responsibilityId);
    // Chama o metodo do funcionario que atribui a responsabilidade
    f.operaResponsabilidade(r, true);
  }

  //Atribui uma responsabilidade a um funcionario
  public void removeResponsibilidade(Funcionario f, String responsibilityId) throws CoreNoResponsibilityException {
    // Verifica se a responsabilidade existe(tanto especie como habitat)
    Responsabilidade r = (_especies.containsKey(responsibilityId)) ? _especies.get(responsibilityId) : _habitats.get(responsibilityId);
    // Chama o metodo do funcionario que retira a responsabilidade
    f.operaResponsabilidade(r, false);
  }

  // Regista uma especie 
  public void registerSpecies(String speciesId, String name) throws CoreDuplicateSpeciesKeyException {
    // Verifica se a especie já existe no hashmap
    if (_especies.containsKey(speciesId)) 
      // Lança exceção se já existir
      throw new CoreDuplicateSpeciesKeyException(speciesId);
    
    Especie e = new Especie(speciesId, name);
    _especies.put(speciesId, e);
  }

  // Regista um habitat
  public void registerHabitat(String habitatId, String nome, int area/*adicionar vetor arvores */) throws CoreDuplicateHabitatKeyException {
    // Verifica se o habitat já existe no mapa
    if (_habitats.containsKey(habitatId)) 
      // Lança exceção se já existir
      throw new CoreDuplicateHabitatKeyException(habitatId);
    // Cria o novo habitat
    Habitat novoHabitat = new Habitat(habitatId, nome, area);
    // Adiciona o habitat ao HashMap
    _habitats.put(habitatId, novoHabitat);
  }

  
  public void registerVaccine(String vaccineId, String nome, String[] speciesIds) throws CoreDuplicateVaccineKeyException, CoreUnknownSpeciesKeyException {
    // Inicializa a lista de especies
    List<Especie> species = new ArrayList<>();
    // Verifica se já existe uma vacina com o mesmo identificador
    if (_vacinas.containsKey(vaccineId)) 
        throw new CoreDuplicateVaccineKeyException(vaccineId);
    // Valida as espécies fornecidas e adiciona a lista
    for (String id : speciesIds) {
        Especie e = getEspecie(id);  
        species.add(e);
    }
    // Cria a nova vacina com as espécies validadas
    Vacina v = new Vacina(vaccineId, nome, species);
    // Adiciona a vacina ao mapa
    _vacinas.put(vaccineId, v);
  }

  // Cria uma arvore **** NAO ESQUECER QUE A ARVORE PRECISA SER PRINTADA DEPOIS Q É FEITA  
  public void registerTree(String habitatId,String treeId, String name, int age, int dificuldadeBase, String type) throws CoreDuplicateTreeKeyException, CoreUnknownHabitatKeyException {
    // Verifica se arvore ja existe
    if(_arvores.containsKey(treeId))
      throw new CoreDuplicateTreeKeyException(treeId);
    // Verifica o tipo e constroi a arvore
    Arvore a = (type.equals("CADUCA")) ? new ArvoreCaduca(treeId, name, age, dificuldadeBase, _estacaoAno) : new ArvorePerene(treeId, name, age, dificuldadeBase, _estacaoAno);
    // Associa a arvore ao habitat
    Habitat h = getHabitat(habitatId);
    h.addArvore(a);
    // adiciona ao hashmap
    _arvores.put(treeId, a);
  }
  
  public Collection<String> visualiza(Collection<? extends Visualiza> T) {
    List<String> view = new ArrayList<>();
    for (Visualiza item : T) {
      view.add(item.visualiza(this));
    }
    return Collections.unmodifiableList(view);
  }
  

  // mudei o metodo para public por causa da logica impementada

  // Ordena uma lista de ids e retorna os objetos ordenados pelo id
  public <T>List <T>sortIds(Map<String, T> map) {
    List<String> idList = new ArrayList<>(map.keySet()); // Faz uma lista das keys do map
    idList.sort(String.CASE_INSENSITIVE_ORDER); // ordena os ids pela ordem lexicografica
    return idList.stream().map(map::get).collect(Collectors.toList()); // transforma os ids em seus proprios objetos
  }
  /* 
  // Visualiza todos animais
  public Collection<String> visualizaTodosAnimais() {
    return visualiza(sortIds(_animais));
  }

  // Visualiza todas arvores
  public Collection<String> visualizaTodasArvores() {
    return visualiza(sortIds(_arvores));
  }

  // Visualiza todos funcionarios
  public Collection<String> visualizaTodosFuncionarios() {
    return visualiza(sortIds(_funcionarios)); 
  }

  // Visualiza todos habitats
  public Collection<String> visualizaTodosHabitats() {
    return visualiza(sortIds(_habitats));
  }

  // Visualiza todas vacinas
  public Collection<String> visualizaTodasVacinas() {
    return visualiza(sortIds(_vacinas));
  }*/

  public Estacao getEstacaoAno() {
    return _estacaoAno;
  }

  public Funcionario getFuncionario(String key) {
    return _funcionarios.get(key);
  }

  public Arvore getArvore(String key) {
    return _arvores.get(key);
  }

  // Recebe um Id e devolve um habitat do hashmap _habitats 
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
  public Especie getEspecie(String key) throws CoreUnknownSpeciesKeyException{
    Especie e = _especies.get(key);
    if(e == null) {
      throw new CoreUnknownSpeciesKeyException(key); // Se a espécie não existir, lança exceção que cria a especie
    }
    return e;
  }


  public Vacina getVacina(String key) {
    return _vacinas.get(key);
  }


  public Map<String, Animal> getAnimals(){
    return _animais;
  }

  public Map<String, Funcionario> getFuncionarios(){
    return _funcionarios;
  }

  public Map<String, Habitat> getHabitats(){
    return _habitats;
  }

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
