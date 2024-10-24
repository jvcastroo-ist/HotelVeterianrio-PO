package hva.core;

public class CaducaOutonoState implements EstacaoState {
  @Override
  public EstacaoState mudarEstacao() {
    return new CaducaInvernoState();
  }

  @Override
  public String getCicloBio() {
    return "LARGARFOLHAS";
  }

  @Override
  public int getEsforcoSazonal() {
    return 5;
  }  
}
