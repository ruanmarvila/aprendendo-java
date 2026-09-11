package lambdas.optionals.exc;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class MangaRepository {
    private static List<Manga> mangas = List.of(
        new Manga(1, "Naruto", 550),
        new Manga(2, "Bleach", 900),
        new Manga(3, "Boku no Hero Academia", 600)
    );

    public static Optional<Manga> getManga(Predicate<Manga> predicate) {
        Manga mangaFound = null;

        for (Manga manga : mangas) {
            if (predicate.test(manga)) {
                mangaFound = manga;
            }
        }
        return Optional.ofNullable(mangaFound);
    }

    public static Optional<Manga> getMangaByTitulo(String titulo) {
        return getManga(m -> m.getTitulo().equals(titulo));
    }

    public static Optional<Manga> getMangaById(Integer id) {
        return getManga(m -> m.getId().equals(id));
    }
}
