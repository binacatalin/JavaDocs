package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by user on 6/30/2016.
 * 12.	Create a new class named Circles with a method getAreaPub with double return type.
 * In this method create a new Circle object and  return its area (call to public method area(), within package).
 *

 */
public class Circles {

    public double getAreaPub() {
        Circle circle = new Circle();
        return circle.area();
    }

    //* 17.	In class Circles create a method named getAreaDef with void return type.
    // In this method create a new Circle object and call the three fillColour methods.
    public void getAreaDef() {
        Circle circle = new Circle();
        circle.fillColour();
        circle.fillColour(111);
        circle.fillColour(2.22f);
    }
}
