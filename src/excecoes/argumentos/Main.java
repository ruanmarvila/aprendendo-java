package excecoes.argumentos;

public class Main {
    public static void main(String[] args) {
        ContaBancaria conta1 = new ContaBancaria(1000);
        try {
            conta1.sacar(1500);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
