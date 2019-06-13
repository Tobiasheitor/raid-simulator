import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;

public class menu {

    public static void main(String[] args) throws Exception {
        Path diretorioDestino = Paths.get(args[0]);
        Path diretorioAtual = Paths.get(System.getProperty("user.dir"));

        diretorioDestino = verifyDestination(diretorioDestino);
                
        ArrayList<Path> paths = getAllDirectoriesRecursive(diretorioAtual);
        //paths = getAllClearDirectories(diretorioAtual, paths);
        //paths = concatDestinationDirectory(diretorioDestino, paths);
        copyFileTo(diretorioDestino, paths);
    }

    private static Path verifyDestination(Path path) {
        if(Files.exists(path)) {
            if(Files.isDirectory(path)) {
                return path;
            }
            return path.getParent();
        }
        if(!Files.isDirectory(path)) {
            path = path.getParent();
        }
        try {
            Path dir = Files.createDirectory(path);
            System.out.println("Diretorio \"" + dir + "\" criado");
            return dir;
        } catch (IOException e1) {
            System.out.println("Falha " + e1 + " ao criar o diretorio");
            return null;
        }
    }

    private static ArrayList<Path> getAllDirectoriesRecursive( Path path ) {
        File root = path.toFile();
        File[] list = root.listFiles();
        ArrayList<Path> paths = new ArrayList<>();

        if (list == null) return paths;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                paths.addAll( getAllDirectoriesRecursive( Paths.get(f.getAbsolutePath()) ) );
            }
            else {
                paths.add(Paths.get(f.getAbsolutePath()));
            }
        }
        return paths;
    }

    private static ArrayList<Path> getAllClearDirectories(Path diretorioAtual, ArrayList<Path> paths) {
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
        return resultPaths;
    }

    private static void copyFileTo(Path diretorioDestino, ArrayList<Path> paths) {
        System.out.println(paths.size() + " files to copy");
        paths.forEach((path)-> {
            try {
                Files.copy(path, Paths.get(diretorioDestino.toString() + "\\" + path.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Success to copy " + path);
            } catch (IOException e) {
                System.out.println("Error to copy " + e);
            }
        });
    }

}
