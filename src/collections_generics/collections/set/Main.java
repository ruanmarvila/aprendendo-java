package collections_generics.collections.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import collections_generics.collections.list.comparacao.Anime;

public class Main {
    public static void main(String[] args) {
        Set<Anime> animes = new HashSet<>();

        animes.add(new Anime(1L, "Mushoku Tensei", 8.2, 101));
        animes.add(new Anime(3L, "Sousou no Frieren", 9.1, 45));
        animes.add(new Anime(5L, "Naruto", 8.0, 722));
        animes.add(new Anime(2L, "Pokemon XY", 7.4, 79));
        animes.add(new Anime(2L, "Pokemon XY", 7.4, 79));
        animes.add(new Anime(4L, "Bookworm", 7.8, 86));

        // O HashSet não mantém a ordem, pois ela é baseada no hashCode()
        for (Anime anime : animes) {
            System.out.println(anime);
        }

        System.out.println("-------------");

        Set<Anime> animesLinkados = new LinkedHashSet<>();

        animesLinkados.add(new Anime(3L, "Bleach", 8.1, 336));
        animesLinkados.add(new Anime(1L, "Hitorigoto", 7.8, 45));
        animesLinkados.add(new Anime(2L, "Buchigare", 7.5, 12));

        // O LinkedHashSet mantém a ordem mesmo sendo baseado no hashCode() pois todos os elementos tem uma dupla ligação com o anterior e o posterior
        for (Anime anime : animesLinkados) {
            System.out.println(anime);
        }

        System.out.println("-------------");
        // O TreeSet é um set ordenado, usado para buscas binárias
        // Ele precisa do Comparable ou do Comparator
        // Ele pode ser instanciado por Set<>, SortedSet<> ou NavigableSet<>
        // Você escolhe baseado nos métodos que você quer

        NavigableSet<Anime> animesTree = new TreeSet<>();

        animesTree.add(new Anime(7L, "TenSura", 7.89, 112));
        animesTree.add(new Anime(7L, "Konosuba", 7.5, 195));
        animesTree.add(new Anime(9L, "DBZ", 8.34, 153));

        System.out.println(animesTree.first()); // DBZ
        System.out.println(animesTree.last()); // TenSura

        // o compareTo da classe Anime está comparando por Nome
        for (Anime anime : animesTree) {
            System.out.println(anime);
        }

    }
}
