package GUI_Project.Stuff;

public class Baggage extends Stuff{
    private Human owner;

    public Baggage(double weight, Human owner) {
        super(weight);
        this.owner = owner;
    }
}
