package arquivos.escrita;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("pasta/texto.txt");
        String linguagens = "Java\nC#\nPython\nGo\nJS\nTS\nRuby\nC\nC++\nRust\nPHP\nSwift\nKotlin";
        Files.writeString(path, linguagens);
    }
}
