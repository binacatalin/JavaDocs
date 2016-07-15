package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.LocationDao;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
public class LocationServiceImpl implements LocationService {
    @Override
    public List<Location> findAllLocations() {
        LocationDao locationDao = new LocationDao();

        return locationDao.getAllLocations();
    }
}
