package Parser;

import jdk.dynalink.beans.StaticClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class Reader {
    private final Path inpPath;

    public Reader(Path inpPath){
        this.inpPath = inpPath;
    }

    public WordStatistic reading() throws IOException {
        WordStatistic statistic = new WordStatistic();

        BufferedReader reader = Files.newBufferedReader(inpPath);

        while (reader.ready()){
            String line = reader.readLine().toLowerCase();
            String [] words = line.split("[^a-z0-9]");

            statistic.addToMap(words);
        }

        return statistic;

    }

}
