package ma.projet.services;

import ma.projet.classes.EmployeTache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class EmployeTacheService implements IDao<EmployeTache> {

    @Override
    public EmployeTache save(EmployeTache et) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(et);
        session.getTransaction().commit();
        session.close();
        return et;
    }

    @Override
    public EmployeTache update(EmployeTache et) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(et);
        session.getTransaction().commit();
        session.close();
        return et;
    }

    @Override
    public void delete(EmployeTache et) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(et);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public EmployeTache findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        EmployeTache et = session.get(EmployeTache.class, id);
        session.close();
        return et;
    }

    @Override
    public List<EmployeTache> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<EmployeTache> employetaches = session.createQuery("from EmployeTache", EmployeTache.class).list();
        session.close();
        return employetaches;
    }

}
