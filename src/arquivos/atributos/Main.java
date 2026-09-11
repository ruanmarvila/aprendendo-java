package arquivos.atributos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Main {
    public static void main(String[] args) throws IOException {
        LocalDateTime date = LocalDateTime.now().minusDays(10);
        Path path = Paths.get("pasta/file.txt");
        Files.createFile(path);
        FileTime time = FileTime.from(date.toInstant(ZoneOffset.UTC));
        Files.setLastModifiedTime(path, time);

        System.out.println(Files.isWritable(path));
        System.out.println(Files.isReadable(path));
        System.out.println(Files.isExecutable(path));
    }
}
