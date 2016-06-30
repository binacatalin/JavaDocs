package ro.teamnet.zerotohero.oop.gc;

/**
 * Created by user on 6/30/2016.
 */
public class DemoObject {

    private static final int bufferSize = 100000;
    private static int count = 0;
    private String temp;
    private String objectRef;

    public DemoObject() {
        this.objectRef = this.toString();
    }
}
