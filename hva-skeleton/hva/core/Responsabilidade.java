package hva.core;

import java.util.*;
import java.io.*;

public abstract class Responsabilidade implements Serializable{
  public abstract String getId();

  
  /**
   * Generates a concatenated string of IDs from a collection of Responsabilidade objects.
   *
   * @param T a collection of objects that extend the Responsabilidade class
   * @return a string containing the IDs of the Responsabilidade objects, separated by commas and prefixed with a "|". 
   *         Returns an empty string if the collection is empty.
   */
  public static String idResponsabilidade(Collection<? extends Responsabilidade> T) {
    List<String> ids = new ArrayList<>();
    if (T.isEmpty()) {return "";}
    for (Responsabilidade r : T) {
      ids.add(r.getId());
    }
    return "|" + String.join(",", ids);
  }
}
