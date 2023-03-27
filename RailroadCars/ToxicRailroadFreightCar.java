package GUI_Project.RailroadCars;

import GUI_Project.Stuff.Material;
import GUI_Project.Stuff.Stuff;

public class ToxicRailroadFreightCar extends HeavyRailroadCar{
    public ToxicRailroadFreightCar(double carWeight, double maxCargoWeight) {
        super(carWeight, maxCargoWeight);
        this.uniqueNum = "TFC" + uniqueNumGenerator;
    }

    public void addCommodity(Stuff stuff) {
        if(((Material) stuff).getMaterial() == Stuff.Materials.toxicMaterial && currentCargoWeight + stuff.getWeight() < maxCargoWeight) {
            commodity.add(stuff);
            currentCargoWeight += stuff.getWeight();
        }
        else
            System.out.println("Wrong freight car");
    }

}
