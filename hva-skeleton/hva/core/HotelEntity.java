package hva.core;

import java.io.Serializable;
import java.util.*;
import java.util.stream.*;

public abstract class HotelEntity implements Serializable{
  private final String _id;
  private final String _nome;

  /**
   * Constructs a new HotelEntity with the specified id and name.
   *
   * @param id   the unique identifier for the hotel entity
   * @param nome the name of the hotel entity
   */
  protected HotelEntity(String id, String nome) {
    this._id = id;
    this._nome = nome;
  }

  /**
   * Retrieves the unique identifier of the hotel entity.
   *
   * @return the unique identifier as a String.
   */
  public String getId() {
    return _id;
  }

  /**
   * Retrieves the name of the hotel entity.
   *
   * @return the name of the hotel entity.
   */
  public String getNome() {
    return _nome;
  }

  /**
   * Sorts the list of objects by their IDs in a case-insensitive manner.
   *
   * @param <T> the type of objects in the list, which must extend HotelEntity
   * @param list the list of objects to be sorted
   * @return A sorted list of objects based on their IDs.
   */
  public <T extends HotelEntity> List<T> sort(List<T> list) {
    return list.stream().sorted((o1, o2) -> o1.getId().compareToIgnoreCase(o2.getId())).collect(Collectors.toList());
  }
}
