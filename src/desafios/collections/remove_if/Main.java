package desafios.collections.remove_if;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> animes = new ArrayList<>(List.of("Naruto", "DBZ", "Bleach", "Naruto", "OP"));

        animes.removeIf(anime -> anime.equals("Naruto"));
        Collections.sort(animes);
        System.out.println(animes);
    }
}
