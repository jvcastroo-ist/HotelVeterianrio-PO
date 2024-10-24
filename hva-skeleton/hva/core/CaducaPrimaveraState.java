package hva.core;

public class CaducaPrimaveraState implements EstacaoState {
  @Override
  public EstacaoState mudarEstacao() {
    return new CaducaVeraoState();
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
