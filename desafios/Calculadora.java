package desafios;

import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            double resultado = 0.0;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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

            if ("1".equals(escolha)) {
                resultado = numero1 + numero2;
            }

            else if ("2".equals(escolha)) {
                resultado = numero1 - numero2;
            }

            else if ("3".equals(escolha)) {
                resultado = numero1 * numero2;
            }

            else if ("4".equals(escolha)) {
                if (numero2 != 0) {
                    resultado = numero1 / numero2;
                } else {
                    System.out.println("Não é possível dividr por zero");
                    continue;
                }
            }

            System.out.println("Resultado: " + resultado);


        }
        scanner.close();
    }
    
}
