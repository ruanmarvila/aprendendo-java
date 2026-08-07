package enums.enum_basico;

public class Main {
    public static void main(String[] args) {
        Naipe naipe = Naipe.PAUS;

        System.out.println(naipe.name());
        System.out.println(naipe.ordinal());

        // o '==' serve nessa comparação por se tratar da mesma referência
        if (naipe == Naipe.COPAS || naipe == Naipe.OUROS) {
            System.out.println("Naipe vermelho");
        } else {
            System.out.println("Naipe preto");
        }
    }
}
