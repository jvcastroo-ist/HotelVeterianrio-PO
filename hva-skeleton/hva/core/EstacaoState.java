package hva.core;

import java.io.Serializable;

public interface EstacaoState extends Serializable{
  public EstacaoState mudarEstacao();
  public String getCicloBio();
  public int getEsforcoSazonal();
}