package lambdas.method_reference;

public class AnimeComparators {
    public static int compareTitulo(Anime anime1, Anime anime2) {
        return anime1.getTitulo().compareTo(anime2.getTitulo());
    }

    public static int compareEpisodios(Anime anime1, Anime anime2) {
        return Integer.compare(anime1.getEpisodios(), anime2.getEpisodios());
    }
    
    public int comparePorEpisodios(Anime anime1, Anime anime2) {
        return Integer.compare(anime1.getEpisodios(), anime2.getEpisodios());
    }
}
