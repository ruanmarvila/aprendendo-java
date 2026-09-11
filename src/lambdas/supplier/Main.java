package lambdas.supplier;

import java.util.function.Supplier;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Supplier<Double> numero = () -> Math.random();
        System.out.println(numero.get());

        Supplier<LocalDateTime> data = () -> LocalDateTime.now();
        System.out.println(data.get());
    }
}
