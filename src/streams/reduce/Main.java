package streams.reduce;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numeros = List.of(1, 2, 3, 4, 5, 6);

        // reduce retorna um Optional, logo tu pode usar os métodos de Optional
        // numeros.stream().reduce(Integer::sum).ifPresent(System.out::println);
        System.out.println(numeros.stream().reduce(0, (a, b) -> a + b));
        System.out.println(numeros.stream().reduce(0, Integer::sum));

        System.out.println(numeros.stream().reduce(1, (a, b) -> a * b));

        System.out.println(numeros.stream().reduce((a, b) -> a > b ? a : b).get());
        System.out.println(numeros.stream().reduce(Integer::max).get());
    }
}
