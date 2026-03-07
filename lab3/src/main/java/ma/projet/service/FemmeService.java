package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.*;

public class FemmeService implements IDao<Femme> {

    @Override
    public boolean create(Femme o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(o);
        tx.commit();
        s.close();
        return true;
    }

    @Override
    public List<Femme> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Femme> list = s.createQuery("from Femme").list();
        s.close();
        return list;
    }

    // Requête native
    public int countEnfants(int femmeId, Date d1, Date d2) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Object result = s.createNamedQuery("Femme.countEnfantsBetweenDates")
                .setParameter(1, femmeId)
                .setParameter(2, d1)
                .setParameter(3, d2)
                .getSingleResult();
        s.close();
        return result == null ? 0 : ((Number) result).intValue();
    }

    public List<Femme> femmesMarieesDeuxFois() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Femme> list = s.createNamedQuery("Femme.findMarriedTwice", Femme.class).list();
        s.close();
        return list;
    }

    @Override public boolean update(Femme o){return false;}
    @Override public boolean delete(Femme o){return false;}
    @Override public Femme findById(int id){return null;}
}
