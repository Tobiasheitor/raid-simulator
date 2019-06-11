import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;

public class menu {

    public static void main(String[] args) throws Exception {
        //getAllDirectories().stream().forEach(x->System.out.println(x.getFileName()));;
        Path arquivo = Paths.get("C:\\Users\\tobias.goettert_est\\Desktop\\java teste\\copiar.txt");
        //Path diretorioDestino = Paths.get("C:\\Users\\tobias.goettert_est\\Desktop\\java teste\\raid-simulator\\teste");
        
        Path diretorioDestino = Paths.get(args[0]);

        if(Files.isDirectory(diretorioDestino)) {
            createDirectory(diretorioDestino);
        }
        else {
            diretorioDestino = diretorioDestino.getParent();
            createDirectory(diretorioDestino);
        }
        
        if(!Files.exists(arquivo))
            throw new Exception("Arquivo não existe");
        else
            copyFileTo(arquivo, diretorioDestino);
        
        ArrayList<Path> paths = getAllClearDirectories(getAllDirectories());

        concatDestinationDirectory(diretorioDestino, paths);
    }

    private static void createDirectory(Path path) {
        try {
            Path dir = Files.createDirectory(path);
            System.out.println("Diretorio \"" + dir + "\" criado");
        } catch (IOException e1) {
            System.out.println("Falha " + e1 + " ao criar o diretorio");
        }
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

    private void getAllDirectoriesRecursive( String path ) {
        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                getAllDirectoriesRecursive( f.getAbsolutePath() );
                System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else {
                System.out.println( "File:" + f.getAbsoluteFile() );
            }
        }
    }

    private static ArrayList<Path> getAllClearDirectories(ArrayList<Path> paths) {
        Path diretorioAtual = Paths.get(System.getProperty("user.dir"));

        ArrayList<Path> resultPaths = new ArrayList<>();
        paths.stream().map((Path path)-> {
                return Paths.get(path.toString().replace(diretorioAtual.toString(), ""));
            }).forEach(resultPaths::add);
        
        return resultPaths;
    }

    private static ArrayList<Path> concatDestinationDirectory(Path diretorioDestino, ArrayList<Path> paths) {
        ArrayList<Path> resultPaths = new ArrayList<>();
        paths.stream().map((Path path)-> {
            return Paths.get(diretorioDestino + path.toString());
        }).forEach(resultPaths::add);
        resultPaths.forEach(x -> System.out.println(x));
        return null;
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
