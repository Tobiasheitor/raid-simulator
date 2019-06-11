import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class menu {

    public static void main(String[] args) {
        Path p1 = Paths.get("C:\\Users\\tobias.goettert_est\\Desktop\\java teste\\");
        System.out.println("Path created: " + p1.toString());
        System.out.println("Path parent: " + p1.getParent());

        System.out.println(isValidPath(p1));
    }

    private static boolean isValidPath(Path path) {
        try {
            path.toRealPath();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}