package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.LocationDao;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
public class LocationServiceImpl implements LocationService {
    private final LocationDao locationDao = new LocationDao();

    @Override
    public List<Location> findAllLocations() {
        return locationDao.getAllLocations();
    }

    @Override
    public Location findOneLocation(Long id) {
        return locationDao.getLocationById(id);
    }

    @Override
    public void deleteOneLocation(Long id) {
        locationDao.getLocationById(id);
    }

    @Override
    public Location saveLocation(Location location) {
        return locationDao.insertLocation(location);
    }
}
