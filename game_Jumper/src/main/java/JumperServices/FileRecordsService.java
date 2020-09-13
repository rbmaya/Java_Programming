package JumperServices;

import Observers.Observable;
import Observers.Observer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import static JumperConstants.Constants.COUNT_OF_RECORDS;

public class FileRecordsService implements RecordsService, Observer {
    private Properties records = new Properties();

    @Override
    public void save(Integer score) {
        try {
            records.load(new FileInputStream("src/main/resources/records.properties"));
        } catch (Exception ignored) {
        }

        records.setProperty("0", score.toString());

        for (Integer i = COUNT_OF_RECORDS; !i.equals(0); --i) {
            int value = Integer.parseInt(records.getProperty(i.toString()));
            if (value >= score) {
                if (value == score || i.equals(COUNT_OF_RECORDS)) {
                    return;
                }
                for (Integer j = COUNT_OF_RECORDS; !j.equals(i); --j) {
                    int tmp = Integer.parseInt(records.getProperty(Integer.valueOf(j - 1).toString()));
                    records.setProperty(j.toString(), Integer.toString(tmp));
                }
                records.setProperty((Integer.valueOf(i + 1)).toString(), score.toString());
                try {
                    records.store(new FileOutputStream("src/main/resources/records.properties"), null);
                } catch (Exception ignored) {
                }
                return;
            }
        }
        records.setProperty((Integer.valueOf(1)).toString(), score.toString());
        try {
            records.store(new FileOutputStream("src/main/resources/records.properties"), null);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void update(Observable.Event<?> event) {
        if (event instanceof Observable.RecordEvent){
            save((Integer) event.getContext());
        }
    }
}
