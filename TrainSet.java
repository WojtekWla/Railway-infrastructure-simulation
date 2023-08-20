package GUI_Project;

import GUI_Project.Interface.MyTable;
import GUI_Project.Interface.PanelController;
import GUI_Project.Rail.Connection;
import GUI_Project.Rail.Station;
import GUI_Project.RailroadCars.MixRailroadFreightCar;
import GUI_Project.RailroadCars.PassengerRailroadCar;
import GUI_Project.RailroadCars.RailroadCar;
import GUI_Project.RailroadCars.RefrigeratedRailroadCar;
import GUI_Project.Stuff.Car;
import GUI_Project.Stuff.Material;
import GUI_Project.Stuff.Stuff;
import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import java.util.*;

public class TrainSet extends Thread{
    public static int identifierGen = 1;
    public static ArrayList<TrainSet> trainSets = new ArrayList<>();
    public static MyTable myTable = null;


    private PanelController panelController;
    private Locomotive locomotive;
    private Station startingStation;
    private Station lastStation;
    private Station currentStation;
    private Station nextStation;
    private Connection currentConnection;
    private List<RailroadCar> railroadCars;
    private Map<Station, Integer> stations;
    private int currentPosition;
    private int positionToNextStation;
    private String id;
    private int roadLength;
    private int distanceToNextStation;
    private boolean onStation;
    private boolean endOfRoad;
    //private boolean collisionDetected = false;
    //private int stationTimer;

    public Station getCurrentStation() {
        return currentStation;
    }

    public List<RailroadCar> getRailroadCars() {
        return railroadCars;
    }

    public TrainSet(Locomotive locomotive, Station startingStation)
    {
        this.locomotive = locomotive;
        this.startingStation = startingStation;
        this.myTable = myTable;
        railroadCars = new ArrayList<>();
        stations = new LinkedHashMap<>();
        id = "TS" + identifierGen++;

        stations.put(startingStation,0);
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

    public Map<Station, Integer> getStations() {
        return stations;
    }

    public void setStations(Map<Station, Integer> stations) {
        this.stations = stations;
    }

    public Station getStartingStation() {
        return startingStation;
    }

    public int getRoadLength() {
        return roadLength;
    }

    public Station getLastStation() {
        return lastStation;
    }

    public Station getNextStation() {
        return nextStation;
    }

    public int getDistanceToNextStation() {
        return distanceToNextStation;
    }

    public void setOnStation(boolean onStation) {
        this.onStation = onStation;
    }

    public void setNextStationTest(Station nextStation) {
        this.nextStation = nextStation;
    }

    public String get_id(){
        return id;
    }

    public PanelController getPanelController() {
        return panelController;
    }

    public void setPanelController(PanelController panelController) {
        this.panelController = panelController;
    }

    @Override
    public void run() {
        try {

            while (!Thread.interrupted()) {
                locomotive.setTimeInRoad(locomotive.getTimeInRoad() + 1);
                translate();
                locomotive.setChangeSpeedTimer(locomotive.getChangeSpeedTimer() + 1);
                locomotive.changeSpeed();

                if (distanceToNextStation <= positionToNextStation) {
                    currentStation = nextStation;
                    if (currentStation != lastStation)
                        onStation = true;
                    else
                        endOfRoad = true;
                    stayOnStation();

                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }

            }
        }catch(Exception e)
        {

        }

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


    void stayOnStation(/*Station station*/)
    {
        //UDPATE
        //currentConnection.setUsed(false);
        currentConnection.setUsedToFalse();
        System.out.println(currentConnection + " " + currentConnection.isUsed());
        //END

        for(RailroadCar railroadCar : railroadCars)
        {
            if(railroadCar instanceof PassengerRailroadCar)
            {
                ((PassengerRailroadCar) railroadCar).exitOnStation(this);
                ((PassengerRailroadCar) railroadCar).enterOnStation(this);
            }
        }

        int timeToWait;
        if(onStation) {
            System.out.println(id + " stays on station " + nextStation);
            timeToWait = 2000;
            setNextStation();
            positionToNextStation = 0;
            locomotive.setSpeed(100);
            locomotive.setTimeInRoad(1);

        }
        else {
            System.out.println(id + " arrived at last station " + nextStation);
            timeToWait = 5000;
            startingStation = lastStation;
            lastStationFun();
            calculateRoad();
        }

        panelController.updateLabel(this);
        SwingUtilities.invokeLater(()->myTable.repaint());
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //UPDATE
        lookForCollision(trainSets);
        //END
        onStation = false;
        endOfRoad = false;
    }

    public void lookForCollision(ArrayList<TrainSet> trainSets)
    {
//        TrainSet collide = null;
//        for(Connection connection : Connection.connections)
//        {//station 1 station 2
//            Station station1 = connection.getStation1();
//            Station
//        }

        getConnection();
        System.out.println(id + " Next connection" + currentConnection + currentConnection.isUsed());
        if(currentConnection.isUsed())
        {
            currentConnection.setCurrentWaiting(this);
            System.out.println(id + "Waiting for route to be cleared");
            //System.out.println("Waiting train" + currentConnection.getWaitingTrain());
//            while(currentConnection.isUsed());
            //UPDATE
            //synchronized (Connection.monitor) {

            try {
                synchronized (currentConnection) {
                    currentConnection.setWaitingTrainsNum(currentConnection.getWaitingTrainsNum()+1);
                    currentConnection.wait();
                }
            }catch (InterruptedException e) {
                System.out.println("Leaving");
            }
           //}
        }
//          currentConnection.setUsed(true);
            //UDPATE
        currentConnection.setUsedToTrue();
        //currentConnection.setCurrentRunning(this);
            //END
        System.out.println(id + " ready to departure");

//            onStation = false;
//            endOfRoad = false;


//        if(collisionDetected) {
//            System.out.println(id + " waits for " + collide.id + " to arrive station");
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        
//        while(collisionDetected)
//        {
//            if(collide.onStation)
//                collisionDetected = false;
//        }





    }

    public void getConnection()
    {
        Connection result = null;
        for(Connection connection : Connection.connections)
        {
            Station station1 = connection.getStation1();
            Station station2 = connection.getStation2();

            if(station1 == currentStation && station2 == nextStation || station1 == nextStation && station2 == currentStation)
                result = connection;
        }

        if(result == null)
        {
            System.out.println("Connection not found");
        }else
        {
            this.currentConnection = result;
        }


    }


    public void travelInfo()
    {

        double distanceToEnd = Math.round((currentPosition/(double)roadLength)*100);
        double nextStation = Math.round(positionToNextStation/(double)distanceToNextStation*100);
        System.out.println(id + ": all distance traveled (beginning, end): " + distanceToEnd + "%; " +
                "distance traveled to next station: " + nextStation + "%");

//        panelController.updateRoad(nextStation);
    }

    void lastStationFun()
    {
        stations.clear();
        startingStation = currentStation;
        currentPosition = 0;
        positionToNextStation = 0;
        roadLength = 0;
        locomotive.setSpeed(100);
        locomotive.setTimeInRoad(1);
        locomotive.setChangeSpeedTimer(0);
    }

    public void setNextStation()
    {
        List<Station> keys = new ArrayList<>(stations.keySet());
        int index = keys.indexOf(currentStation)+1;

        if(index < keys.size())
        {
            nextStation = keys.get(index);
            distanceToNextStation = stations.get(nextStation);
        }


    }

    public void calculateRoad()
    {
        Station currentStation = startingStation;
        Random random = new Random();
        int numOfStation = 3/*random.nextInt(7)+4*/;
        System.out.print(currentStation + " ");
        int length = 1;
        while(this.stations.size()-1 < numOfStation)
        {
            int randomStation = random.nextInt(currentStation.getConnectedStations().size());
            Station stationToInsert = (Station)currentStation.getConnectedStations().keySet().toArray()[randomStation];
//            if(!this.stations.containsKey(stationToInsert))
//            {
//                this.stations.put(stationToInsert, currentStation.getConnectedStations().get(stationToInsert));
//                roadLength += currentStation.getConnectedStations().get(stationToInsert);
//                System.out.print(stationToInsert + " ");
//                currentStation = stationToInsert;
//            }
//
            if(!stations.containsKey(stationToInsert)){
                this.stations.put(stationToInsert, currentStation.getConnectedStations().get(stationToInsert));
//            if(length < stations.size())
//            {
                roadLength += currentStation.getConnectedStations().get(stationToInsert);
                System.out.print(stationToInsert + " ");
                currentStation = stationToInsert;
                lastStation = currentStation;
                length++;
            //}
            }

        }
        this.currentStation = startingStation;
        setNextStation();
        //UPDATE
        getConnection();
        //END
        System.out.println();

    }

    public void translate()
    {
        int calculation = (int) (locomotive.getSpeed() * locomotive.getTimeInRoad()/6);
        currentPosition = calculation;
        positionToNextStation = calculation;



        if(positionToNextStation/distanceToNextStation > 1)
            positionToNextStation = distanceToNextStation;

        if(currentPosition/roadLength >1)
            currentPosition = roadLength;

        System.out.println(id +" total distance" + roadLength + " distance to next station " + distanceToNextStation +
                ", current speed and position " + locomotive.getSpeed() +
                "km/h "+ positionToNextStation);
        travelInfo();
    }




    @Override
    public String toString() {
        railroadCars.sort(new Comparator<RailroadCar>() {
            @Override
            public int compare(RailroadCar o1, RailroadCar o2) {
                return (int)(o1.grossWeight() - o2.grossWeight());
            }
        });

        String output = "railroad cars: ";
        for(RailroadCar railroadCar : railroadCars)
            output += railroadCar.getUniqueNum() + " " + railroadCar.grossWeight() + ", ";


        output = output.substring(0, output.length()-2) + ".";

        return "Trainset: " + id + " locomotive," + locomotive +" starting station:" + startingStation  + ", current position " + currentPosition  /*" current position " + currentPosition + ", " + output*/;
    }


}
