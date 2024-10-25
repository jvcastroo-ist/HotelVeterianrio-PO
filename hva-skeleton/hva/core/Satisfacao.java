package hva.core;

import java.io.Serializable;

public interface Satisfacao extends Serializable{
  /**
   * Calculates and returns the satisfaction level.
   *
   * @return an integer representing the satisfaction level.
   */
  public double satisfacao();
}
