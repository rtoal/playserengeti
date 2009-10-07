package com.playserengeti.service;

import com.playserengeti.dao.LocationDao;
import com.playserengeti.dao.UserDao;
import com.playserengeti.dao.TeamDao;
import com.playserengeti.domain.Location;

/**
 * Service for operations related to location.
 */
public class LocationService {

	private TeamDao teamDao;
	private UserDao userDao;
	private LocationDao locationDao;

    // TODO: private LocationDao LocationDao;

    public LocationService(LocationDao LocationDao) {
        this.locationDao = locationDao;
    }

    public Location getLocationId(Integer locationId) {
        return locationDao.getLocationById(locationId);
    }

    /**
     * Writes the given location to persistent storage. If the given location has a null
     * id, a location is created in persistent storage. If the id is not null, and a
     * location with that id exists, the location in persistent storage will be updated
     * with the fields in the argument. If the id is not null, but no such location
     * with that id exists, then throws some kind of exception. (TODO)
     */
    public void saveLocation(Location location) {
		if (location.getLocationId() == null) {
			locationDao.insertLocation(location);
		} else {
			locationDao.updateLocation(location);
		}
    }

    /**
     * Deletes the location with the given id from persistent storage. If no such
     * location exists, throws an IllegalArgumentException.
     */
    public void deleteLocation(Integer locationId) {
        locationDao.deleteLocation(locationId);
    }
}
