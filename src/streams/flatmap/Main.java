package streams.flatmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<String>> escola = new ArrayList<>();
        List<String> professores = List.of("Maria", "Alexandre", "Leandro", "Ivan");
        List<String> estudantes = List.of("Matheus", "João", "Sofia", "Karla");
        List<String> cordenacao = List.of("Regina", "André", "Marcos", "Rebecca");
        escola.add(professores);
        escola.add(estudantes);
        escola.add(cordenacao);

        // for (List<String> pessoas : escola) {
        //     for (String pessoa : pessoas) {
        //         System.out.println(pessoa);
        //     }
        // }
        escola.stream().flatMap(Collection::stream).forEach(System.out::println);

        System.out.println("------------------");

        List<String> seujorge = List.of("Ela", "é", "amiga", "da", "minha", "mulher");
        List<String> letra = seujorge.stream()
            .map(p -> p.split("")) // String[]
            .flatMap(Arrays::stream) // String
            .toList();
        System.out.println(letra);
    }
}
