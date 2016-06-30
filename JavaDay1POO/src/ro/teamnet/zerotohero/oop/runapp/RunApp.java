package ro.teamnet.zerotohero.oop.runapp;

import ro.teamnet.zerotohero.oop.graphicshape.*;

/**
 * Created by user on 6/30/2016.
 */
public class RunApp {
    public static void main(String[] args) {
        Circles circles = new Circles();

        System.out.println("Area is: " + circles.getAreaPub());

        circles.getAreaDef();

        // ..............................................
        Shape shape = new Circle(10);
        System.out.println("Area of shape is:" + shape.area());

        // ..............................................
        ShapeBehaviour shapeBehaviour = new Square(10);
        System.out.println("Area of shape is:" + shape.area());

//        ..............................................
        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);

        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));

        ImmutablePerson immutablePerson = new ImmutablePerson(20, "Gogu");
    }
}
