package GUI_Project;

import GUI_Project.Rail.Station;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class MapGenerator {
    public static int change = 0;

    public static void mapGenerator(){
        for (int i = 0; i < Station.stations.size(); i++) {
            generateMap(Station.stations.get(i), new Position(50,50));
        }
    }

    public static void generateMap(Station station, Position currPos){
        Station[][] map = Station.stationsOnMap;
//        ArrayList<Station> stations = Station.stations;
        Random random = new Random();

        if(!station.isDrawOnMap()){
            if(map[currPos.x][currPos.y] != null){
                int x = 0;
                int y = 0;
                while(true){
                    x = random.nextInt(100);
                    y = random.nextInt(100);
                    if(map[x][y] == null) {
                        currPos.x = x;
                        currPos.y = y;
                        break;
                    }
                }
            }
            map[currPos.x][currPos.y] = station;
            station.setDrawOnMap(true);
        }


         for(Map.Entry<Station, Integer> entry : station.getConnectedStations().entrySet()){
             if(!entry.getKey().isDrawOnMap()) {
                 int distance = entry.getValue() / 100 + 3;
                 boolean ver = random.nextBoolean(); // true - go up false - go down
                 boolean hor = random.nextBoolean(); // true - go left false - go right
//                 boolean ver = random.nextBoolean(); // true - go up false - go down
//                 boolean hor = random.nextBoolean(); // true - go left false - go right
//                 int dis_ver = 0;
//                 int dis_hor = 0;
//
//                 while(true){
//                     dis_ver = random.nextInt(distance);
//                     dis_hor = random.nextInt(distance);
//                     boolean change_1 = false;
//                     boolean change_2 = false;
//
//
//                     if((ver && currPos.x - dis_ver < 0) || (!ver && currPos.x + dis_ver > map.length-1)){
//
//                     }else
//                         change_1 = true;
//
//                     if((hor && currPos.y - dis_hor < 0) || (!hor && currPos.y + dis_hor > map[0].length-1)){
//
//                     }else
//                         change_2 = true;
//
//                     if(map[currPos.x+dis_ver][currPos.y+dis_hor] == null && change_1 && change_2) // check whether new place is empty
//                        break;
//                 }


                 Position vec = Position.generateNewPosition(currPos, distance,ver,hor);


                 if (currPos.x != 0 && ver) {
                     currPos.x -= vec.x ;
                 }
                 if (currPos.x != map.length - 1 && !ver) {
                    currPos.x += vec.x;
                 }
                 if (currPos.y != 0 && hor ) {
                    currPos.y -= vec.y;
                 }
                 if (currPos.y != map[0].length - 1 && !hor ) {
                    currPos.y += vec.y;
                 }

                 map[currPos.x][currPos.y] = entry.getKey();
                 entry.getKey().setDrawOnMap(true);
                 change++;

                 if(stationToDraw(entry.getKey()) != 0) {
                     generateMap(entry.getKey(), currPos);
                 }else
                     return;
             }/*else if(stationToDraw(entry.getKey()) != 0){
                 generateMap(entry.getKey(), currPos);
             }*/
         }

    }

    static int stationToDraw(Station station){
        int count = 0;
        for(Map.Entry<Station, Integer> entry : station.getConnectedStations().entrySet()){
            if(!entry.getKey().isDrawOnMap()){
                count++;
            }
        }

        return count;
    }
//
//    static int generateX()



    public static class Position{
        public int x;
        public int y;

        public Position(){

        }

        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }

        public static Position generateNewPosition(Position currPos, int distance, boolean ver, boolean hor) {

            Position p = new Position();
            Random random = new Random();
            int dis_ver = 0;
            int dis_hor = 0;

            while (true) {
                dis_ver = random.nextInt(distance);
                dis_hor = random.nextInt(distance);
                boolean change_1 = false;
                boolean change_2 = false;


                if ((ver && currPos.x - dis_ver < 0) || (!ver && currPos.x + dis_ver > Station.stationsOnMap.length - 1)) {

                } else
                    change_1 = true;

                if ((hor && currPos.y - dis_hor < 0) || (!hor && currPos.y + dis_hor > Station.stationsOnMap[0].length - 1)) {

                } else
                    change_2 = true;

                if (Station.stationsOnMap[currPos.x + dis_ver][currPos.y + dis_hor] == null && change_1 && change_2) // check whether new place is empty
                    break;
            }

            p.x = dis_ver;
            p.y = dis_hor;

            return p;

        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }


}
