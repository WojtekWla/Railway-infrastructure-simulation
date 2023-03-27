package GUI_Project.Stuff;

public class Stuff {
    public static enum Materials{
        liquidMaterial,
        gaseousMaterial,
        explosives,
        toxicMaterial,
    }

    protected double weight;

    public Stuff(double weight)
    {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " weight: " + weight;
    }
}
