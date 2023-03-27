package GUI_Project.RailroadCars;

import GUI_Project.Locomotive;
import GUI_Project.Stuff.Stuff;

import java.util.ArrayList;
import java.util.List;

public abstract class RailroadCar implements Comparable<RailroadCar>{
    public static int uniqueNumGenerator = 0;



    //private Shipper shipper
    protected String uniqueNum;
    protected double carWeight;
    protected double maxCargoWeight;
    protected double currentCargoWeight;
    protected List<Stuff> commodity;
    protected boolean requireElectricity = false;

    public RailroadCar(double carWeight, double maxCargoWeight)
    {
        this.carWeight = carWeight;
        this.maxCargoWeight = maxCargoWeight;
        currentCargoWeight = 0;
        commodity = new ArrayList<>();
        currentCargoWeight++;
        uniqueNumGenerator++;
    }

    public String getUniqueNum() {
        return uniqueNum;
    }


    public double getCarWeight() {
        return carWeight;
    }

    public void setCarWeight(double carWeight) {
        this.carWeight = carWeight;
    }

    public double getMaxCargoWeight() {
        return maxCargoWeight;
    }

    public void setMaxCargoWeight(double maxCargoWeight) {
        this.maxCargoWeight = maxCargoWeight;
    }

    public boolean requiresElectricity() {
        return requireElectricity;
    }

    public void setRequireElectricity(boolean requireElectricity) {
        this.requireElectricity = requireElectricity;
    }


    public void addCommodity(Stuff stuff)
    {
        if(currentCargoWeight + stuff.getWeight() <= maxCargoWeight) {
            commodity.add(stuff);
            currentCargoWeight += stuff.getWeight();
        }
    }

    public void removeAllCommodity(Locomotive locomotive) {
        for(Stuff stuff : commodity)
        {
            double weight = locomotive.getCurrentWeight() - stuff.getWeight();
            locomotive.setCurrentWeight(weight);
        }
        commodity.clear();

        //System.out.println("Car successfully cleared from passengers");
    }


    public void removeElementFromCommodity() {

    }//to do


    public void showCommodity() {
        for(Stuff stuff : commodity)
        {
            System.out.println(stuff);
        }
    }

    public double grossWeight()
    {
        return currentCargoWeight + carWeight;
    }

    @Override
    public int compareTo(RailroadCar o) {
        if(grossWeight() > o.grossWeight())
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +  "{" + uniqueNum + ", current capacity: " + currentCargoWeight + "/" + maxCargoWeight +
                ", gross weigh: "+ grossWeight() +"}";
    }
}
