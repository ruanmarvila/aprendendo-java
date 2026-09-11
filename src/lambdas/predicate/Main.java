package lambdas.predicate;

import java.util.ArrayList;
import java.util.List;

import lambdas.Carro;

public class Main {
    private static List<Carro> carros = List.of(
        new Carro("verde", 2018), 
        new Carro("preto", 2015), 
        new Carro("amarelo", 2024),
        new Carro("verde", 2008),
        new Carro("vermelho", 1998)
    );
    public static void main(String[] args) {
        List<Carro> carrosVerdes = filtrar(carros, carro -> carro.getCor().equals("verde"));
        List<Carro> carrosPretos = filtrar(carros, carro -> carro.getCor().equals("preto"));
        List<Carro> carrosVelhos = filtrar(carros, carro -> carro.getAno() <= 2015);

        System.out.println(carrosVerdes);
        System.out.println(carrosPretos);
        System.out.println(carrosVelhos);
    }

    private static List<Carro> filtrar(List<Carro> carros, CarroPredicate carroPredicate) {
        List<Carro> carrosFiltrados = new ArrayList<>();
        for (Carro c : carros) {
            if (carroPredicate.teste(c)) {
                carrosFiltrados.add(c);
            }
        }
        return carrosFiltrados;
    }
}
