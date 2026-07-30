package desafios.calculadora;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("==== Calculadora ====");
            System.out.println("1. Soma");
            System.out.println("2. Subtração");
            System.out.println("3. Multiplicação");
            System.out.println("4. Divisão");
            System.out.println("0. Sair");
            System.out.println("=====================");

            System.out.print("Escolha uma opção: ");
            String escolha = scanner.nextLine();

            if ("0".equals(escolha)) {
                break;
            }

            System.out.print("Digite um número: ");
            double numero1 = scanner.nextDouble();

            System.out.print("Digite outro número: ");
            double numero2 = scanner.nextDouble();

            scanner.nextLine();

            double resultado;

            try {
                resultado = switch (escolha) {
                    case "1" -> Operacoes.somar(numero1, numero2);
                    case "2" -> Operacoes.subtrair(numero1, numero2);
                    case "3" -> Operacoes.multiplicar(numero1, numero2);
                    case "4" -> Operacoes.dividir(numero1, numero2);
                    default -> {
                        System.out.println("Opção inválida");
                        yield 0;
                    }
                };
                System.out.println("Resultado: " + resultado);
                
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
        scanner.close();
    }
}