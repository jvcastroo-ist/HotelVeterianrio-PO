package hva.core;

public class PerenePrimaveraState implements EstacaoState {
  @Override
  public EstacaoState mudarEstacao() {
    return new PereneVeraoState();
  }

  @Override
  public String getCicloBio() {
    return "GERARFOLHAS";
  }

  @Override
  public int getEsforcoSazonal() {
    return 1;
  }  
}
