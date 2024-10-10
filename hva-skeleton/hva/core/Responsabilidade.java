package hva.core;

import java.util.*;
import java.io.*;

public abstract class Responsabilidade implements Serializable{
  public abstract String getId();

  // Recebe uma lista de especies/habitats e devolve os ids numa unica String formatada 
  public static String idResponsabilidade(Collection<? extends Responsabilidade> T) {
    List<String> ids = new ArrayList<>();
    if (T.isEmpty()) {return "";}
    for (Responsabilidade r : T) {
      ids.add(r.getId());
    }
    return "|" + String.join(",", ids);
  }
}
