package hva.core;
import java.util.ArrayList;

public class Vacina implements Responsabilidade{
    private final String _id;
    private final String _nome;
    private int _numAplicacoes;
    private ArrayList<Especie> _especies;
    private ArrayList<RegistoVacina> _registosVacina;

    public Vacina(String idVacina, String nome, ArrayList<Especie> especies){
        _id = idVacina;
        _nome = nome;
        _especies = especies;
        _numAplicacoes = 0;
    }

    public String getId() {
      return _id;
    }

    public String getNome() {
      return _nome;
    }

    // Somador do numero de vezes que vacina foi aplicada
    public void adicionaAplicacao(){
      _numAplicacoes++;
    }

    public ArrayList<Especie> getEspecies() {
      return _especies;
    }

    public ArrayList<RegistoVacina> getRegistosVacina() {
      return _registosVacina;
    }

    public String visualiza(){
      String especies = String.format(",", _especies);
      return String.format("VACINA|%s|%s|%d|%s", _id, _nome, _numAplicacoes, especies);
    }
    
}