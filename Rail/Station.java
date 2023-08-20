package GUI_Project.Rail;

import GUI_Project.Stuff.Human;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Station{
    public static Color[] colors = {
            Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK,
            new Color(255, 165, 0), new Color(255, 192, 203), new Color(0, 128, 0), new Color(139, 0, 139),
            new Color(255, 140, 0), new Color(128, 0, 0), new Color(0, 0, 128), new Color(128, 0, 128),
            new Color(0, 128, 128), new Color(0, 255, 0), new Color(128, 128, 0), new Color(255, 0, 0),
            new Color(255, 215, 0), new Color(30, 144, 255), new Color(139, 69, 19), new Color(220, 20, 60),
            new Color(255, 0, 255), new Color(0, 255, 255), new Color(255, 99, 71), new Color(128, 0, 0),
            new Color(0, 0, 139), new Color(218, 165, 32), new Color(0, 100, 0), new Color(95, 158, 160),
            new Color(210, 105, 30), new Color(139, 69, 19), new Color(178, 34, 34), new Color(107, 142, 35),
            new Color(188, 143, 143), new Color(85, 107, 47), new Color(255, 215, 0), new Color(0, 255, 127),
            new Color(255, 69, 0), new Color(0, 139, 139), new Color(255, 255, 0), new Color(0, 100, 0),
            new Color(218, 165, 32), new Color(0, 128, 128), new Color(95, 158, 160), new Color(184, 134, 11),
            new Color(0, 0, 205), new Color(107, 142, 35), new Color(188, 143, 143), new Color(85, 107, 47),
            new Color(255, 215, 0), new Color(0, 255, 127), new Color(255, 69, 0), new Color(0, 139, 139),
            new Color(255, 255, 0), new Color(0, 100, 0), new Color(218, 165, 32), new Color(0, 128, 128),
            new Color(95, 158, 160), new Color(184, 134, 11), new Color(0, 0, 205), new Color(107, 142, 35),
            new Color(188, 143, 143), new Color(85, 107, 47), new Color(255, 215, 0), new Color(0, 255, 127),
            new Color(255, 69, 0), new Color(0, 139, 139), new Color(255, 255, 0), new Color(0, 100, 0),
            new Color(218, 165, 32), new Color(0, 128, 128), new Color(95, 158, 160), new Color(184, 134, 11),
            new Color(0, 0, 205), new Color(107, 142, 35), new Color(188, 143, 143), new Color(85, 107, 47),
            new Color(255, 215, 0), new Color(0, 255, 127), new Color(255, 69, 0), new Color(0, 139, 139),
            new Color(255, 255, 0), new Color(0, 100, 0), new Color(218, 165, 32), new Color(0, 128, 128),
            new Color(95, 158, 160), new Color(184, 134, 11), new Color(0, 0, 205), new Color(107, 142, 35),
            new Color(188, 143, 143), new Color(85, 107, 47), new Color(255, 215, 0), new Color(0, 255, 127),
            new Color(255, 69, 0), new Color(0, 139, 139), new Color(255, 255, 0), new Color(0, 100, 0),
            new Color(218, 165, 32), new Color(0, 128, 128), new Color(95, 158, 160), new Color(184, 134, 11),
            new Color(0, 0, 205), new Color(107, 142, 35), new Color(188, 143, 143), new Color(85, 107, 47),
            new Color(255, 215, 0), new Color(0, 255, 127), new Color(255, 69, 0), new Color(0, 139, 139),
            new Color(255, 255, 0), new Color(0, 100, 0), new Color(218, 165, 32), new Color(0, 128, 128),
            new Color(95, 158, 160), new Color(184, 134, 11), new Color(0, 0, 205), new Color(107, 142, 35)};

    public static int currColor = 0;
    public static ArrayList<Station> stations = new ArrayList<>();
    public static Station[][] stationsOnMap = new Station[100][100];

    private String name;
    private List<Human> peopleWaitingForTrain;
    private Map<Station, Integer> connectedStations;
    private int maxStationConnections;
    private boolean drawOnMap;
    private Color color;



    public Station(String name)
    {
        this.name = name;
        peopleWaitingForTrain = new ArrayList<>();
        connectedStations = new HashMap<>();
        maxStationConnections = 2; //new Random().nextInt(3)+3;
        drawOnMap = false;
        color = colors[currColor++];
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

    public Color getColor() {
        return color;
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

    public boolean isDrawOnMap() {
        return drawOnMap;
    }

    public void setDrawOnMap(boolean drawOnMap) {
        this.drawOnMap = drawOnMap;
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

        System.out.println("End of connecting roads");

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
        String[] arr = name.split(" ");
        String res = "";
        for (int i = 0; i < arr.length; i++) {
            res += arr[i].toCharArray()[0];
        }


        return res;
    }

}
