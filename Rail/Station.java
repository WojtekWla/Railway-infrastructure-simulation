package GUI_Project.Rail;

import GUI_Project.Stuff.Human;

import java.util.*;

public class Station{
    public static ArrayList<Station> stations = new ArrayList<>();


    private String name;
    private List<Human> peopleWaitingForTrain;
    private Map<Station, Integer> connectedStations;
    private int maxStationConnections;



    public Station(String name)
    {
        this.name = name;
        peopleWaitingForTrain = new ArrayList<>();
        connectedStations = new HashMap<>();
        maxStationConnections = 2; //new Random().nextInt(3)+3;
    }

    public String getName() {
        return name;
    }

    public List<Human> getPeopleWaitingForTrain() {
        return peopleWaitingForTrain;

    }

    public void addPeople(Human[] human)
    {
        for (int i = 0; i < human.length; i++) {
            peopleWaitingForTrain.add(human[i]);
        }
    }

    public Map<Station, Integer> getConnectedStations() {
        return connectedStations;
    }

    public int getMaxStationConnections() {
        return maxStationConnections;
    }

    public void setConnectedStations(Map<Station, Integer> connectedStations) {
        this.connectedStations = connectedStations;
    }

    public static void createRoads(List<Station> stations)
    {
        long startTime;
        //long time;
        boolean t = false;
        Random random = new Random();
        for(Station station : stations)
        {
            startTime = System.currentTimeMillis();
            while(station.connectedStations.size() < station.maxStationConnections && (t = ((System.currentTimeMillis() - startTime)) <= 3000)) {
                int randomStationNum = random.nextInt(stations.size());
                int randomLengthBetweenStations = random.nextInt(250) + 50;
                Station stationToConnect = stations.get(randomStationNum);
                if (stationToConnect.connectedStations.size() < stationToConnect.maxStationConnections && !stationToConnect.name.equals(station.name)) {
                    station.connectedStations.put(stationToConnect, randomLengthBetweenStations);
                    stationToConnect.connectedStations.put(station, randomLengthBetweenStations);
                    Connection.connections.add(new Connection(station, stationToConnect));
                }

            }
            if(!t)
            {
                for(Station station1 : stations)
                {
                    station1.connectedStations.clear();
                }
                Connection.connections.clear();
                createRoads(stations);
                return;
            }

            System.out.print(station.name + ": ");
            for(Map.Entry<Station, Integer> entry : station.connectedStations.entrySet())
            {
                System.out.print(entry.getKey().name + ", ");
            }
            System.out.println("max connections: " + station.maxStationConnections + " connected" + station.connectedStations.size());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return name.equals(station.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
//        String toDisplay = new String();
//        for(Map.Entry<Station, Integer> entry : connectedStations.entrySet())
//        {
//            toDisplay +=" [" + entry.getKey() + " distance: " + entry.getValue() + "],";
//        }


        return name;
    }

}
