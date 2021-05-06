package FileSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public interface DataHandler {

    default void ImportPlayerStatisticsData() throws FileNotFoundException {
        try {
            FileInputStream fileIn = new FileInputStream("/tmp/PlayerStatistics.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (Player) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
