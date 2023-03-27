package GUI_Project.RailroadCars;

import GUI_Project.Stuff.*;

public class RefrigeratedRailroadCar extends BasicRailroadFreightCar{
    public RefrigeratedRailroadCar(double carWeight, double maxCargoWeight) {
        super(carWeight, maxCargoWeight);
        requireElectricity = true;
        this.uniqueNum = "RRC" + uniqueNumGenerator;
    }

    @Override
    public void addCommodity(Stuff stuff) {
        if(stuff instanceof Material || stuff instanceof Human || stuff instanceof Mail || stuff instanceof Baggage)
            System.out.println(stuff.getClass().getSimpleName() + " cannot be stored here");
        else {
            commodity.add(stuff);
            currentCargoWeight += stuff.getWeight();
        }
    }
}
