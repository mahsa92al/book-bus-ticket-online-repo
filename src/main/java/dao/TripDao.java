package dao;

import model.Ticket;
import model.Trip;
import model.TripDto;
import model.enumeration.BusType;
import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import javax.management.Query;
import java.util.Date;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class TripDao extends BaseDao {
    private static final int pageIndex = 0;

    public List<TripDto> findAvailableTripsInfo(String origin, String destination, Date departureDate,
                                                String corporationName, BusType busType, Double[] priceRange,
                                                Date[] departureTimeRange, int pageSize) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = getCriteria(origin, destination, departureDate, corporationName, busType,
                priceRange, departureTimeRange, session);
        criteria.setFirstResult(pageIndex);
        criteria.setMaxResults(pageSize);
        List<TripDto> trips = criteria.list();
        transaction.commit();
        session.close();
        return trips;
    }

    private Criteria getCriteria(String origin, String destination, Date departureDate,
                                 String corporationName, BusType busType, Double[] priceRange,
                                 Date[] departureTimeRange, Session session) {
        Criteria criteria = session.createCriteria(Trip.class, "t");
        if (departureDate == null && corporationName == null && busType == null
                && priceRange.length == 0 && departureTimeRange.length == 0) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination)));
        }
        if (departureDate != null && corporationName == null && busType == null
                && priceRange.length == 0 && departureTimeRange.length == 0) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.eq("t.departureDate", departureDate)));
        }
        if (departureDate == null && corporationName != null && busType == null
                && priceRange.length == 0 && departureTimeRange.length == 0) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.eq("t.corporationName", corporationName)));
        }
        if (departureDate == null && corporationName == null && busType != null
                && priceRange.length == 0 && departureTimeRange.length == 0) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.eq("t.busType", busType)));
        }
        if (departureDate == null && corporationName == null && busType == null
                && priceRange.length == 2 && departureTimeRange.length == 0) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.between("t.price", priceRange[0], priceRange[1])));
        }
        if (departureDate == null && corporationName == null && busType == null
                && priceRange.length == 0 && departureTimeRange.length == 2) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.between("t.departureTime", departureTimeRange[0], departureTimeRange[1])));
        }
        if (departureDate != null && corporationName != null && busType == null
                && priceRange.length == 0 && departureTimeRange.length == 0) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.eq("t.departureDate", departureDate),
                    Restrictions.eq("t.corporationName", corporationName)));
        }
        if (departureDate != null && corporationName == null && busType != null
                && priceRange.length == 0 && departureTimeRange.length == 0) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.eq("t.departureDate", departureDate),
                    Restrictions.eq("t.busType", busType)));
        }
        if (departureDate != null && corporationName == null && busType == null
                && priceRange.length == 2 && departureTimeRange.length == 0) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.eq("t.departureDate", departureDate),
                    Restrictions.between("t.price", priceRange[0], priceRange[1])));
        }
        if (departureDate != null && corporationName == null && busType == null
                && priceRange.length == 0 && departureTimeRange.length == 2) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.eq("t.departureDate", departureDate),
                    Restrictions.between("t.departureTime", departureTimeRange[0], departureTimeRange[1])));
        }
        if (departureDate == null && corporationName != null && busType != null
                && priceRange.length == 0 && departureTimeRange.length == 0) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.eq("t.corporationName", corporationName),
                    Restrictions.eq("t.busType", busType)));
        }
        if (departureDate == null && corporationName != null && busType == null
                && priceRange.length == 2 && departureTimeRange.length == 0) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.eq("t.corporationName", corporationName),
                    Restrictions.between("t.price", priceRange[0], priceRange[1])));
        }
        if (departureDate == null && corporationName != null && busType == null
                && priceRange.length == 0 && departureTimeRange.length == 2) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.eq("t.corporationName", corporationName),
                    Restrictions.between("t.departureTime", departureTimeRange[0], departureTimeRange[1])));
        }
        if (departureDate == null && corporationName == null && busType != null
                && priceRange.length == 2 && departureTimeRange.length == 0) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.eq("t.busType", busType),
                    Restrictions.between("t.price", priceRange[0], priceRange[1])));
        }
        if (departureDate == null && corporationName == null && busType != null
                && priceRange.length == 0 && departureTimeRange.length == 2) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.eq("t.busType", busType),
                    Restrictions.between("t.departureTime", departureTimeRange[0], departureTimeRange[1])));
        }
        if (departureDate == null && corporationName == null && busType == null
                && priceRange.length == 2 && departureTimeRange.length == 2) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.between("t.price", priceRange[0], priceRange[1]),
                    Restrictions.between("t.departureTime", departureTimeRange[0], departureTimeRange[1])));
        }
        if (departureDate != null && corporationName != null && busType != null
                && priceRange.length == 0 && departureTimeRange.length == 0) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.eq("t.departureDate", departureDate),
                    Restrictions.eq("t.corporationName", corporationName),
                    Restrictions.eq("t.busType", busType)));
        }
        //for 3 and 4 are remaining:/

        if (departureDate != null && corporationName != null && busType != null
                && priceRange.length == 2 && departureTimeRange.length == 2) {
            criteria.add(Restrictions.and(Restrictions.eq("t.origin", origin),
                    Restrictions.eq("t.destination", destination),
                    Restrictions.eq("t.departureDate", departureDate),
                    Restrictions.eq("t.corporationName", corporationName),
                    Restrictions.eq("t.busType", busType),
                    Restrictions.between("t.price", priceRange[0], priceRange[1]),
                    Restrictions.between("t.departureTime", departureTimeRange[0], departureTimeRange[1])));
        }
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("t.id").as("code"))
                .add(Projections.property("t.corporationName").as("corporationName"))
                .add(Projections.property("t.busType").as("busType"))
                .add(Projections.property("t.departureTime").as("departureTime"))
                .add(Projections.property("t.price").as("price"))
                .add(Projections.property("t.remainingSeats").as("remainingSeats")));
        criteria.setResultTransformer(Transformers.aliasToBean(TripDto.class));
        return criteria;
    }

    public Trip findTripDetailById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Trip.class);
        List result = criteria.add(Restrictions.eq("id", id)).list();
        transaction.commit();
        session.close();
        return (Trip) result.get(0);
    }

    public void updateRemainingSeats(Trip trip, int remainingSeats) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        trip.setRemainingSeats(remainingSeats);
        session.saveOrUpdate(trip);
        transaction.commit();
        session.close();
    }
}
