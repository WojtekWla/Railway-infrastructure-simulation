package GUI_Project.Stuff;

import GUI_Project.Rail.Station;

public class Ticket {
    private Station entry;
    private Station exit;

    public Ticket(Station entry, Station exit)
    {
        this.entry = entry;
        this.exit = exit;
    }

    public Station getEntry() {
        return entry;
    }

    public Station getExit() {
        return exit;
    }
}
