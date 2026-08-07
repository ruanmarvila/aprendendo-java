package collections_generics.collections.list.comparacao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Anime> animes = new ArrayList<>();

        animes.add(new Anime(1L, "Mushoku Tensei", 8.2));
        animes.add(new Anime(3L, "Sousou no Frieren", 9.1));
        animes.add(new Anime(5L, "Naruto", 8.0));
        animes.add(new Anime(2L, "Pokemon", 7.4));
        animes.add(new Anime(4L, "Bookworm", 7.8));

        for (Anime anime : animes) {
            System.out.println(anime);
        }

        animes.sort(null);
        System.out.println("-------------");
        for (Anime anime : animes) {
            System.out.println(anime);
        }

        animes.sort(Comparator.comparingDouble(Anime::getNota).reversed());
        System.out.println("-------------");
        for (Anime anime : animes) {
            System.out.println(anime);
        }
    }
}
