package GUI_Project.RailroadCars;

import GUI_Project.Stuff.Material;
import GUI_Project.Stuff.Stuff;

public class GaseousFreightCar extends BasicRailroadFreightCar{
    public GaseousFreightCar(double carWeight, double maxCargoWeight) {
        super(carWeight, maxCargoWeight);
        this.uniqueNum = "GFC" + uniqueNumGenerator;
    }

    public void addCommodity(Stuff stuff) {
        if(((Material) stuff).getMaterial() == Stuff.Materials.gaseousMaterial && currentCargoWeight + stuff.getWeight() < maxCargoWeight) {
            commodity.add(stuff);
            currentCargoWeight += stuff.getWeight();
        }
        else
            System.out.println(((Material) stuff).getMaterial() + "cannot be stored there");
    }
}
