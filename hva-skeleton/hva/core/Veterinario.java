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


  private ArrayList<String> stringResponsabilidade(){
    ArrayList<String> idHabitats = new ArrayList<String>();

    for(Especie h : _especies){
      idHabitats.add(h.getIdEspecie());
    }
    return idHabitats;
  }

  // Fazer derivado da classe abstrata
  @Override
  public String visualiza(){
    if(_especies.isEmpty()){
      return String.format("VETERINÁRIO|%s|%s", getIdFuncionario(), getNome());
    } else{
      
      String responsabilidades = String.join(",", stringResponsabilidade());

      return String.format("VETERINÁRIO|%s|%s|%s", getIdFuncionario(), getNome(), responsabilidades);
    }
  }
}