import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;

public class menu {

    public static void main(String[] args) {
        //getAllDirectories().stream().forEach(x->System.out.println(x.getFileName()));;
        Path arquivo = Paths.get("C:\\Users\\tobias.goettert_est\\Desktop\\java teste\\copiar.txt");
        Path diretorioDestino = Paths.get("C:\\Users\\tobias.goettert_est\\Desktop\\java teste\\raid-simulator");
        copyFileTo(arquivo, diretorioDestino);
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

    private static void copyFileTo(Path arquivo, Path diretorioDestino) {
        try {
            Files.copy(arquivo, Paths.get(diretorioDestino.toString() + "\\" + arquivo.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Success to copy");
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }
}
