package streams.collectors.group_by;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import streams.Categoria;
import streams.LightNovel;
import streams.Promocao;

public class Main {
    private static List<LightNovel> novels = new ArrayList<>(List.of(
        new LightNovel("TSSDK", 55.0, Categoria.FANTASIA),
        new LightNovel("Mushoku Tensei", 47.99, Categoria.FANTASIA),
        new LightNovel("Mushoku Tensei", 47.99, Categoria.FANTASIA),
        new LightNovel("Sword Art Online", 69.99, Categoria.FANTASIA),
        new LightNovel("Bunny Girl", 35.50, Categoria.DRAMA),
        new LightNovel("No Game No life", 34.99, Categoria.FANTASIA),
        new LightNovel("Hyouka", 25.0, Categoria.ROMANCE),
        new LightNovel("Makeine", 52.0, Categoria.ROMANCE)
    ));
    public static void main(String[] args) {
        Map<Categoria, List<LightNovel>> novelMap = novels.stream()
            .collect(Collectors.groupingBy(LightNovel::getCategoria));
        System.out.println(novelMap);

        System.out.println("-----------------------------------------------");

        Map<Promocao, List<LightNovel>> novelsPromotion = novels.stream()
            .collect(Collectors.groupingBy(
                ln -> ln.getPreco() < 50 ? Promocao.EM_PROMOCAO : Promocao.PRECO_NORMAL
            ));
        System.out.println(novelsPromotion);

        System.out.println("-----------------------------------------------");

        Map<Categoria, Map<Promocao, List<LightNovel>>> novelMapPromotion = novels.stream()
            .collect(Collectors.groupingBy(LightNovel::getCategoria, Collectors.groupingBy(
                ln -> ln.getPreco() < 50 ? Promocao.EM_PROMOCAO : Promocao.PRECO_NORMAL
                )));
        System.out.println(novelMapPromotion);

        System.out.println("-----------------------------------------------");

        Map<Categoria, Long> countNovels = novels.stream()
                .collect(Collectors.groupingBy(LightNovel::getCategoria, Collectors.counting()));
        System.out.println(countNovels);

        System.out.println("-----------------------------------------------");

        Map<Categoria, LightNovel> categoriaPreco = novels.stream()
                .collect(Collectors.groupingBy(LightNovel::getCategoria,
                    Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparing(LightNovel::getPreco)), Optional::get)));
        System.out.println(categoriaPreco);

        System.out.println("-----------------------------------------------");

        Map<Categoria, DoubleSummaryStatistics> sumario = novels.stream()
                .collect(Collectors.groupingBy(LightNovel::getCategoria, 
                    Collectors.summarizingDouble(LightNovel::getPreco)));
        System.out.println(sumario);

        System.out.println("-----------------------------------------------");
        
        Map<Categoria, Set<Promocao>> collect = novels.stream()
                    .collect(Collectors.groupingBy(LightNovel::getCategoria, Collectors.mapping(
                        ln -> ln.getPreco() < 50 ? Promocao.EM_PROMOCAO : Promocao.PRECO_NORMAL, Collectors.toSet())));
        System.out.println(collect);

        System.out.println("-----------------------------------------------");
        
        Map<Categoria, LinkedHashSet<Promocao>> collect2 = novels.stream()
                    .collect(Collectors.groupingBy(LightNovel::getCategoria, Collectors.mapping(
                        ln -> ln.getPreco() < 50 ? Promocao.EM_PROMOCAO : Promocao.PRECO_NORMAL, Collectors.toCollection(LinkedHashSet::new))));
        System.out.println(collect2);

        System.out.println("-----------------------------------------------");

        Map<Categoria, Map<Promocao, DoubleSummaryStatistics>> sumarioPromocao = novels.stream()
                    .collect(Collectors.groupingBy(LightNovel::getCategoria, Collectors.groupingBy(
                        ln -> ln.getPreco() < 50 ? Promocao.EM_PROMOCAO : Promocao.PRECO_NORMAL, Collectors.summarizingDouble(LightNovel::getPreco))));
        System.out.println(sumarioPromocao);
    }
}
