package ro.teamnet.zth.appl;

import org.junit.Assert;
import org.junit.Test;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 7/11/2016.
 */
public class LocationDao {

    @Test
    public void testFindAllLocations() {
        EntityManagerImpl emi = new EntityManagerImpl();
        List<Location> result = emi.findAll(Location.class);
        assertEquals("Department should have 28 entries.", 23, result.size());

    }


    @Test
    public void testUpdateLocation() {
        EntityManagerImpl emi = new EntityManagerImpl();
        Location d = emi.findById(Location.class, new Long(1000));
        d.setCity("Bucuresti");
        d.setStateProvince("Provincie");
        emi.update(d);
        Assert.assertEquals("updated location_name='Bucuresti'.",
                "Provincie", emi.findById(Location.class, new Long(1000)).getStateProvince());
    }



    @Test
    /**
     * Integer getNextIdVal(String tableName, String columnIdName)
     */
    public void testGetNextIdValLocation() {
        EntityManagerImpl emi = new EntityManagerImpl();
        Long res = emi.getNextIdVal("LOCATIONS", "LOCATION_ID");
        assertEquals((long) 207, (long) res);
    }

    @Test
    public void testInsertLocation() {
        EntityManagerImpl manager = new EntityManagerImpl();
        Location d = new Location();
        d.setCity("BlaBla");
        d.setStateProvince("Provincie");
        d.setStreetAddress("hdgfhg");
        d.setPostalCode("123445");
//        TODO valoarea id-ului din obiectul departament trebuie sa o iei din baza de date pentru ca se schimba la fiecare insert deci o sa pice testul pt ca nu e actuala
        d.setId(278L);

        Location res = (Location) (manager.insert(d));
        assertEquals(d.getId(), res.getId());
    }

    @Test
    public void testFindById() {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Department res = entityManager.findById(Department.class, (long) 10);
        assertEquals("Administration", res.getDepartmentName());
    }

}
