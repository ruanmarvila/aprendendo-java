package arquivos.resolvendo;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path dir = Paths.get("home/ruan");
        Path arq = Paths.get("dev/texto.txt");
        Path resolve = dir.resolve(arq);
        System.out.println(resolve);

        Path absoluto = Paths.get("/home/ruan");
        Path relativo = Paths.get("dev");
        Path file = Paths.get("file.txt");

        System.out.println("1. "+absoluto.resolve(relativo));
        System.out.println("2. "+absoluto.resolve(file));
        System.out.println("3. "+relativo.resolve(absoluto));
        System.out.println("4. "+relativo.resolve(file));
        System.out.println("5. "+file.resolve(absoluto));
        System.out.println("6. "+file.resolve(relativo));
    }
}
