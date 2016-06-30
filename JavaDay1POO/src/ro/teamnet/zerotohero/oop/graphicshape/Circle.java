package ro.teamnet.zerotohero.oop.graphicshape;

import java.lang.Math;

/**
 * Created by user on 6/30/2016.
 * 5.	Create a new class Circle, in ro.teamnet.zerotohero.oop.graphicshape package.
 * In this class create three private data fields of type int: xPos, yPos and radius,
 * and a constructor with no parameters in which the three primitives are initializes with values (of you choice).
 * ---------------------------------------------------------------------------------------------------------------
 * <p>
 * 16.	In the Circle class create an overloaded method named fillColour. One method should have no parameters,
 * one with an int parameter and one with a float parameter. The fillColour method with no parameters should print the super classes color primitive variable.
 * The fillColour method with an int parameter should set the super classes color primitive variable and print a message after.
 * The message should be like "The circle color is now 2". The fillColour method with a float parameter should set the superClass saturation parameter.
 * ---------------------------------------------------------------------------------------------------------------
 */
public class Circle extends Shape {
    private int xPos, yPos, radius;

    public Circle() {
        this.xPos = 0;
        this.yPos = 0;
        this.radius = 1;
    }

    public Circle(int radius) {
        this.radius = radius;
    }

    public Circle(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Circle(int xPos, int yPos, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public void fillColour() {
        System.out.println("Color from superclass" + super.getColor());
    }

    //     * The fillColour method with an int parameter should set the super classes color primitive variable and print a message after.
    public void fillColour(int color) {
        super.setColor(color);
        System.out.println("Color from superclass" + super.getColor());
    }

    //    The fillColour method with a float parameter should set the superClass saturation parameter.
    public void fillColour(float saturation) {
        super.setSaturation(saturation);
        System.out.println("Saturation from superclass" + super.getSaturation());
    }

    @Override
    public String toString() {
        return "center=(" + xPos + "," + yPos + ") and radius=" + radius;
    }
}
