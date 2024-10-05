package hva.core;

public abstract class Funcionario {
    private final String _id;
    private final String _nome;

    // Constructor
    public Funcionario(String idFuncionario, String nome) {
        _id = idFuncionario;
        _nome = nome;
    }

    public abstract String visualizaFuncionario();

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