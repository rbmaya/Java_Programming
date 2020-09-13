package Parser;

import java.util.*;

public class WordStatistic {
    private TreeMap<String, Integer> stat = new TreeMap<>();
    private int numOfWords;
    private ArrayList<Map.Entry<String, Integer>> sortedStatistic = new ArrayList<>();

    public void addToMap(String[] words){
        for (String word : words){
            if (!word.isEmpty()){
                if (stat.containsKey(word)) {
                    stat.replace(word, stat.get(word) + 1);
                } else {
                    stat.put(word, 1);
                }
                ++numOfWords;
            }
        }
    }

    public void sortByValue() throws Exception{
        if (!sortedStatistic.addAll(stat.entrySet())) throw new Exception("Can not elements to set.");

        sortedStatistic.sort(Map.Entry.comparingByValue());

        Collections.reverse(sortedStatistic);
    }

    public ArrayList<Map.Entry<String, Integer>> getSortedStatistic(){
        return sortedStatistic;
    }

    public double getFrequency(int entry){
        return (double) entry / numOfWords * 100;
    }

}
