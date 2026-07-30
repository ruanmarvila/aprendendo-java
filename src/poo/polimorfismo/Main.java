package poo.polimorfismo;

public class Main {
    public static void main(String[] args) {
        Veiculo carro = new Carro("Kwid", 4);
        Veiculo barco = new Barco("Pérola Negra");

        carro.locomover();
        barco.locomover();
    }
}
