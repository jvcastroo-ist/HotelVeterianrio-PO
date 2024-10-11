package hva.core;

import java.io.*;

public class RegistoVacina implements Serializable{
    private final String _id;
    private final String _idAnimal;
    private final String _idVeterinario;
    private Dano _dano;

    /**
     * Constructs a new RegistoVacina object with the specified identifiers.
     *
     * @param idVacina the identifier of the vaccine
     * @param idAnimal the identifier of the animal
     * @param idVeterinario the identifier of the veterinarian
     */
    public RegistoVacina(String idVacina, String idAnimal, String idVeterinario){
        _id = idVacina;
        _idAnimal = idAnimal;
        _idVeterinario = idVeterinario;
    }

    /**
     * Retrieves the unique identifier of the vaccine record.
     *
     * @return the unique identifier as a String.
     */
    public String getId() {
      return _id;
    }

    /**
     * Retrieves the ID of the animal associated with this vaccine record.
     *
     * @return the ID of the animal as a String.
     */
    public String getIdAnimal() {
      return _idAnimal;
    }

    /**
     * Retrieves the ID of the veterinarian.
     *
     * @return the ID of the veterinarian as a String.
     */
    public String getIdVeterinario() {
      return _idVeterinario;
    }

    /**
     * Retrieves the damage associated with this vaccine record.
     *
     * @return the damage (Dano) associated with this vaccine record.
     */
    public Dano getDano() {
      return _dano;
    }
}