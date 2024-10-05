package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;
// FIXME import classes

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  private Estacao _estacaoAno;
  private Map<String, Arvore> _arvores;
  private Map<String, Habitat> _habitats;
  private Map<String, Animal> _animais;
  private Map<String, Vacina> _vacinas;
  private List<RegistoVacina> _registoVacinas;

  // Constructor
  public Hotel() {
    _estacaoAno = Estacao.PRIMAVERA;
    _arvores = new HashMap<>();
    _habitats = new HashMap<>();
    _animais = new HashMap<>();
    _vacinas = new HashMap<>();
    _registoVacinas = new ArrayList<>();
  }

  // FIXME define more methods
  
  // Avança a estação
  public int avancaEstacao() {
    _estacaoAno = _estacaoAno.proximaEstacao();
    return _estacaoAno.ordinal();
  }

  public Collection<String> visualiza(Collection<? extends Visualiza> T) {
    List<String> view = new ArrayList<>();
    for (Visualiza item : T) {
      view.add(item.visualiza(this));
    }
    return Collections.unmodifiableList(view);
  }

  // Visualiza todos animais
  public Collection<String> visualizaTodosAnimais() {
    return visualiza(_animais.values());
  }

  // Visualiza todas arvores
  public Collection<String> visualizaTodasArvores() {
    return visualiza(_arvores.values());
  }

  // Visualiza todos funcionarios
  public Collection<String> visualizaTodosFuncionarios() {
    return visualiza(_funcionarios.values()); // Assuming you have a _funcionarios map
  }

  // Visualiza todos habitats
  public Collection<String> visualizaTodosHabitats() {
    return visualiza(_habitats.values());
  }

  // Visualiza todas vacinas
  public Collection<String> visualizaTodasVacinas() {
    return visualiza(_vacinas.values());
  }

  public Estacao getEstacaoAno() {
    return _estacaoAno;
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

  public Vacina getVacina(String key) {
    return _vacinas.get(key);
  }




  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    //FIXME implement method
  }
}
