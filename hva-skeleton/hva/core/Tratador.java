package hva.core;
import java.util.ArrayList;

public class Tratador extends Funcionario{
    private ArrayList<Habitat> _habitats;

    public Tratador(String idTratador, String nome){
      super(idTratador, nome);
      _habitats = new ArrayList<Habitat>();
    }

    // Completar derivado do abstract class erança 

    private ArrayList<String> stringResponsabilidade(){
      ArrayList<String> idHabitats = new ArrayList<String>();

      for(Habitat h : _habitats){
        idHabitats.add(h.getIdHabitat());
      }
      return idHabitats;
    }

    @Override
    public String visualiza(){
      // Se o tratador não tiver habitats, retorna a String sem responsabilidades
      if(_habitats.isEmpty()){
        return String.format("TRATADOR|%s|%s", getIdFuncionario(), getNome());
      } else{

        String responsabilidades = String.join(",", stringResponsabilidade());

        // Retorna a string formatada com ID, nome e responsabilidades (habitats)
        return String.format("TRATADOR|%s|%s|%s", getIdFuncionario(), getNome(), responsabilidades);
      }
    }

}