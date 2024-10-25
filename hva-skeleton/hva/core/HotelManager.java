package hva.core;

import hva.core.exception.*;
import java.io.*;

/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel.
 **/
public class HotelManager {
  /** The current zoo hotel */ // Should we initialize this field?
  private Hotel _hotel = new Hotel();

  /**
   * Name of file storing current store.
   */
  private String _filename = "";

  /**
   * Cria um novo hotel, limpando o estado atual.
   */
  public void newHotel() {
    _hotel = new Hotel();  // Cria uma nova instância do hotel
    _filename = "";        // Reseta o nome do arquivo associado, se houver
  }

  /**
   * Advances the current season in the hotel management system.
   *
   * @return an integer representing the result of advancing the season.
   */
  public int advanceSeason(){
    return _hotel.avancaEstacao();
  }

  /**
   * Calculates and returns the global satisfaction level of the hotel.
   *
   * @return the total satisfaction score of the hotel.
   */
  public double globalSatisfaction(){
    return _hotel.satisfacaoTotal();
  }
  
  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    if(_filename == null || _filename.isBlank())
      throw new MissingFileAssociationException();
    
    if(_hotel.isModified()){
      try (ObjectOutputStream out = new ObjectOutputStream(
          new BufferedOutputStream(new FileOutputStream(_filename)))){
          out.writeObject(_hotel);
      }
      _hotel.clean();
    }
  }
  
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    _filename = filename;
    save();
  }
  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException {
    try(ObjectInputStream in = new ObjectInputStream(
      new BufferedInputStream(new FileInputStream(filename)))){
      _hotel = (Hotel) in.readObject();
      this._filename = filename;
    } catch(IOException | ClassNotFoundException e){
      throw new UnavailableFileException(filename);
    }
  }
  
  /**
   * Read text input file and initializes the current zoo hotel (which should be empty)
   * with the domain entitiesi representeed in the import file.
   *
   * @param filename name of the text input file
   * @throws ImportFileException if some error happens during the processing of the
   * import file.
   **/
  public void importFile(String filename) throws ImportFileException {
    try {
      _hotel.importFile(filename);
    } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(filename, e);
    }
  } 
  
  /**
   * Returns the zoo hotel managed by this instance.
   *
   * @return the current zoo hotel
   **/
  public final Hotel getHotel() {
    return _hotel;
  }
}
