package hva.core;

import java.util.*;

public class Veterinario extends Funcionario{
  private List<Especie> _especies;
  private List<RegistoVacina> _registoVacinas;

  public Veterinario(String idVeterinario, String nome){
    super(idVeterinario, nome);
    _especies = new ArrayList<Especie>();
    _registoVacinas = new ArrayList<RegistoVacina>();
  }

  @Override
  public String visualiza(Hotel h) {
      return super.visualiza(h, "VETERINÁRIO", _especies);
  }
}