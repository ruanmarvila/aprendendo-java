package arquivos.leitura;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("pasta/texto.txt");

        try {
            String conteudo = Files.readString(path);
            System.out.println(conteudo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
