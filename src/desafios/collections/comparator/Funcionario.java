package desafios.collections.comparator;

public class Funcionario {
    private String nome;
    private String departamento;
    private Double salario;

    public Funcionario(String nome, String departamento, Double salario) {
        this.nome = nome;
        this.departamento = departamento;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "nome: "+nome+" | departamento: "+departamento+" | salário: "+salario;
    }
    
    public String getNome() {
        return nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public Double getSalario() {
        return salario;
    }
}
