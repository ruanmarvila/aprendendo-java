import java.util.Scanner; // Preciso do Scanner pra consguir ler entrada (input) no terminal

public class Saudacao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Defino scanner com um Scanner

        System.out.print("Digite o seu nome: ");
        String nome = scanner.nextLine(); // Leio o nome

        System.out.print("Digite sua idade: ");
        int idade = scanner.nextInt(); // Leio a idade

        System.out.println("Olá, seu nome é " + nome + " e você tem " + idade + " anos");

        scanner.close(); // O Scanner sempre precisa ser fechado
    }
}

