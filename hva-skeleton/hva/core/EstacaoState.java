package hva.core;

import java.io.Serializable;

public interface EstacaoState extends Serializable{
  /**
   * Changes the current state of the station.
   *
   * @return the new state of the station after the change.
   */
  public EstacaoState mudarEstacao();

  /**
   * Retrieves the biological cycle information.
   *
   * @return a String representing the biological cycle.
   */
  public String getCicloBio();

  /**
   * Retrieves the seasonal effort value.
   *
   * @return an integer representing the seasonal effort.
   */
  public int getEsforcoSazonal();
}