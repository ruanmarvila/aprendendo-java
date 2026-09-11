package streams.find_and_match;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import streams.LightNovel;

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
        // algum elemento tem um preço maior que 60?
        System.out.println(novels.stream().anyMatch(ln -> ln.getPreco() > 60));
        // todos os elementos têm preços maiores que 24.99?
        System.out.println(novels.stream().allMatch(ln -> ln.getPreco() > 24.99));
        // nenhum elemento tem preço menor que 0?
        System.out.println(novels.stream().noneMatch(ln -> ln.getPreco() < 0));

        novels.stream()
            .filter(ln -> ln.getPreco() > 50)
            .findAny()
            .ifPresent(System.out::println);

        novels.stream()
            .filter(ln -> ln.getPreco() > 50)
            .max(Comparator.comparing(LightNovel::getPreco).reversed())
            .ifPresent(System.out::println);
    }
}
