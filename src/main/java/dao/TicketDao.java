package dao;

import model.Passenger;
import model.Ticket;
import model.Trip;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Mahsa Alikhani m-58
 */
public class TicketDao extends BaseDao{
    public void saveNewTicket(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(ticket);
        transaction.commit();
        session.close();
    }
}
