package GUI_Project.Rail;

import GUI_Project.TrainSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Connection {
    public static HashSet<Connection> connections = new HashSet<>();
    //public static String monitor = new String();

    private final Station station1;
    private final Station station2;
    private boolean isUsed;
    private TrainSet waitingTrain;
    private int waitingTrainsNum = 0;

    public Connection(Station station1, Station station2)
    {
        this.station1 = station1;
        this.station2 = station2;
        isUsed = false;
    }

    public Station getStation1() {
        return station1;
    }

    public Station getStation2() {
        return station2;
    }

    public  boolean isUsed() {
        //synchronized (this) {
            return isUsed;
        //}
    }

    public int getWaitingTrainsNum() {
        return waitingTrainsNum;
    }

    public void setWaitingTrainsNum(int waitingTrainsNum) {
        this.waitingTrainsNum = waitingTrainsNum;
    }

    public void setUsedToFalse() {
        System.out.println("Enter setUsedFalse");
        synchronized (this) {
            System.out.println("Entering synchronized block");
            isUsed = false;
        if(waitingTrain!= null) {
            System.out.println("Unlocking waiting train");
            try {
                System.out.println(waitingTrain.getState());
                this.notify();
            }catch(Exception e)
            {
                System.out.println(e.getMessage() + "  " + e.getCause());
                System.out.println(waitingTrain.getState());
            }
            waitingTrain = null;
        }
//            if(waitingTrainsNum > 0) {
//                currentRunning.notifyAll();
//            }

        }

        System.out.println("Continue");
    }

    synchronized public void setUsedToTrue()
    {
        synchronized (this)
        {

            isUsed = true;
        }
    }

    public void setCurrentWaiting(TrainSet currentWaiting) {
        this.waitingTrain = currentWaiting;
    }

    //    public TrainSet getWaitingTrain() {
//        return waitingTrain;
//    }
//
//    public void setWaitingTrain(TrainSet waitingTrain) {
//        this.waitingTrain = waitingTrain;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return (station1.equals(that.station1) && station2.equals(that.station2))
                || (station1.equals(station2) && station2.equals(station1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(station1, station2);
    }

    @Override
    public String toString() {
        return "Connection{" +
                "station1=" + station1 +
                ", station2=" + station2 +
                '}';
    }
}
