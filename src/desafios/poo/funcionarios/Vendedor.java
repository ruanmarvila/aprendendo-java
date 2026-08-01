package desafios.poo.funcionarios;

public class Vendedor extends Funcionario {
    private int quantidadeDeVendas;

    public Vendedor(String nome, double salario, int quantidadeDeVendas) {
        super(nome, salario);
        this.quantidadeDeVendas = quantidadeDeVendas;
    }

    @Override
    public double calcularBonus() {
        return salarioBase * 0.1 + 50 * quantidadeDeVendas;
    }
}
