import java.util.Scanner;

public class Condicionais {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a sua idade: "); // entrada do usuário
        int idade = scanner.nextInt();

        if (idade >= 18) { // a condição fica entre parênteses ()
            System.out.println("Maior de idade");
        } else if (idade >= 13) { // else if é o equivalente ao elif 
            System.out.println("Adolecente");
        } else { // else é else 
            System.out.println("Criança");
        }

        scanner.close(); // tem que fechar SEMPRE
    }
}
