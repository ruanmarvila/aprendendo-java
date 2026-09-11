package arquivos.normalizacao;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String diretorio = "home/ruan/arquivo.txt";
        String arquivo = "../../arquivo.txt";
        Path path1 = Paths.get(diretorio, arquivo);
        System.out.println(path1);
        System.out.println(path1.normalize());

        Path path2 = Paths.get("/home/./ruan/./dev/");
        System.out.println(path2);
        System.out.println(path2.normalize());
    }
}
