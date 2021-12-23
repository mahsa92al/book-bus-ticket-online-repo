package dao;

import model.Bus;
import model.BusDto;
import model.enumeration.BusType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class BusDao extends BaseDao{
    public List<BusDto> findBusReportsByType(BusType busType) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Bus.class, "b");
        criteria.createAlias("b.trip", "bt");
        criteria.add(Restrictions.eq("bt.busType", busType));
        criteria.addOrder(Order.asc("bt.departureDate"));
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("b.busPlate").as("busPlate"))
                .add(Projections.property("bt.departureDate").as("departureDate"))
                .add(Projections.property("bt.remainingSeats").as("remainingSeats")));
        criteria.setResultTransformer(Transformers.aliasToBean(BusDto.class));
        List<BusDto> reports = criteria.list();
        transaction.commit();
        session.close();
        return reports;
    }
}
