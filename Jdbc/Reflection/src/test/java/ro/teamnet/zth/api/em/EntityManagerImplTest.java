package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

import static org.junit.Assert.assertEquals;

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
        d.setDepartmentName("BlaBla");
        d.setLocation(1000l);
//        TODO valoarea id-ului din obiectul departament trebuie sa o iei din baza de date pentru ca se schimba la fiecare insert deci o sa pice testul pt ca nu e actuala
        d.setId(274L);

        Department res = (Department) (manager.insert(d));
        assertEquals(d.getId(), res.getId());
    }

    @Test
    public void testFindById() {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Department res = entityManager.findById(Department.class, (long) 10);
        assertEquals("Administration", res.getDepartmentName());
    }

}
