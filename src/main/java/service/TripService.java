package service;

import dao.TripDao;
import model.Ticket;

import java.util.Date;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class TicketService {
    TripDao ticketDao = new TripDao();
    public List<Ticket> getAvailableTicketsInfo(String origin, String destination, Date departureDate, int pageSize) {
        List<Ticket> ticketsInfo = ticketDao.findAvailableTicketInfo(origin, destination, departureDate, pageSize);
        return null;
    }
}
