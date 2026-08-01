package excecoes.zero;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==== DIVISÃO ====");

        try {
            System.out.print("Digite o dividendo: ");
            int dividendo = sc.nextInt();
            System.out.print("Digite o divisor: ");
            int divisor = sc.nextInt();
            System.out.println("Resultado: " + dividendo/divisor);
        } catch (ArithmeticException e) {
            System.out.println("Não é possível dividr por zero");
        } finally {
            sc.close();
        }
    }
}
