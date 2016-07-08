package ro.teamnet.zth.api.database;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by user on 7/8/2016.
 */
public class DBManagerTest {

    @Test
    public void testGetConnection() {
        Connection connection = DBManager.getConnection();

        assertTrue("Null connection", connection!=null);
    }

    @Test
    public void testCheckConnection() {
        Connection connection = DBManager.getConnection();
        boolean ck = DBManager.checkConnection(connection);

        assertTrue("check connection faild", ck);
    }
}
