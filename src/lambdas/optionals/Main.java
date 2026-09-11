package lambdas.optionals;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<String> nomes = List.of("Ana", "Bob", "Carl");
        // Usado quando o retorno pode ser nulo
        Optional<String> nome = getName("Ana", nomes);
        // Troca o valor se for nulo
        String nomeOutro = nome.orElse("Vazio");
        // Forma de  usar um Consumer<T>
        nome.ifPresent(s -> System.out.println(s.toUpperCase()));
        System.out.println(nomeOutro);
    }

    private static Optional<String> getName(String name, List<String> lista) {
        int i = lista.indexOf(name);
        if (i >= 0) {
            return Optional.of(lista.get(i));
        }
        return Optional.empty();
    }

}
