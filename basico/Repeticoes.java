public class Repeticoes {
    public static void main(String[] args) {
        // for loop -> for (declarar variável inicial, condição de parada, "passos")
        for (int n = 0; n < 5; n++) {
            System.out.print(n + ", "); // 0, 1, 2, 3, 4
        }

        for (int n = 10; n > 0; n--) {
            System.out.print(n + ", "); // 10, 9, 8, 7, 6, 5, 4, 3, 2, 1
        }

        for (int i = 1, j = 10; i < j; i++, j--) {
             System.out.print(i + ", "); // 1, 2, 3, 4, 5
        }

        // while loop -> Repete enquanto a condição for verdadeira
        // while (true) -> Loop que precisa de uma condição de parada dentro dela com break

        int numero = 0;
        while (numero <= 100) {
            if (numero % 2 == 0) {
                System.out.println(numero);
            }

            numero++;
        }

        // do while -> Executa o bloco uma vez e depois verifica a condição

        String res;

        do {
            System.out.println("Quer continuar? (S/N): ");
            res = "N"; // "Resposta do usuário"

        } while ("S".equals(res));
    }
}
