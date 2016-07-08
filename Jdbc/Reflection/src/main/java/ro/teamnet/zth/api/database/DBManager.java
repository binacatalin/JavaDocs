package ro.teamnet.zth.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by user on 7/8/2016.
 * -	create a private constructor with no params which will throw UnsupportedOperationException();
 * -	create a constant field CONNECTION_STRING= "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT;",
 * where IP and PORT will be taken from  DProperties file.
 * <p>
 * -
 * -	create a public static method getConnection() with the following properties:
 * •	method will return an object of type Connection;
 * •	first you’ll have to register your driver;
 * •	in order to obtain the connection object use DriverManager.getConnection with params: CONNECTION_STRING, USER and PASS from DBProperties file;
 * <p>
 * -	create a public static method checkConnection(Connection connection)
 * in which you’ll make a simple query to DB (SELECT 1 FROM DUAL) using Statement interface.
 */
public class DBManager {
    private static final String CONNECTION_STRING = "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT;
    ;

    private DBManager() {
        throw new UnsupportedOperationException();
    }

    /**
     * create a  private static method registerDriver() for registering your driver. (Ex: Class.forName(DBProperties.DRIVER_CLASS) )
     */
    private static void registerDriver() {
        try {
            Class.forName(DBProperties.DRIVER_CLASS);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error from registerDriver: unable to load driver class!");
            System.exit(1);
        }
    }

    /**
     * create a public static method getConnection() with the following properties:
     * •	method will return an object of type Connection;
     * •	first you’ll have to register your driver;
     * •	in order to obtain the connection object use DriverManager.getConnection with params: CONNECTION_STRING, USER and PASS from DBProperties file;
     *
     * @return
     */
    public static Connection getConnection() {
        registerDriver();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DBManager.CONNECTION_STRING, DBProperties.USER, DBProperties.PASS);
        } catch (SQLException e) {
            System.out.println("Exception from getConnection");
            e.printStackTrace();
        }

        return connection;
    }


    /**
     * create a public static method checkConnection(Connection connection)
     * in which you’ll make a simple query to DB (SELECT 1 FROM DUAL) using Statement interface.
     *
     * @param connection
     */
    public static boolean checkConnection(Connection connection) {
        boolean ret = false;

        if (connection == null)
            return ret;

        try (Statement stmt = connection.createStatement()) {
            ret = stmt.execute("SELECT 1 FROM DUAL");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
