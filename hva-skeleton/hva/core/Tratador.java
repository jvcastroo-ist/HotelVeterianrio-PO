package hva.core;
import java.util.*;

public class Tratador extends Funcionario{
    private ArrayList<Habitat> _habitats;

    public Tratador(String idTratador, String nome){
      super(idTratador, nome);
      _habitats = new ArrayList<Habitat>();
    }

   // Fazer derivado da classe abstrata
   @Override
  public String visualiza(Hotel h) {
      return super.visualiza(h, "TRATADOR", _habitats);
  }
}