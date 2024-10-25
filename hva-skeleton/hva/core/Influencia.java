package hva.core;

public enum Influencia {
  NEG{
    @Override
    public int getInfluencia() {
      return -20;
    }
  },
  NEU{
    @Override
    public int getInfluencia() {
      return 0;
    }
  },
  POS{
    @Override
    public int getInfluencia() {
      return 20;
    }
  };
  
  /**
   * Retrieves the influence value.
   *
   * @return the influence value as an integer.
   */
  public abstract int getInfluencia(); 
}
