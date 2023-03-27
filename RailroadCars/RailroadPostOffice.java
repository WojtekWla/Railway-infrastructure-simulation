package GUI_Project.RailroadCars;

import GUI_Project.Stuff.Human;
import GUI_Project.Stuff.Stuff;

import java.util.List;

public class RailroadPostOffice extends RailroadCar{
    //collecting and withdrawal post

    public RailroadPostOffice(double carWeight, double maxCargoWeight) {
        super(carWeight, maxCargoWeight);
        requireElectricity = true;
        this.uniqueNum = "RPO" + uniqueNumGenerator;
    }



}
