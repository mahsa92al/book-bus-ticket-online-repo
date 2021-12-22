package dao;

import model.Ticket;
import model.Trip;
import model.TripDto;
import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import java.util.Date;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class TripDao extends BaseDao{
    private static final int pageIndex = 0;
    public List<Ticket> findAvailableTripsInfo(String origin, String destination, Date departureDate, int pageSize) {
        //int totalRecords;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ScrollableResults scrollableResults = getCriteria(origin, destination, departureDate, session).scroll();
        scrollableResults.last();
        //totalRecords = scrollableResults.getRowNumber() + 1;
        scrollableResults.close();
        Criteria criteria = getCriteria(origin, destination, departureDate, session);
        criteria.setFirstResult(pageIndex);
        criteria.setMaxResults(pageSize);
        List trips = criteria.list();
        transaction.commit();
        session.close();
        return trips;
    }

    private Criteria getCriteria(String origin, String destination, Date departureDate, Session session) {
        Criteria criteria = session.createCriteria(Trip.class, "t");
        criteria.createAlias("t.buses", "tb");
        if(departureDate != null){
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.eq("t.departureDate", departureDate)));
        }else {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination)));
        }
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("t.corporationName").as("corporationName"))
                .add(Projections.property("tb.busType").as("busType"))
                .add(Projections.property("t.departureTime").as("departureTime"))
                .add(Projections.property("t.price").as("price"))
                .add(Projections.property("t.remainingSeats").as("remainingSeats")));
        criteria.setResultTransformer(Transformers.aliasToBean(TripDto.class));
        return criteria;
    }
}
