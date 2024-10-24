package hva.core;

public class PereneVeraoState implements EstacaoState {
    @Override
    public EstacaoState mudarEstacao() {
      return new PereneOutonoState();
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