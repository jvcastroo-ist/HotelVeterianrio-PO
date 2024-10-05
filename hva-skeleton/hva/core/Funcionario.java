package hva.core;

public abstract class Funcionario implements Visualiza{
    private final String _id;
    private final String _nome;

    // Constructor
    public Funcionario(String idFuncionario, String nome) {
      _id = idFuncionario;
      _nome = nome;
    }

    @Override
    public abstract String visualiza();

    public String getIdFuncionario(){
      return _id;
    }

    public String getNome(){
      return _nome;
    }


    /*
    + satisfaçao(): int
    + visualizaFuncionario(): void
    + operaResponsabilidade<R>(<R>, lista: ArrayList<R>,  atribui: boolean): void

     */

}