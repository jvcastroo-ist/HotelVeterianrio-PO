package hva.core;
import java.util.ArrayList;

public class Veterinario extends Funcionario{
    private ArrayList<Especie> _especies;
    private Arraylist<RegistoVacina> _registoVacinas;

    public Veterinario(String idVeterinario, String nome){
        super(idVeterinario, nome);
        _especies = new ArrayList<Especie>();
        _registoVacinas = new ArrayList<RegistoVacina>();
    }

    // Fazer derivado da classe abstrata
    @Override
    public String visualizaFuncionario();
}