package desafios.collections.generic;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numeros = List.of(3, 7, 22, 90, 121, 34);
        List<String> frutas = List.of("Amora", "Maça", "Pera", "Morango", "Banana");

        System.out.println(maiorValor(numeros));
        System.out.println(maiorValor(frutas));
    }

    private static <T extends Comparable<T>> T maiorValor(List<T> lista) {
        T maior = lista.get(0);

        for (T valor : lista) {
            if (valor.compareTo(maior) > 0) {
                maior = valor;
            }
        }

        return maior;
    }
}