package hva.core;

import java.io.*;

public class RegistoVacina implements Serializable{
    private final String _id;
    private final String _idAnimal;
    private final String _idVeterinario;
    private Dano _dano;

    public RegistoVacina(String idVacina, String idAnimal, String idVeterinario){
        _id = idVacina;
        _idAnimal = idAnimal;
        _idVeterinario = idVeterinario;
    }

    public String getId() {
      return _id;
    }

    public String getIdAnimal() {
      return _idAnimal;
    }

    public String getIdVeterinario() {
      return _idVeterinario;
    }

    public Dano getDano() {
      return _dano;
    }


    
    /*
    + DanoVacina(especies: ArrayList<Especie>): int
    + tamanhoNome(nomeOutraEspecie: String): int
    +caracterComum(nomeOutraEspecie: String): int
    */
}