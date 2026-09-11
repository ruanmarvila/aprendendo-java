package arquivos.diretorio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path dir = Paths.get(".");
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir)) {
            for (Path path : dirStream) {
                System.out.println(path.getFileName());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
