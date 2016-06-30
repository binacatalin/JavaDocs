package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by user on 6/30/2016.
 * Create a new class Square that extends the Shape class with a private int primitive named side and
 * a constructor that takes one parameter which initializes the side variable.
 * Overwrite the area method so that it calculates the square area.
 */
public class Square extends Shape {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public double area() {
        return side * side;
    }
}
