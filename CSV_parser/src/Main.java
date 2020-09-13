import Parser.Controller;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path inpPath = Paths.get(args[0]);
        inpPath = inpPath.toAbsolutePath();
        Path outPath = Paths.get(args[1]);
        outPath = outPath.toAbsolutePath();

        Controller controller = new Controller(inpPath, outPath);

        try{
            controller.process();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }

    }
}
