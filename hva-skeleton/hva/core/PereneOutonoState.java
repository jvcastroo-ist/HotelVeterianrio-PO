package hva.core;

public class PereneOutonoState implements EstacaoState {
  @Override
  public EstacaoState mudarEstacao() {
    return new PereneInvernoState();
  }

  @Override
  public String getCicloBio() {
    return "COMFOLHAS";
  }

  @Override
  public int getEsforcoSazonal() {
    return 1;
  }
}
