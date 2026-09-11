package streams.reduce;

import java.util.ArrayList;
import java.util.List;

import streams.LightNovel;

public class Reduce {
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
        novels.stream()
            .filter(ln -> ln.getPreco() > 50)
            .map(LightNovel::getPreco)
            .reduce(Double::sum)
            .ifPresent(System.out::println);

        double sum = novels.stream()
            .mapToDouble(LightNovel::getPreco)
            .filter(preco -> preco > 50)
            .sum();
        System.out.println(sum);
    }
}
