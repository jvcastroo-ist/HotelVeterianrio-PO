package hva.core;

public class PereneInvernoState  implements EstacaoState {
  @Override
  public EstacaoState mudarEstacao() {
    return new PerenePrimaveraState();
  }

  @Override
  public String getCicloBio() {
    return "LARGARFOLHAS";
  }

  @Override
  public int getEsforcoSazonal() {
    return 2;
  }
}
