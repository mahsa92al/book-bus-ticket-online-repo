package dao;

import model.Passenger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Mahsa Alikhani m-58
 */
public class PassengerDao extends BaseDao{
    public void saveNewPassenger(Passenger passenger) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(passenger);
        transaction.commit();
        session.close();
    }
}
