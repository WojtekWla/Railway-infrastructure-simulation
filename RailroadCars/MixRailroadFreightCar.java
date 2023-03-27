package GUI_Project.RailroadCars;

import GUI_Project.Stuff.Human;
import GUI_Project.Stuff.Material;
import GUI_Project.Stuff.Stuff;


public class MixRailroadFreightCar extends LiquidFreightCar {
    public MixRailroadFreightCar(double carWeight, double maxCargoWeight) {
        super(carWeight, maxCargoWeight);
        this.uniqueNum = "MFC" + uniqueNumGenerator;
    }

    public void addCommodity(Stuff stuff) {
        if((((Material) stuff).getMaterial() == Stuff.Materials.liquidMaterial || ((Material) stuff).getMaterial() == Stuff.Materials.toxicMaterial )&& currentCargoWeight + stuff.getWeight() <= maxCargoWeight) {
            commodity.add(stuff);
            currentCargoWeight += stuff.getWeight();
        }
        else
            System.out.println(((Material) stuff).getMaterial() + "cannot be stored there");
    }


}
