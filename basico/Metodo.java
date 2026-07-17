public class Metodo {
    // métodos -> static (void/type/String) nome(parâmetro)
    // parâmetros -> tipo nome
    // return -> retorna um valor do mesmo tipo do método (não usado com void)

    static void boasVindas(String nome) {
        System.out.println("Olá " + nome + ", seja muito bem-vindo(a)");
    }

    static int somar(int a, int b) {
        int res = a + b;
        return res;
    }

    static boolean isAdult(int idade) {
        if (idade >= 18) {
            return true;
        }
        return false;
    }

    static String upper(String nome) {
        return nome.toUpperCase();
    }

    public static void main(String[] args) {
        boasVindas("Ana"); // "Olá Ana, seja bem-vindo(a)"

        int result = somar(1, 6);
        System.out.println("Resultado: " + result); // "Resultado: 7"

        System.out.println(isAdult(25)); // true

        System.out.println(upper("ana")); // ANA
    }
}
