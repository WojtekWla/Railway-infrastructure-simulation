package GUI_Project.Stuff;

public class Material extends Stuff{
    private Materials material;

    public Material(double weight, Materials material) {
        super(weight);
        this.material = material;
    }

    public Materials getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return "Material{" +
                "material=" + material +
                ", weight=" + weight +
                '}';
    }

}
