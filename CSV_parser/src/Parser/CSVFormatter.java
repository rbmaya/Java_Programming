package Parser;

import java.util.Map;

public class CSVFormatter implements Formatter{
    @Override
    public String stringFormat(WordStatistic statistic) {
        StringBuilder formattedLine = new StringBuilder();
        for (Map.Entry<String, Integer> pair : statistic.getSortedStatistic()){
            formattedLine.append(pair.getKey()).append(";").append(pair.getValue()).append(";");
            formattedLine.append(String.format("%.3f", statistic.getFrequency(pair.getValue()))).append("%\n");
        }

        return formattedLine.toString();
    }
}
