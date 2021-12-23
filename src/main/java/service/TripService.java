package service;

import dao.TripDao;
import model.Ticket;
import model.Trip;
import model.TripDto;
import model.enumeration.BusType;

import java.util.Date;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class TripService {
    TripDao tripDao = new TripDao();
    public List<TripDto> getAvailableTripsInfo(String origin, String destination, Date departureDate,
                                               String corporationName, BusType busType, Double[] priceRange,
                                               Date[] departureTimeRange, int pageSize) {
        List<TripDto> trips = tripDao.findAvailableTripsInfo(origin, destination, departureDate,
                corporationName, busType, priceRange, departureTimeRange, pageSize);
        return trips;
    }

    public Trip getTripDetailById(int id) {
        Trip trip = tripDao.findTripDetailById(id);
        return trip;
    }
}
