package GUI_Project;

import GUI_Project.Rail.Station;
import GUI_Project.RailroadCars.MixRailroadFreightCar;
import GUI_Project.RailroadCars.RailroadCar;
import GUI_Project.RailroadCars.RefrigeratedRailroadCar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Locomotive {
    public static int numGen = 1;

    private String name;
    private String id;
    private double speed;
    private double maxWeight;
    private double currentWeight;
    private int maxNumOfCars;
    private int maxNumOfCarsWithElectricalGrid;
    private int currentNumOfCarsWithElectricity;
    //private List<RailroadCar> railroadCars;


    public Locomotive(String name, double maxWeight, int maxNumOfCars, int maxNumOfCarsWithElectricalGrid)
    {
        this.name = name;
        this.maxWeight = maxWeight;
        this.maxNumOfCars = maxNumOfCars;
        this.maxNumOfCarsWithElectricalGrid = maxNumOfCarsWithElectricalGrid;

        speed = 10;
        //railroadCars = new ArrayList<>();
        this.id = "LOC" + numGen++;
    }


    public static int getNumGen() {
        return numGen;
    }

    public static void setNumGen(int numGen) {
        Locomotive.numGen = numGen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public int getMaxNumOfCars() {
        return maxNumOfCars;
    }

    public void setMaxNumOfCars(int maxNumOfCars) {
        this.maxNumOfCars = maxNumOfCars;
    }

    public int getMaxNumOfCarsWithElectricalGrid() {
        return maxNumOfCarsWithElectricalGrid;
    }

    public void setMaxNumOfCarsWithElectricalGrid(int maxNumOfCarsWithElectricalGrid) {
        this.maxNumOfCarsWithElectricalGrid = maxNumOfCarsWithElectricalGrid;
    }

    public int getCurrentNumOfCarsWithElectricity() {
        return currentNumOfCarsWithElectricity;
    }

    public void setCurrentNumOfCarsWithElectricity(int currentNumOfCarsWithElectricity) {
        this.currentNumOfCarsWithElectricity = currentNumOfCarsWithElectricity;
    }

    public void changeSpeed()
    {
        int r = new Random().nextInt(100) +1;
        double newSpeed = speed*0.03;
        if(r <= 50)//increase
            speed += newSpeed;
        else //decrease
        {
            if(speed < newSpeed)
                speed = 0;
            else
                speed -= newSpeed;
        }
    }



    @Override
    public String toString() {
        return "Locomotive{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", speed=" + speed +
                ", currentWeight=" + currentWeight + "/" +maxWeight +
                ", currentNumOfCarsWithElectricity=" + currentNumOfCarsWithElectricity +
                '}';
    }



}
