package ro.teamnet.zth.api.em;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 7/8/2016.
 */
public class EntityManagerImplTest {


    @Test
    /**
     * Integer getNextIdVal(String tableName, String columnIdName)
     */
    public void testGetNextIdVal() {
        EntityManagerImpl emi = new EntityManagerImpl();
        int res = emi.getNextIdVal("EMPLOYEES", "EMPLOYEE_ID");
        assertEquals(207, res);
    }


}
