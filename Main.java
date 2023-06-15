package GUI_Project;

import GUI_Project.Rail.Connection;
import GUI_Project.Rail.Station;
import GUI_Project.RailroadCars.*;
import GUI_Project.Stuff.*;

import java.time.Year;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1st train set

//
//
//        RefrigeratedRailroadCar refrigeratedRailroadCar_1 = new RefrigeratedRailroadCar(20,100);
//        MixRailroadFreightCar mixRailroadFreightCar = new MixRailroadFreightCar(30, 200);
//        PassengerRailroadCar passengerRailroadCar = new PassengerRailroadCar(40,300,20);
//        passengerRailroadCar.addCommodity(new Human(20, "Wojtek", "Jakis", new Ticket(new Station("Warszawa"), new Station("Malbork"))));
//
//        trainSet.addCar(refrigeratedRailroadCar_1);
//        trainSet.addCar(mixRailroadFreightCar);
//        trainSet.addCar(passengerRailroadCar);
//
//
//        //2nd railroad
//
//        Locomotive locomotive1 = new Locomotive("jakas", 3425, 4, 2);
//        Locomotive locomotive2 = new Locomotive("jfafs", 3425, 2, 1);
//        RefrigeratedRailroadCar refrigeratedRailroadCar = new RefrigeratedRailroadCar(43, 400);
//        ToxicRailroadFreightCar toxicRailroadFreightCar = new ToxicRailroadFreightCar(45, 200);
//        PassengerRailroadCar passengerRailroadCar1 = new PassengerRailroadCar(564, 1000, 50);
//        ExplosivesFreightCar explosivesFreightCar = new ExplosivesFreightCar(31, 425);
//
//        refrigeratedRailroadCar.addCommodity(new Food(200));
//        toxicRailroadFreightCar.addCommodity(new Material(123, Stuff.Materials.toxicMaterial));
//        passengerRailroadCar1.addCommodity(new Human(235, "Gaba", "Jakas", new Ticket(new Station("kfadjkf"), new Station("fafav"))));
//        explosivesFreightCar.addCommodity(new Material(420, Stuff.Materials.explosives));
//
//        ArrayList<RailroadCar> railroadCars = new ArrayList<>();
//        railroadCars.add(refrigeratedRailroadCar);
//        railroadCars.add(toxicRailroadFreightCar);
//        railroadCars.add(passengerRailroadCar1);
//        railroadCars.add(explosivesFreightCar);
//
//        railroadCars.sort(new Comparator<RailroadCar>() {
//            @Override
//            public int compare(RailroadCar o1, RailroadCar o2) {
//                return (int)(o1.grossWeight() - o2.grossWeight());
//            }
//        });
//
//        System.out.println(railroadCars);
//
//        TrainSet trainSet2 = new TrainSet(locomotive2, new Station("dfhoqiw"));
//        TrainSet trainSet1 = new TrainSet(locomotive1, new Station("fjafksd"));
//
//        ArrayList<TrainSet> trainSets = new ArrayList<>();
//        trainSets.add(trainSet2);
//        trainSets.add(trainSet1);
//
//        trainSet2.setCurrentPosition(2);
//        System.out.println(trainSet2.getCurrentPosition());
//        System.out.println(trainSet1.getCurrentPosition());
//
//
//
//        System.out.println(trainSets);
//
//        trainSet1.addCar(refrigeratedRailroadCar);
//        trainSet1.addCar(toxicRailroadFreightCar);
//        trainSet1.addCar(passengerRailroadCar1);
//        trainSet1.addCar(explosivesFreightCar);
//
//
//        trainSet.setCurrentPosition(1);
//        ArrayList<TrainSet> trainSets = new ArrayList<>();
//        trainSets.add(trainSet);
//        trainSets.add(trainSet1);
//        //
//        //WriteTrainSetsToFile writeTrainSetsToFile = new WriteTrainSetsToFile(trainSets);
//
//
////        Arrays.sort(trainSets.toArray());
////        System.out.println(Arrays.toString(trainSets.toArray()));
//        Arrays.sort(trainSet.getRailroadCars().toArray());
//        System.out.println(Arrays.toString(trainSet.getRailroadCars().toArray()));
        ArrayList<Station> stations= ReadFromFiles.createStations();

       Station.createRoads(stations);
//       for(Connection connection : Connection.connections){
//           System.out.println(connection);
//       }
        Locomotive locomotive = new Locomotive("djksad", 400,31, 42);
        TrainSet trainSet = new TrainSet(locomotive, stations.get(0));
        TrainSet trainSet2 = new TrainSet(new Locomotive("adfka", 3124, 42,2), stations.get(1));
        TrainSet trainSet3 = new TrainSet(new Locomotive("fsaf", 143, 43,12), stations.get(2));
//        for(int i = 0; i < stations.size(); i++)
//        {
//            System.out.println(i+1 + " " + stations.get(i));
//        }

//        System.out.println("train set first station" + trainSet.getStations().get(stations.get(0)));
        trainSet.calculateRoad(); // to fix all function read about treemap
        trainSet2.calculateRoad();
        trainSet3.calculateRoad();
        //System.out.println();
        //Station station1 = new Station("Cedarville Station");

//        System.out.println();
//        int connections = 1;
//        for(Map.Entry<Station, Integer> entry : trainSet.getStations().entrySet())
//        {
//            System.out.println(connections + ": " + entry.getKey() + " distance: " + entry.getValue() +", ");
//            connections++;
//        }
        //System.out.println(trainSet.getStations().size());
//
//        while(true)
//        {
//            locomotive.timer();
//            locomotive.changeSpeed();
//            trainSet.translate();
//            System.out.println("Distance: " + trainSet.getRoadLength() + "trains speed: " + locomotive.getSpeed() +" Trains position: " + trainSet.getCurrentPosition());
//        }


//        System.out.println(trainSet.getStations().size());
//        System.out.println(trainSet.getStartingStation());
//        System.out.println(trainSet.getLastStation());
//
//        System.out.println("\n\n");
////
        System.out.println("\n\n");

        WriteTrainSetsToFile writeTrainSetsToFile = new WriteTrainSetsToFile();
        writeTrainSetsToFile.start();
        TrainSet.trainSets.add(trainSet);
        TrainSet.trainSets.add(trainSet2);
        TrainSet.trainSets.add(trainSet3);
        for(TrainSet trainSet1 : TrainSet.trainSets) {
            trainSet1.start();
        }

//        System.out.println();
//        ArrayList<TrainSet> trainSets = ReadFromFiles.createTrainSets(stations);
//        for(TrainSet trainSet1 : trainSets)
//        {
//            System.out.println(trainSet1);
//        }

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        trainSets.sort(new Comparator<TrainSet>() {
//            @Override
//            public int compare(TrainSet o1, TrainSet o2) {
//                int diff = o2.getCurrentPosition() - o1.getCurrentPosition();
//                if(diff < 0)
//                    return -1;
//                else if(diff > 0)
//                    return 1;
//                else
//                    return 0;
//
//               //return Integer.compare(o2.getCurrentPosition(), o1.getCurrentPosition());
//
//            }
//        });
//
//        System.out.println(trainSets);

    }
}
