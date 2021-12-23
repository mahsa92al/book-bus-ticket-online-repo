package service;

import dao.TicketDao;
import model.Passenger;
import model.Ticket;
import model.Trip;

/**
 * @author Mahsa Alikhani m-58
 */
public class TicketService {
    TicketDao ticketDao = new TicketDao();
    public void saveNewTicket(Ticket ticket) {
        ticketDao.saveNewTicket(ticket);
    }
}
