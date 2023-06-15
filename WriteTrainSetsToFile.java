package GUI_Project;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class WriteTrainSetsToFile extends Thread{


    public void run()
    {
        while(true)
        {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            writeToFile(TrainSet.trainSets);
        }
    }

    public void writeToFile(ArrayList<TrainSet> trainSets)
    {
        trainSets.sort(new Comparator<TrainSet>() {
            @Override
            public int compare(TrainSet o1, TrainSet o2) {
                int diff = o2.getCurrentPosition() - o1.getCurrentPosition();
                if(diff < 0)
                    return -1;
                else if(diff > 0)
                    return 1;
                else
                    return 0;
            }
        });

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
