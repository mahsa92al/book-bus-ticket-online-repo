package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Mahsa Alikhani m-58
 */
public class BaseDao {
    SessionFactory sessionFactory = new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
}
