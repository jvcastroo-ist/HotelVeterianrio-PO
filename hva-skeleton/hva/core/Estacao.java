package hva.core;

public enum Estacao {
  PRIMAVERA {
    @Override
    public EstacaoState criarEstacaoStateCaduca() {
        return new CaducaPrimaveraState();
    }

    @Override
    public EstacaoState criarEstacaoStatePerene() {
        return new PerenePrimaveraState();
    }
  },
  VERAO {
    @Override
    public EstacaoState criarEstacaoStateCaduca() {
        return new CaducaVeraoState();
    }

    @Override
    public EstacaoState criarEstacaoStatePerene() {
        return new PereneVeraoState();
    }
  },
  OUTONO {
    @Override
    public EstacaoState criarEstacaoStateCaduca() {
        return new CaducaOutonoState();
    }

    @Override
    public EstacaoState criarEstacaoStatePerene() {
        return new PereneOutonoState();
    }
  },
  INVERNO {
    @Override
    public EstacaoState criarEstacaoStateCaduca() {
        return new CaducaInvernoState();
    }

    @Override
    public EstacaoState criarEstacaoStatePerene() {
        return new PereneInvernoState();
    }
  };

  /**
   * Creates a new state for the station based on the deciduous tree.
   *
   * @return a new instance of EstacaoState representing the state of the station
   */
  public abstract EstacaoState criarEstacaoStateCaduca();

  /**
   * Creates a new state for the station based on the perennial tree.
   *
   * @return a new instance of EstacaoState representing the state of the station
   */
  public abstract EstacaoState criarEstacaoStatePerene();

  // Método que retorna a próxima estação no ciclo anual
  /**
   * Returns the next season in the sequence. The sequence follows the order of the enum constants.
   * If the current season is the last one in the sequence, it wraps around to the first season.
   *
   * @return the next season in the sequence
   */
  public Estacao proximaEstacao() {
      int nextOrdinal = (this.ordinal() + 1) % Estacao.values().length;  // Garante que depois de INVERNO volte a PRIMAVERA
      return Estacao.values()[nextOrdinal];
  }

}

    

