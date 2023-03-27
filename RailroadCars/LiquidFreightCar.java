package GUI_Project.RailroadCars;

import GUI_Project.Stuff.Material;
import GUI_Project.Stuff.Stuff;

public class LiquidFreightCar extends BasicRailroadFreightCar{
    public LiquidFreightCar(double carWeight, double maxCargoWeight) {
        super(carWeight, maxCargoWeight);
        this.uniqueNum = "LFC" + uniqueNumGenerator;
    }

    @Override
    public void addCommodity(Stuff stuff) {
        if(((Material) stuff).getMaterial() == Stuff.Materials.liquidMaterial && currentCargoWeight + stuff.getWeight() < maxCargoWeight) {
            commodity.add(stuff);
            currentCargoWeight += stuff.getWeight();
        }
        else
            System.out.println(((Material) stuff).getMaterial() + "cannot be stored there");
    }

}
