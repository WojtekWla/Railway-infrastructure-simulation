package GUI_Project;

import GUI_Project.Rail.Station;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ReadFromFiles {

    public static ArrayList<Station> createStations()
    {
        ArrayList<Station> list = new ArrayList<>();
        BufferedReader bufferedReader;

        try{
            bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project\\src\\GUI_Project\\stations.txt"));
            while(bufferedReader.ready())
            {
                list.add(new Station(bufferedReader.readLine()));
            }
        }catch (FileNotFoundException e)
        {
            System.out.println("File was not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
        return list;
    }

    public static ArrayList<TrainSet> createTrainSets(ArrayList<Station> stations)
    {
        ArrayList<TrainSet> list = new ArrayList<>();
        Random random = new Random();
        BufferedReader bufferedReader;
        try{
            bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\src\\GUI_Project\\locomotives.txt"));
            while(bufferedReader.ready())
            {
                int maxWeight = random.nextInt(2000)+1000;
                int maxNumOfCars = random.nextInt(6)+5;
                int maxNUmOfCarsElectricity = random.nextInt(3)+1;
                int randomStartingStation = random.nextInt(stations.size());
                Station station = stations.get(randomStartingStation);

                list.add(new TrainSet(new Locomotive(bufferedReader.readLine(), maxWeight, maxNumOfCars, maxNUmOfCarsElectricity),station));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
