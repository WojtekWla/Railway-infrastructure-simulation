package GUI_Project.RailroadCars;

import GUI_Project.Stuff.Stuff;

public class RailRoadBaggageAndMail extends RailroadCar{

    public RailRoadBaggageAndMail(double carWeight, double maxCargoWeight) {
        super(carWeight, maxCargoWeight);
        this.uniqueNum = "RBM" + uniqueNumGenerator;
    }

}
