package arquivos.atributos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;

public class DosFile {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("pasta/teste.txt");
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
        // Files.setAttribute(path, "dos:hidden", true);
        // Files.setAttribute(path, "dos:readonly", true);
        // Files.setAttribute(path, "dos:hidden", false);
        // Files.setAttribute(path, "dos:readonly", false);

        DosFileAttributes dos = Files.readAttributes(path, DosFileAttributes.class);
        System.out.println(dos.isHidden());
        System.out.println(dos.isReadOnly());

        DosFileAttributeView dosView = Files.getFileAttributeView(path, DosFileAttributeView.class);
        dosView.setHidden(true);
        dosView.setReadOnly(true);

        System.out.println(dosView.readAttributes().isHidden());
        System.out.println(dosView.readAttributes().isReadOnly());
    }
}