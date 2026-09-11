package arquivos.atributos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class Ler {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("pasta/file.txt");
        BasicFileAttributes atributos = Files.readAttributes(path, BasicFileAttributes.class);
        
        FileTime creationTime = atributos.creationTime();
        FileTime lastModifiedTime = atributos.lastModifiedTime();
        FileTime lastAccessTime = atributos.lastAccessTime();

        System.out.println("creationTime: "+creationTime);
        System.out.println("lastModifiedTime: "+lastModifiedTime);
        System.out.println("lastAccessTime: "+lastAccessTime);

        System.out.println("-------------------------------------------");

        BasicFileAttributeView attributeView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        FileTime timeMillis = FileTime.fromMillis(System.currentTimeMillis());
        attributeView.setTimes(lastModifiedTime, timeMillis, creationTime);

        creationTime = attributeView.readAttributes().creationTime();
        lastModifiedTime = attributeView.readAttributes().lastModifiedTime();
        lastAccessTime = attributeView.readAttributes().lastAccessTime();

        System.out.println("creationTime: "+creationTime);
        System.out.println("lastModifiedTime: "+lastModifiedTime);
        System.out.println("lastAccessTime: "+lastAccessTime);
    }
}
