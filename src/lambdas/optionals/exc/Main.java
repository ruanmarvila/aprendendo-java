package lambdas.optionals.exc;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<Manga> manga = MangaRepository.getMangaByTitulo("Naruto");
        manga.ifPresent(m -> m.setTitulo("Naruto Shipudden"));
        System.out.println(manga);

        Manga mangaId = MangaRepository.getMangaById(2)
            .orElseThrow(IllegalArgumentException::new);
        System.out.println(mangaId);

        Manga novoManga = MangaRepository.getMangaByTitulo("Fairy Tail")
            .orElse(new Manga(4, "Fairy Tail", 490));
        System.out.println(novoManga);
    }
}
