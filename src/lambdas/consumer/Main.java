package lambdas.consumer;

import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        List<String> nomes = List.of("Rafaela", "Kauã", "Fernanda", "Yuri", "Sofia");
        List<Integer> inteiros = List.of(1, 2, 3, 4, 5);
        forEach(nomes, nome -> System.out.println(nome));
        forEach(inteiros, i -> System.out.println(i));
    }

    private static <T> void forEach(List<T> lista, Consumer<T> consumer) {
        for (T t : lista) {
            consumer.accept(t);
        }
    }
}
