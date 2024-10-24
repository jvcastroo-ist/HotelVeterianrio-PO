package hva.core;

public class CaducaInvernoState implements EstacaoState{
  @Override
  public EstacaoState mudarEstacao() {
    return new CaducaPrimaveraState();
  }

  @Override
  public String getCicloBio() {
    return "SEMFOLHAS";
  }

  @Override
  public int getEsforcoSazonal() {
    return 0;
  }  
}
