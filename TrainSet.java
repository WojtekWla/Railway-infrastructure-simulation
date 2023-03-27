package GUI_Project;

import GUI_Project.Rail.Station;
import GUI_Project.RailroadCars.MixRailroadFreightCar;
import GUI_Project.RailroadCars.PassengerRailroadCar;
import GUI_Project.RailroadCars.RailroadCar;
import GUI_Project.RailroadCars.RefrigeratedRailroadCar;
import GUI_Project.Stuff.Car;
import GUI_Project.Stuff.Material;
import GUI_Project.Stuff.Stuff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TrainSet implements Comparable<TrainSet>{
    public static int identifierGen = 1;

    private Locomotive locomotive;
    private Station currentStation;
    private List<RailroadCar> railroadCars;
    private int currentPosition;
    private String id;

    public Station getCurrentStation() {
        return currentStation;
    }

    public List<RailroadCar> getRailroadCars() {
        return railroadCars;
    }

    public TrainSet(Locomotive locomotive)
    {
        this.locomotive = locomotive;
        railroadCars = new ArrayList<>();
        id = "TS" + identifierGen++;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
    }

    public void addCar(RailroadCar rc)
    {

        if(railroadCars.size() + 1 <= locomotive.getMaxNumOfCars() && (rc.grossWeight() + locomotive.getCurrentWeight() <= locomotive.getMaxWeight()))
        {
            if(rc.requiresElectricity() && locomotive.getCurrentNumOfCarsWithElectricity() + 1 <= locomotive.getMaxNumOfCarsWithElectricalGrid()) {
                railroadCars.add(rc);
                locomotive.setCurrentNumOfCarsWithElectricity(locomotive.getCurrentNumOfCarsWithElectricity()+1);
                locomotive.setCurrentWeight(rc.grossWeight() + locomotive.getCurrentWeight());
            }
            else if(!rc.requiresElectricity()) {
                railroadCars.add(rc);
                locomotive.setCurrentWeight(rc.grossWeight() + locomotive.getCurrentWeight());
            }
            else
                System.out.println(rc.getUniqueNum() + " can not be added to train set, because it is full number of trains with need of electricity");
        }else
            if(railroadCars.size() + 1 <= locomotive.getMaxNumOfCars())
                System.out.println(rc.getUniqueNum() + " can not be added to train set, because it is full number of railroad cars");
            else
                System.out.println(rc.getUniqueNum() + " can not be added to train set, because gross weight of railroad car exceeds maximum weight");
    }

    public void removeCar()
    {
        System.out.println("Choose railroad car to remove");
        String car = new Scanner(System.in).next();
        System.out.println(railroadCars.size());
        for(RailroadCar railroadCar : railroadCars)
        {
            if(railroadCar.getUniqueNum().equalsIgnoreCase(car))
            {
                railroadCar.removeAllCommodity(locomotive);
                railroadCars.remove(railroadCar);
                break;
            }
        }
        System.out.println("Successfully removed");
        System.out.println(railroadCars.size());

    }

    public void removeAllCars()
    {
        railroadCars.clear();
    }


    public void displayTrainSet()
    {
        System.out.println(locomotive);
        for(RailroadCar railroadCar : railroadCars)
            System.out.println(railroadCar);
    }

    void hazard() throws RailroadHazard
    {
        if(locomotive.getSpeed() > 200)
            throw new RailroadHazard();
    }

    void stayOnStation(Station station)
    {
        for(RailroadCar railroadCar : railroadCars)
        {
            if(railroadCar instanceof PassengerRailroadCar)
            {
                ((PassengerRailroadCar) railroadCar).exitOnStation(this);
                ((PassengerRailroadCar) railroadCar).enterOnStation(this);
            }
        }
    }


    @Override
    public int compareTo(TrainSet o) {
        if(currentPosition > o.getCurrentPosition())
            return -1;
        else
            return 1;
    }

    @Override
    public String toString() {
        Arrays.sort(railroadCars.toArray());

        String output = "railroad cars: ";
        for(RailroadCar railroadCar : railroadCars)
        {
            output += railroadCar.getUniqueNum() + " " + railroadCar.grossWeight() + ", ";
        }

        output = output.substring(0, output.length()-2) + ".";

        return "Trainset: " + id + " current position " + currentPosition + ", " + output;
    }
}
