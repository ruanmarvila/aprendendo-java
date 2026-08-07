package collections_generics.collections.list.iteracao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import collections_generics.collections.list.comparacao.Anime;

public class Main {
    public static void main(String[] args) {
        List<Anime> animes = new ArrayList<>();

        animes.add(new Anime(1L, "Mushoku Tensei", 8.2, 101));
        animes.add(new Anime(3L, "Sousou no Frieren", 9.1, 45));
        animes.add(new Anime(5L, "Naruto", 8.0, 722));
        animes.add(new Anime(2L, "Pokemon XY", 7.4, 79));
        animes.add(new Anime(4L, "Bookworm", 7.8, 86));

        Iterator<Anime> animeIterator = animes.iterator();

        while(animeIterator.hasNext()) {
            if (animeIterator.next().getQuantidadeEp() == 79) {
                animeIterator.remove();
            }
        }
        System.out.println(animes);

        animes.removeIf(anime -> anime.getQuantidadeEp() == 722);
        System.out.println(animes);
    }
}
