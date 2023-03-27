package GUI_Project.Stuff;

public class Human extends Stuff{
    protected String name;
    protected String surname;

    private Baggage baggage = null;
    private Ticket ticket;


    public Human(double weight, String name, String surname, Ticket ticket) {
        super(weight);
        this.name = name;
        this.surname = surname;
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void addBaggage(Baggage baggage)
    {
        this.baggage = baggage;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", baggage=" + baggage +
                ", weight=" + weight +
                '}';
    }
}
