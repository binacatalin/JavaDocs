package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by user on 6/30/2016.
 * 2.	Create a new class named Shape with a public area method,  that returns a double value,
 * and one  primitive data field of type int, named color, and one primitive of type float, named saturation.
 * Give the area method a default implementation. For color and saturation create setter methods.
 */
public class Shape extends AbstractShape implements ShapeBehaviour {

    private  int color;
    private float saturation;

    public int getColor() {
        return color;
    }

    public float getSaturation() {
        return saturation;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }


    @Override
    public double area() {
        return 0;
    }
}
