package hva.core;
import java.util.ArrayList;

public class Tratador extends Funcionario{
    private ArrayList<Habitat> _habitats;

    public Tratador(String idTratador, String nome){
        super(idTratador, nome);
        _habitats = new ArrayList<Habitat>();
    }

    // Completar derivado do abstract class erança 

    @Override
    public String visualizaFuncionario();
}