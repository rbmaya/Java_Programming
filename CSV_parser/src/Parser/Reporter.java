package Parser;

import java.io.*;
import java.nio.file.Path;

public class Reporter {
    public void getReport(WordStatistic statistic, Path outPath) throws FileNotFoundException {
        PrintStream outStream = new PrintStream(new PrintStream(outPath.toString()));
        outStream.println("Word; Frequency; Percentage frequency:");

        CSVFormatter formatter = new CSVFormatter();

        outStream.print(formatter.stringFormat(statistic));
    }
}
