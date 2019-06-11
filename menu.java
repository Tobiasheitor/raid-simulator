import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.*;

public class menu {

    public static void main(String[] args) {
        getAllDirectories().stream().forEach(x->System.out.println(x.getFileName()));;
    }

    private static boolean isValidPath(Path path) {
        if (!Files.exists(path)) {
            return false;
        }
        try {
            path.toRealPath();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private static ArrayList<Path> getAllDirectories() {
        ArrayList<Path> caminhos = new ArrayList<>();
        String pwd = System.getProperty("user.dir");
        Path dir = Paths.get(pwd);
        try {
            Files.walk(dir, 1).forEach(path -> caminhos.add(path));
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
        return caminhos;
    }
}