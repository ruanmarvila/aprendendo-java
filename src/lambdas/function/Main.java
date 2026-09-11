package lambdas.function;

import java.util.function.Function;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> protagonistas = List.of("Naruto", "Goku", "Ichigo", "Natsu", "Seiya", "Gon");
        List<Integer> tamanhos = map(protagonistas, p -> p.length());
        List<String> up = map(protagonistas, p -> p.toUpperCase());
        System.out.println(tamanhos);
        System.out.println(up);
    }

    private static <T, R> List<R> map(List<T> lista, Function<T, R> function) {
        List<R> resultado = new ArrayList<>();

        for (T t : lista) {
            R r = function.apply(t);
            resultado.add(r);
        }

        return resultado;
    }
}
