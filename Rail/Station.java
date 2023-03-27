package GUI_Project.Rail;

import GUI_Project.Stuff.Human;

import java.util.ArrayList;
import java.util.List;

public class Station {
    private String name;
    private List<Human> peopleWaitingForTrain;

    public Station(String name)
    {
        this.name = name;
        peopleWaitingForTrain = new ArrayList<>();
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
}
