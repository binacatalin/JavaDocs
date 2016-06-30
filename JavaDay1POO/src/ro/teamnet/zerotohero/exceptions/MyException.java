package ro.teamnet.zerotohero.exceptions;

/**
 * Created by user on 6/30/2016.
 */
public class MyException extends Exception {
    public String details = "Some details :D";
    private static int len = 100;
    private static int[] arr = new int[10];

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws MyException {



        if (len > 10) {
            try {
                propagException1();
            } catch (MyException e1) {
                System.out.println("Hello from catch");
            } finally {
                System.out.println("Hello from finally");
            }
        }
    }

    public static void propagException1() throws MyException {
        propagException2();
    }


    public static void propagException2() throws MyException {
        if (len > 10) {
            throw new MyException();
        }
    }


}
