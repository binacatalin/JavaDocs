package ro.teamnet.zerotohero.canvas;

import ro.teamnet.zerotohero.oop.graphicshape.Circle;

/**
 * Created by user on 6/30/2016.
 * 19.	Create a canvas package in in ro.teamnet.zerotohero and in int create a Canvas class.
 * In this class create a method getArea that return the default Circle area. The getArea method should have the default access modifier.
 * From  the main method of the RunApp class create a new Canvas object and try to call the getArea method. Observe that the compiler won’t let you do so.
 */
public class Canvas {

    double getArea() {
        Circle circle  = new Circle();
        return circle.area();
    }
}
