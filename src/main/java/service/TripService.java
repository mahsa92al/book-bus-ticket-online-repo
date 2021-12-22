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
    public List<Ticket> getAvailableTicketsInfo(String origin, String destination, Date departureDate, int pageSize) {
        List<Ticket> trips = tripDao.findAvailableTicketInfo(origin, destination, departureDate, pageSize);
        return trips;
    }
}
