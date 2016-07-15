package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.appl.domain.Location;
import ro.teamnet.zth.appl.service.LocationService;
import ro.teamnet.zth.appl.service.LocationServiceImpl;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
/*
TODO - reparare - gaseste bug
 */
@MyController(urlPath = "/locations")
public class LocationController {
    private final LocationService locationService = new LocationServiceImpl();

    @MyRequestMethod(urlPath = "/all")
    List<Location> getAllLocations() {

        return locationService.findAllLocations();
    }
}

