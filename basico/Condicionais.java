import java.util.Scanner;

public class Condicionais {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a sua idade: "); // entrada do usuário
        int idade = scanner.nextInt();

        System.out.println("Digite o número do mês do seu aniversário: ");
        int mes = scanner.nextInt();
        String estacao = "";

        if (idade >= 18) { // a condição fica entre parênteses ()
            System.out.println("Maior de idade");
        } else if (idade >= 13) { // else if é o equivalente ao elif 
            System.out.println("Adolecente");
        } else { // else é else 
            System.out.println("Criança");
        }

        switch (mes) {
            case 12:
            case 1:
            case 2:
                estacao = "Verão";
                break;
            case 3:
            case 4:
            case 5:
                estacao = "Outono";
                break;
            case 6:
            case 7:
            case 8:
                estacao = "Inverno";
                break;
            case 9:
            case 10:
            case 11:
                estacao = "Primavera";
                break;
            default:
                estacao = "Mês inválido";
        }

        System.out.println("Você nasceu na " + estacao);

        scanner.close(); // tem que fechar SEMPRE

        // Operador Lógico AND (&&)
        // Resulta em true se todas as condições forem true
        boolean ensolarado = true;
        boolean marLimpo = true;

        if (ensolarado && marLimpo) {
            System.out.println("Tá na hora da praia!");
        }

        // Operador Lógico OR (||)
        // Resulta em true se uma das condições for true
        boolean banhoTomado = true;
        boolean vontade = false;

        if (vontade || banhoTomado) {
            System.out.println("Pode sair");
        }

        // Operador Lógico NOT (!)
        // Resulta em true se for false e false se for true
        boolean ativo = false;

        System.out.println(!ativo); // true
    }
}
