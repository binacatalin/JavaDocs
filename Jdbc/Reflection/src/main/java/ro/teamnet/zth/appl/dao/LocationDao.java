package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

/**
 * Created by user on 7/11/2016.
 */
public class LocationDao {
    private EntityManager entityManager;

    public LocationDao() {
        this.entityManager = new EntityManagerImpl();
    }

    public List<Location> findAllLocations() {
        return this.entityManager.findAll(Location.class);
    }

    public Location findLocationById(Long id) {
        return entityManager.findById(Location.class, id);
    }

    public Location insertLocation(Location location) {
        return (Location)entityManager.insert(location);
    }

    public Location update(Location location) {
        return entityManager.update(location);
    }

    public void deleteLocation(Location location) {
        entityManager.delete(location);
    }
}
