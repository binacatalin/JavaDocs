package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by user on 7/8/2016.
 */
public class EntityManagerImplTest {

    @Test
    public void testFindAll() {
        EntityManagerImpl emi = new EntityManagerImpl();
        List<Department> result = emi.findAll(Department.class);
        assertEquals("Department should have 28 entries.", 28, result.size());

    }

    @Test
    /**
     * Integer getNextIdVal(String tableName, String columnIdName)
     */
    public void testGetNextIdVal() {
        EntityManagerImpl emi = new EntityManagerImpl();
        Long res = emi.getNextIdVal("EMPLOYEES", "EMPLOYEE_ID");
        assertEquals((long) 207, (long) res);
    }

    @Test
    public void testInsert() {
        EntityManagerImpl manager = new EntityManagerImpl();
        Department d = new Department();
        d.setDepartmentName("BlaBla2");
        d.setLocation(1000l);
        d.setId(279L);

        Department res = (Department) (manager.insert(d));
        assertEquals(d, res);
    }

    @Test
    public void testFindById() {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Department res = entityManager.findById(Department.class, (long) 10);
        assertEquals("Administration", res.getDepartmentName());
    }

    @Test
    public void testDelete() {
        EntityManagerImpl manager = new EntityManagerImpl();
        Department d = new Department();
        d.setId(278L);
        manager.delete(d);

        Department d1 = manager.findById(Department.class, d.getId());
        assertNull(d1.getId());
    }

}
