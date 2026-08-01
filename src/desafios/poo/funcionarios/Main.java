package desafios.poo.funcionarios;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Funcionario gerente = new Gerente("Astolfo", 5000);
        Funcionario vendedor = new Vendedor("Paulo", 1600, 10);
        
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(gerente);
        funcionarios.add(vendedor);

        for (Funcionario f : funcionarios) {
            System.out.printf("%s recebeu um bônus de R$%.2f%n", f.getNome(), f.calcularBonus());
        }

    }
}
