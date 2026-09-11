package arquivos.match;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path path1 = Paths.get("pasta/subpasta/file.bkp");
        Path path2 = Paths.get("pasta/subpasta/file.txt");
        Path path3 = Paths.get("pasta/subpasta/file.java");

        matchers(path1, "glob:*.bkp");
        matchers(path1, "glob:**.bkp");
        matchers(path1, "glob:**/*.{bkp,txt,java}");
        matchers(path2, "glob:**/*.{bkp,txt,java}");
        matchers(path3, "glob:**/*.{bkp,txt,java}");
        matchers(path1, "glob:**/*.???");
        matchers(path2, "glob:**/*.???");
        matchers(path3, "glob:**/file.????");
        matchers(path3, "glob:**/file2.????");
        
    }
    private static void matchers(Path path, String glob) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher(glob);
        System.out.println(glob+": "+matcher.matches(path));
    }
}
