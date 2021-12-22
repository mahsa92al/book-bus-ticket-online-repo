package service;

import dao.TripDao;
import model.Ticket;

import java.util.Date;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class TripService {
    TripDao tripDao = new TripDao();
    public List<Ticket> getAvailableTripsInfo(String origin, String destination, Date departureDate, int pageSize) {
        List<Ticket> trips = tripDao.findAvailableTripsInfo(origin, destination, departureDate, pageSize);
        return trips;
    }
}
