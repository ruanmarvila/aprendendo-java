package desafios.collections.comparator;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Amanda", "RH", 2500.0));
        funcionarios.add(new Funcionario("João", "Marketing", 2340.50));
        funcionarios.add(new Funcionario("Alicia", "RH", 3400.87));
        funcionarios.add(new Funcionario("Fernando", "Financeiro", 5000.0));
        funcionarios.add(new Funcionario("Bianca", "Financeiro", 4500.0));

        Comparator<Funcionario> funcionariosPorDepartamento = Comparator.comparing(Funcionario::getDepartamento).thenComparing(Funcionario::getSalario, Comparator.reverseOrder());

        funcionarios.sort(funcionariosPorDepartamento);

        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }
}
