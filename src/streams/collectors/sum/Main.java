package streams.collectors.sum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println(novels.stream().collect(Collectors.counting()));

        novels.stream()
            .collect(Collectors.maxBy(Comparator.comparing(LightNovel::getPreco)))
            .ifPresent(System.out::println);

        System.out.println(novels.stream().collect(Collectors.summingDouble(LightNovel::getPreco)));
        
        System.out.println(novels.stream().collect(Collectors.averagingDouble(LightNovel::getPreco)));

        DoubleSummaryStatistics sumario = novels.stream()
            .collect(Collectors.summarizingDouble(LightNovel::getPreco));
        System.out.println(sumario);

        String titulos = novels.stream()
            .map(LightNovel::getTitulo)
            .collect(Collectors.joining(", "));
        System.out.println(titulos);
    }
}
