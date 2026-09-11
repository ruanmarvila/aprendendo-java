package streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static List<LightNovel> novels = new ArrayList<>(List.of(
        new LightNovel("TSSDK", 55.0),
        new LightNovel("Mushoku Tensei", 47.99),
        new LightNovel("Mushoku Tensei", 47.99),
        new LightNovel("Sword Art Online", 69.99),
        new LightNovel("Bunny Girl", 35.50),
        new LightNovel("No Game No life", 34.99),
        new LightNovel("Hyouka", 25.0),
        new LightNovel("Makeine", 52.0)
    ));

    public static void main(String[] args) {
        List<String> titulos = novels.stream()
            .filter(ln -> ln.getPreco() <= 50.0)
            .sorted(Comparator.comparing(LightNovel::getTitulo))
            .limit(3)
            .map(LightNovel::getTitulo)
            .toList();
        System.out.println(titulos);

        novels.forEach(System.out::println);
        long count = novels.stream()
            .distinct() // Depende do equals/hashCode
            .filter(ln -> ln.getPreco() <= 50)
            .count();
        System.out.println(count);
    }
}
