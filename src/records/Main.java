package records;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Anime> romances = new ArrayList<>(List.of(
            new Anime("Romantic Killer", 12),
            new Anime("Horimiya", 13),
            new Anime("Go-toubun no Hanayome", 74),
            new Anime("Kaoru Hana", 13),
            new Anime("Sono Bisque", 50)
        ));
    public static void main(String[] args) {
        romances.forEach(System.out::println);

        System.out.println("---------------------------------");

        Anime kaoruHana = new Anime("Kaoru Hana", 13);
        System.out.println(kaoruHana.equals(romances.get(3))); // true
        System.out.println(kaoruHana == romances.get(3)); // false

        System.out.println("---------------------------------");

        // Um Record é imutável, então para atualizar um valor é necessário criar uma nova instância
        Anime sonoBisque = romances.get(4);
        Anime sonoBisqueAtt = new Anime(sonoBisque.titulo(), 24);

        romances.remove(sonoBisque);
        romances.add(sonoBisqueAtt);
        
        romances.forEach(System.out::println);
    }
}
