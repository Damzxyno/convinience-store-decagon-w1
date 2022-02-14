package operations.interfaces;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public interface CommonOperations {


    default void createAFileToSaveData(String filePath, String fileContent){
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            fileWriter.write(fileContent);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
