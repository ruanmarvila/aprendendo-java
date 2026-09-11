package arquivos.escrita;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class BuffedEscrita {
    public static void main(String[] args) {
        Path path = Path.of("pasta/file.txt");

        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bw.write("Olá, Mundo!");
            bw.newLine();
            bw.write("O Java roda na JVM");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
