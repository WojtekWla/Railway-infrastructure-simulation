package GUI_Project;

import GUI_Project.Rail.Station;
import GUI_Project.RailroadCars.*;
import GUI_Project.Stuff.Human;
import GUI_Project.Stuff.Material;
import GUI_Project.Stuff.Stuff;
import GUI_Project.Stuff.Ticket;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // 1st train set
        Locomotive locomotive = new Locomotive("djksad", 400,31, 42);
        TrainSet trainSet = new TrainSet(locomotive);


        RefrigeratedRailroadCar refrigeratedRailroadCar_1 = new RefrigeratedRailroadCar(20,100);
        MixRailroadFreightCar mixRailroadFreightCar = new MixRailroadFreightCar(30, 200);
        PassengerRailroadCar passengerRailroadCar = new PassengerRailroadCar(40,300,20);
        passengerRailroadCar.addCommodity(new Human(20, "Wojtek", "Jakis", new Ticket(new Station("Warszawa"), new Station("Malbork"))));

        trainSet.addCar(refrigeratedRailroadCar_1);
        trainSet.addCar(mixRailroadFreightCar);
        trainSet.addCar(passengerRailroadCar);


        //2nd railroad

        Locomotive locomotive1 = new Locomotive("jakas", 3425, 4, 2);
        RefrigeratedRailroadCar refrigeratedRailroadCar = new RefrigeratedRailroadCar(43, 400);
        ToxicRailroadFreightCar toxicRailroadFreightCar = new ToxicRailroadFreightCar(45, 200);
        PassengerRailroadCar passengerRailroadCar1 = new PassengerRailroadCar(564, 1000, 50);
        ExplosivesFreightCar explosivesFreightCar = new ExplosivesFreightCar(31, 425);

        TrainSet trainSet1 = new TrainSet(locomotive1);
        trainSet1.addCar(refrigeratedRailroadCar);
        trainSet1.addCar(toxicRailroadFreightCar);
        trainSet1.addCar(passengerRailroadCar1);
        trainSet1.addCar(explosivesFreightCar);


        trainSet.setCurrentPosition(1);
        ArrayList<TrainSet> trainSets = new ArrayList<>();
        trainSets.add(trainSet);
        trainSets.add(trainSet1);
        //
        //WriteTrainSetsToFile writeTrainSetsToFile = new WriteTrainSetsToFile(trainSets);


//        Arrays.sort(trainSets.toArray());
//        System.out.println(Arrays.toString(trainSets.toArray()));
        Arrays.sort(trainSet.getRailroadCars().toArray());
        System.out.println(Arrays.toString(trainSet.getRailroadCars().toArray()));


    }
}
