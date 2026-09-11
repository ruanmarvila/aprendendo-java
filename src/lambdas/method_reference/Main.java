package lambdas.method_reference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        List<Anime> animes = new ArrayList<>(List.of(
            new Anime("Rakudai Kishi", 12), 
            new Anime("Log Horizon", 78),
            new Anime("Charlotte", 12),
            new Anime("Akatsuki no Yona", 25)
        ));
        // static methods
        // Collections.sort(animes, (a1, a2) -> AnimeComparators.compareTitulo(a1, a2));
        Collections.sort(animes, AnimeComparators::compareTitulo);
        System.out.println(animes);
        
        // Collections.sort(animes, (a1, a2) -> AnimeComparators.compareEpisodios(a1, a2));
        Collections.sort(animes, AnimeComparators::compareEpisodios);
        System.out.println(animes);

        System.out.println("--------------------");

        List<Anime> animes2 = new ArrayList<>(List.of(
            new Anime("Naruto", 322), 
            new Anime("One Piece", 1000),
            new Anime("TenSura", 89),
            new Anime("Nisekoi", 25)
        ));
        // instância
        AnimeComparators comparators = new AnimeComparators();
        // animes2.sort((a1, a2) -> comparators.comparePorEpisodios(a1, a2));
        animes2.sort(comparators::comparePorEpisodios);
        System.out.println(animes2);

        System.out.println("--------------------");

        List<String> animes3 = new ArrayList<>(List.of(
            "Rimuru", "Benimaru", "Veldora", "Shion", "Diablo"
        ));
        // instanciando pelo tipo
        animes3.sort(String::compareTo);
        System.out.println("Personagens de TSSDK: "+animes3);

        // s -> Integer.parseInt(s);
        Function<String, Integer> strParaInt = Integer::parseInt;
        System.out.println(strParaInt.apply("23"));

        BiPredicate<List<String>, String> checarNome = List::contains;
        System.out.println(checarNome.test(animes3, "Shion"));

        System.out.println("--------------------");

        Supplier<AnimeComparators> novoAnimeComparators = AnimeComparators::new;
        AnimeComparators aComparators = novoAnimeComparators.get();
        List<Anime> animes4 = new ArrayList<>(List.of(
            new Anime("Mushoku Tensei", 85), 
            new Anime("Toumei", 12),
            new Anime("Sousei no Onmyouji", 55),
            new Anime("Hyouka", 25)
        ));
        // referência para construtor
        animes4.sort(aComparators::comparePorEpisodios);
        System.out.println(animes4);

        // BiFunction<String, Integer, Anime> animeFunction = (s, i) -> new Anime(s, i);
        BiFunction<String, Integer, Anime> animeFunction = Anime::new;
        System.out.println(animeFunction.apply("Fairy Tail", 350));
    }
}
