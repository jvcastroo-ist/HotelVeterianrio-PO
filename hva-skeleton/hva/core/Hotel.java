package hva.core;

import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.DuplicateEmployeeKeyException;
import hva.app.exception.DuplicateHabitatKeyException;
import hva.app.exception.DuplicateVaccineKeyException;
import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.exception.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * Whether the warehouse is in a dirty state, that is, if it was modified
   * since the last time it was saved (or created).
   */
  private boolean _dirty = false;

  private Estacao _estacaoAno;
  private Map<String, Arvore> _arvores;
  private Map<String, Funcionario> _funcionarios;
  private Map<String, Habitat> _habitats;
  private Map<String, Animal> _animais;
  private Map<String, Especie> _especies; // alterei para discussão
  private Map<String, Vacina> _vacinas;
  private List<RegistoVacina> _registoVacinas;

  // Constructor
  public Hotel() {
    _estacaoAno = Estacao.PRIMAVERA;
    _arvores = new HashMap<>();
    _funcionarios = new HashMap<>();
    _habitats = new HashMap<>();
    _animais = new HashMap<>();
    _especies = new HashMap<>(); // alterei para discussão
    _vacinas = new HashMap<>();
    _registoVacinas = new ArrayList<>();
  }

  /**
   * Get whether the warehouse has been modified since it was last cleaned. The
   * warehouse is cleaned when it is saved to disk.
   *
   * @return the value of the dirty flag
   */
  public boolean isDirty() {
    return this._dirty;
  }

  /**
   * Turn the dirty flag off to indicate the warehouse state has been saved.
   */
  public void clean() {
    this._dirty = false;
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
    this._dirty = true;
  }


  // FIXME define more methods
  
  // Avança a estação
  public int avancaEstacao() {
    _estacaoAno = _estacaoAno.proximaEstacao();
    return _estacaoAno.ordinal();
  }


  public void registerAnimal(String animalId, String nome, String habitatId, String speciesId) throws DuplicateAnimalKeyException, UnknownSpeciesKeyException{  
    Animal a = getAnimal(animalId);
    Habitat h = getHabitat(habitatId);
    Especie e = getEspecie(speciesId);

    // if(e == null)
    // cria a especie efetivamente com o nome dado pelo Prompt
    // String speciesName = Prompt.speciesName();  // Solicita o nome da espécie ao utilizador
    // e = new Especie(speciesId, speciesName);
    // _especies.put(speciesId, e);  // Adiciona a nova espécie à lista de espécies */
    // perguntar ao prof se a especie nao existir como fazer para criar, qual funcao utilizar ou se pode ser esta mesmo
    // 

    if(a != null){
      throw new DuplicateAnimalKeyException(animalId);
      /* resolver excepcao no registerAnimal do menu try ... catch etc */
    } else if(e == null){
      throw new UnknownSpeciesKeyException(speciesId);
    }

    Animal novoAnimal = new Animal(animalId, nome, e, h);

    _animais.put(animalId, novoAnimal); // Adiciona ao HashMap geral
    e.addAnimal(novoAnimal);  // Associa novoAnimal à espécie
    h.addAnimal(novoAnimal);  // Associa novoAnimal ao Habitat
  } 


  public void registerEmployee(String employeeId, String name, String empType) throws DuplicateEmployeeKeyException{ 
    Funcionario f = getFuncionario(employeeId);

    if(f != null){
      throw new DuplicateEmployeeKeyException(employeeId);
    }

    if(empType.equals("VET")){
      f = new Veterinario(employeeId, name);
    } else {
      f = new Tratador(employeeId, name);
    }

    _funcionarios.put(employeeId, f);
  }

  public void addResponsibility(String employeeId, String responsibilityId) throws NoResponsibilityException{
    Funcionario f = _funcionarios.get(employeeId);
    // Especie e = _especies.get(responsibility);
    // Habitat h = _habitats.get(responsibility);



    // 
    switch (f) {
      case Veterinario vet -> {
        Especie e = getEspecie(responsibilityId);
              
        if(e == null){
          throw new NoResponsibilityException(employeeId, responsibilityId);
        }
              
        if(vet.getEspecies().contains(e)){
          // Já tem essa responsabilidade, então não faz nada
          return;
        }
        vet.getEspecies().add(e); // Atribui nova responsabilidade
        } case Tratador tratador -> {
          Habitat h = getHabitat(responsibilityId);
              
          if(h == null){
            throw new NoResponsibilityException(employeeId, responsibilityId);
          }
              
          if (tratador.getHabitats().contains(h)) {
            // Já tem essa responsabilidade, então não faz nada
            return;
          }
              
          tratador.getHabitats().add(h);  // Atribui nova responsabilidade
          }
          default -> {
          }
      }
    }
    
  public void registerSpecies(String speciesId, String name) {

    // lançar exceçao no registaEpecie  duplicate
    Especie novaEspecie = new Especie(speciesId, name);
    _especies.put(speciesId, novaEspecie);
  }

  // piroca
  public Habitat registerHabitat(String habitatId, String nome, int area) throws DuplicateHabitatKeyException {
    // Verifica se o habitat já existe no mapa
    if (_habitats.containsKey(habitatId)) {
      // Lança exceção se já existir
      throw new DuplicateHabitatKeyException(habitatId);
    }

    // Cria o novo habitat
    Habitat novoHabitat = new Habitat(habitatId, nome, area);

    // Adiciona o habitat ao HashMap
    _habitats.put(habitatId, novoHabitat);
    return novoHabitat;
  }

  
  public void registerVaccine(String vaccineId, String nome, String[] speciesIds) throws DuplicateVaccineKeyException, UnknownSpeciesKeyException {
    
    // Verifica se já existe uma vacina com o mesmo identificador
    Vacina v = getVacina(vaccineId);
    if (v != null) {
        throw new DuplicateVaccineKeyException(vaccineId);
    }

    // Lista para armazenar as espécies validadas
    List<Especie> especies = new ArrayList<>();

    // Valida as espécies fornecidas
    for (String s : speciesIds) {
        Especie e = getEspecie(s.trim());  // Recupera a espécie e remove espaços em branco do Id
        if (e == null) {
            throw new UnknownSpeciesKeyException(s);
        }
        especies.add(e);  // Adiciona a espécie validada à lista
    }

    // Cria a nova vacina com as espécies validadas
    v = new Vacina(vaccineId, nome, (ArrayList<Especie>) especies);

    // Adiciona a vacina ao mapa
    _vacinas.put(vaccineId, v);
}

  
  
  public void createTree(String TreeId, String name, String type, int age, int diff){
  // perguntar ao prof se nao recebe id Habitat
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
  public <T> Collection<T> sortIds(Map<String, T> map) {
    List<String> idList = new ArrayList<>(map.keySet()); // Faz uma lista das keys do map
    idList.sort(String.CASE_INSENSITIVE_ORDER); // ordena os ids pela ordem lexicografica
    return idList.stream().map(map::get).collect(Collectors.toList()); // transforma os ids em seus proprios objetos
  }

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
  }

  public Estacao getEstacaoAno() {
    return _estacaoAno;
  }

  public Funcionario getFuncionario(String key) {
    return _funcionarios.get(key);
  }

  public Arvore getArvore(String key) {
    return _arvores.get(key);
  }

  public Habitat getHabitat(String key) {
    return _habitats.get(key);
  }

  public Animal getAnimal(String key) {
    return _animais.get(key);
  }

  public Especie getEspecie(String key) {
    return _especies.get(key);
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
