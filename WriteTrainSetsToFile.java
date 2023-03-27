package GUI_Project;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WriteTrainSetsToFile {

    public WriteTrainSetsToFile(ArrayList<TrainSet> trainSets)
    {
        writeToFile(trainSets);
    }


    public void writeToFile(ArrayList<TrainSet> trainSets)
    {
        Arrays.sort(trainSets.toArray());

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\src\\GUI_Project\\TrainSets.txt"));

            for(TrainSet trainSet : trainSets)
            {
                bufferedWriter.write(trainSet.toString() + "\n");
            }

            bufferedWriter.close();
        }catch(FileNotFoundException fileNotFoundException)
        {
            System.out.println("Can't save to file");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Successfully saved");
    }

}
