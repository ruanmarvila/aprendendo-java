package streams.geracao;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        IntStream.range(1, 50)
            .filter(n -> n % 2 == 0)
            .forEach(n -> System.out.print(n+" "));
        System.out.println();
        IntStream.rangeClosed(1, 50)
            .filter(n -> n % 2 == 0)
            .forEach(n -> System.out.print(n+" "));
        System.out.println();

        Stream.of("Seu", "Sorriso é tão", "resplandecente")
            .map(String::toUpperCase)
            .forEach(s -> System.out.print(s+" "));
        System.out.println();

        int[] nums = {1, 2, 3, 4, 5};
        Arrays.stream(nums).average().ifPresent(System.out::println);
    }
}
