package desafios.poo.funcionarios;

public class Gerente extends Funcionario {

    public Gerente(String nome, double salario) {
        super(nome, salario);
    }


    @Override
    public double calcularBonus() {
        return salarioBase * 0.2;
    }

}
