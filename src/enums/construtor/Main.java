package enums.construtor;

public class Main {
    public static void main(String[] args) {
        double dolares = 100;
        double dolarResultado = Moeda.DOLAR.converterParaReais(dolares);        

        double euros = 50;
        double euroResultado = Moeda.EURO.converterParaReais(euros);


        System.out.printf("%.2f dólares equivalem a R$%.2f%n", dolares, dolarResultado);
        System.out.printf("%.2f euros equivalem a R$%.2f%n", euros, euroResultado);
    }
}
