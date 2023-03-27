package GUI_Project.RailroadCars;

import GUI_Project.Rail.Station;
import GUI_Project.Stuff.Human;
import GUI_Project.Stuff.Stuff;
import GUI_Project.TrainSet;

public class PassengerRailroadCar extends RailroadCar{

    private int seatsCapacity;
    private int currentNumberOfPassengers;


    public PassengerRailroadCar(int carWeight, int maxCargoWeight, int seatsNumber) {
        super(carWeight, maxCargoWeight);
        this.seatsCapacity = seatsNumber;
        requireElectricity = true;
        currentNumberOfPassengers = 0;
        uniqueNum = "PRC" + uniqueNumGenerator;
    }

    @Override
    public void addCommodity(Stuff stuff) {
        if(currentNumberOfPassengers != seatsCapacity && maxCargoWeight >= currentCargoWeight + stuff.getWeight())
        {
            commodity.add(stuff);
            currentNumberOfPassengers++;
            currentCargoWeight += stuff.getWeight();
        }else
        {
            if(currentNumberOfPassengers >= seatsCapacity)
                System.out.println("No more empty place in this car");
            else if(maxCargoWeight < currentCargoWeight + stuff.getWeight())
                System.out.println("The car is too heavy");
        }
    }

    public void exitOnStation(TrainSet trainSet)
    {
        Human toRemove = null;
        for(Stuff human : commodity)
        {
            Human human1 = (Human) human;
            if((human1.getTicket().getExit().getName() == trainSet.getCurrentStation().getName()))
            {
                System.out.println(human1.getName() + " " + human1.getSurname() + " exits train");
                this.carWeight -= human1.getWeight();
                //think about refreshing train set weight
            }
            if(toRemove != null)
                commodity.remove(toRemove);
            toRemove = human1;
        }
        if(toRemove != null)
            commodity.remove(toRemove);

    }

    public void enterOnStation(TrainSet trainSet){

        for(Human human : trainSet.getCurrentStation().getPeopleWaitingForTrain()) {
            if (trainSet.getCurrentStation().getName() == human.getTicket().getEntry().getName()) {
                System.out.println(human.getName() + " " + human.getSurname() + "enters train");
                addCommodity(human);
            } else
                System.out.println("Wrong station");
        }
    }


}
