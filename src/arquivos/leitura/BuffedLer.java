package arquivos.leitura;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BuffedLer {
    public static void main(String[] args) {
        Path path = Path.of("pasta/file.txt");

        try (BufferedReader br = Files.newBufferedReader(path)) {
            List<String> linhas = br.readAllLines();
            linhas.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
