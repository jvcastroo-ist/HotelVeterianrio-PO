package hva.core;

public class CaducaVeraoState implements EstacaoState {
  @Override
  public EstacaoState mudarEstacao() {
    return new CaducaOutonoState();
  }

  @Override
  public String getCicloBio() {
    return "COMFOLHAS";
  }

  @Override
  public int getEsforcoSazonal() {
    return 2;
  }  
}
