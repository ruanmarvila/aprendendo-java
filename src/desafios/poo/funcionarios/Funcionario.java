package desafios.poo.funcionarios;

public abstract class Funcionario {
    protected String nome;
    protected double salarioBase;

    public Funcionario(String nome, double salario) {
        this.nome = nome;
        salarioBase = salario;
    }

    public String getNome() {
        return nome;
    }

    public abstract double calcularBonus();
}
