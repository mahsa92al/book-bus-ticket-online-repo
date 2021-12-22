package dao;

import model.Ticket;
import model.TicketDto;
import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.transform.Transformers;

import java.io.Reader;
import java.util.Date;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class TicketDao extends BaseDao{
    private static final int pageIndex = 0;
    public List<Ticket> findAvailableTicketInfo(String origin, String destination, Date departureDate, int pageSize) {
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
        List tickets = criteria.list();
        transaction.commit();
        session.close();
        return tickets;
    }

    private Criteria getCriteria(String origin, String destination, Date departureDate, Session session) {
        Criteria criteria = session.createCriteria(Ticket.class, "t");
        criteria.createAlias("t.bus", "tb");
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
                .add(Projections.property("tb.capacity").as("capacity")));
        criteria.setResultTransformer(Transformers.aliasToBean(TicketDto.class));
        return criteria;
    }
}
