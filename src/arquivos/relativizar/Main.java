package arquivos.relativizar;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path dir = Paths.get("/home/ruan");
        Path classe = Paths.get("/home/ruan/dev/Main.java");
        System.out.println(dir.relativize(classe));

        Path absoluto1 = Paths.get("/home/ruan");
        Path absoluto2 = Paths.get("/user/local");
        Path absoluto3 = Paths.get("/home/ruan/dev/Main.java");
        Path relativo1 = Paths.get("temp");
        Path relativo2 = Paths.get("temp/temp.200895");

        System.out.println("1. "+absoluto1.relativize(absoluto3));
        System.out.println("2. "+absoluto3.relativize(absoluto1));
        System.out.println("3. "+absoluto1.relativize(absoluto2));
        System.out.println("4. "+relativo1.relativize(relativo2));
        System.out.println("5. "+absoluto1.relativize(relativo1));
    }
}
