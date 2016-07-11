package ro.teamnet.zth.api.database;

/**
 * Created by user on 7/8/2016.
 * IP, PORT, SID, USER, PASS.
 * The value for this fields are:
 * String IP = "192.168.99.100";
 * String PORT = "49161";
 * String USER = "sys as sysdba"; // user from SQL workshop
 * String PASS = "oracle"; // pass from SQL workshop
 * String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
 */
public interface DBProperties {
    String IP = "192.168.99.100";
    String PORT = "49161";
    String USER = "JeniRotaru"; // user from SQL workshop
    String PASS = "JeniRotaru"; // pass from SQL workshop
    String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
}
