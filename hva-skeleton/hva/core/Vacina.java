package hva.core;
import java.util.ArrayList;

public class Vacina implements Visualiza{
    private final String _id;
    private final String _nome;
    private ArrayList<Especie> _especies;
    private ArrayList<RegistoVacina> _registosVacina;

    public Vacina(String idVacina, String nome, ArrayList<Especie> especies){
        _id = idVacina;
        _nome = nome;
        _especies = especies;
    }

    public String getId() {
      return _id;
    }

    public String getNome() {
      return _nome;
    }

    public ArrayList<Especie> getEspecies() {
      return _especies;
    }

    public ArrayList<RegistoVacina> getRegistosVacina() {
      return _registosVacina;
    }

    @Override
    public String visualiza(Hotel h){
      return String.format("VACINA|%s|%s|%d%s", getId(), getNome(), _registosVacina.size(), Responsabilidade.idResponsabilidade(_especies));
    }
    
}