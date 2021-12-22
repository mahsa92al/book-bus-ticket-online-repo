package dao;

import model.Admin;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class AdminDao extends BaseDao{
    public boolean findAdminByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Admin.class);
        SimpleExpression usernameCond = Restrictions.eq("username", username);
        SimpleExpression passwordCond = Restrictions.eq("password", password);
        Restrictions.and(usernameCond, passwordCond);
        List list = criteria.list();
        transaction.commit();
        session.close();
        if(list != null)
            return true;
        return false;
    }
}
