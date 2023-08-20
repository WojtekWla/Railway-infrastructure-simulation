package GUI_Project;

import GUI_Project.Interface.Window;
import GUI_Project.Rail.Connection;
import GUI_Project.Rail.Station;
import GUI_Project.RailroadCars.*;
import GUI_Project.Stuff.*;

import javax.swing.*;
import java.time.Year;
import java.util.*;

public class Main {
    public static void main(String[] args) {

            Station.stations = ReadFromFiles.createStations();
            Station.createRoads(Station.stations);
            MapGenerator.mapGenerator();
            Locomotive locomotive = new Locomotive("djksad", 400,31, 42);


    //        MapGenerator.generateMap(stations.get(0), new MapGenerator.Position(50,50));

        for (int i = 0; i < Station.stations.size(); i++) {
            System.out.print((i+1) + "."+Station.stations.get(i) + " ");
        }

            SwingUtilities.invokeLater(()-> new Window());
//        System.out.println(TrainSet.myTable);

        TrainSet trainSet = new TrainSet(locomotive, Station.stations.get(0));
        TrainSet trainSet2 = new TrainSet(new Locomotive("adfka", 3124, 42,2), Station.stations.get(1));
        TrainSet trainSet3 = new TrainSet(new Locomotive("fsaf", 143, 43,12), Station.stations.get(2));

        trainSet.calculateRoad(); // to fix all function read about treemap
        trainSet2.calculateRoad();
        trainSet3.calculateRoad();

        TrainSet.trainSets.add(trainSet);
        TrainSet.trainSets.add(trainSet2);
        TrainSet.trainSets.add(trainSet3);

    //        System.out.println(Station.stations.size());




    //        for(int i = 0; i < Station.stationsOnMap.length; i++){
    //            for (int j = 0; j < Station.stationsOnMap[i].length; j++) {
    //                if(Station.stationsOnMap[i][j] != null) {
    //                    System.out.print(Station.stationsOnMap[i][j] + " ");
    //                }else
    //                    System.out.print("0 ");
    //            }
    //            System.out.println();
    //
    //        }
    //        System.out.println(MapGenerator.change);


    //
    //        System.out.println("\n\n");
    //
    //        WriteTrainSetsToFile writeTrainSetsToFile = new WriteTrainSetsToFile();
    //        writeTrainSetsToFile.start();

            for(TrainSet trainSet1 : TrainSet.trainSets) {
                trainSet1.start();
            }
    }
}
