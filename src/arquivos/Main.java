package arquivos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void main(String[] args) throws IOException {
        // criando a pasta
        Path pastaPath = Paths.get("pasta");
        if (Files.notExists(pastaPath)) {
            Files.createDirectory(pastaPath);
        }

        // criando o arquivo
        Path filePath = Paths.get(pastaPath.toString(), "file.txt");
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }

        Path path = Paths.get("pasta/file.txt");
        System.out.println(path.getFileName());

        // copiando
        Path target = Paths.get(filePath.getParent().toString(), "other_file.txt");
        Files.copy(filePath, target, StandardCopyOption.REPLACE_EXISTING);
    }
}