package hva.core;

public enum Estacao {
    PRIMAVERA(0, 1, "GERARFOLHAS", "GERARFOLHAS"),  // Ciclo: Caduca e Perene geram folhas
    VERAO(2, 1, "COMFOLHAS", "COMFOLHAS"),         // Ciclo: Ambas têm folhas no verão
    OUTONO(5, 1, "LARGARFOLHAS", "COMFOLHAS"),     // Ciclo: Caduca larga folhas, Perene continua com folhas
    INVERNO(0, 2, "SEMFOLHAS", "LARGARFOLHAS");    // Ciclo: Caduca sem folhas, Perene larga folhas

    private final int _esforcoCaduca;
    private final int _esforcoPerene;
    private final String _cicloCaduca;
    private final String _cicloPerene;

    Estacao(int esforcoCaduca, int esforcoPerene, String cicloCaduca, String cicloPerene) {
        _esforcoCaduca = esforcoCaduca;
        _esforcoPerene = esforcoPerene;
        _cicloCaduca = cicloCaduca;
        _cicloPerene = cicloPerene;
    }

    public int getEsforco(TipoArvore tipoArvore) {
        switch (tipoArvore) {
            case CADUCA:
                return _esforcoCaduca;
            case PERENE:
                return _esforcoPerene;
            default:
                throw new IllegalArgumentException("Tipo de árvore desconhecido");
        }
    }

    public String getCicloBiologico(TipoArvore tipoArvore) {
        switch (tipoArvore) {
            case CADUCA:
                return _cicloCaduca;
            case PERENE:
                return _cicloPerene;
            default:
                throw new IllegalArgumentException("Tipo de árvore desconhecido");
        }
    }

    // Método que retorna a próxima estação no ciclo anual
    public Estacao proximaEstacao() {
        int nextOrdinal = (this.ordinal() + 1) % Estacao.values().length;  // Garante que depois de INVERNO volte a PRIMAVERA
        return Estacao.values()[nextOrdinal];
    }

    public static Estacao fromNumero(int numero) {
        for (Estacao estacao : Estacao.values()) {
            if (estacao.ordinal() == numero) {
                return estacao;
            }
        }
        throw new IllegalArgumentException("Número inválido para estação: " + numero);
    }
}

    

