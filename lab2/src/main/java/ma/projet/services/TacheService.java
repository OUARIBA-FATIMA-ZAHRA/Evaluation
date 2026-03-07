package ma.projet.services;

import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;


import java.util.List;

public class TacheService implements IDao<Tache> {

    @Override
    public Tache save(Tache t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(t);
        session.getTransaction().commit();
        session.close();
        return t;
    }

    @Override
    public Tache update(Tache t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();
        session.close();
        return t;
    }

    @Override
    public void delete(Tache t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(t);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Tache findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Tache t = session.get(Tache.class, id);
        session.close();
        return t;
    }

    @Override
    public List<Tache> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Tache> taches = session.createQuery("from Tache", Tache.class).list();
        session.close();
        return taches;
    }


    public List<Tache> findPrixSup(double prix) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Tache> taches =
                session.createNamedQuery(
                                "Tache.findPriceHigherThan",
                                Tache.class)
                        .setParameter("price", prix)
                        .list();

        session.close();
        return taches;
    }
    public List<Tache> findBetweenDates(Date d1, Date d2){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Tache> query =
                session.createQuery(
                        "from Tache t where t.dateDebut between :d1 and :d2",
                        Tache.class);

        query.setParameter("d1", d1);
        query.setParameter("d2", d2);

        List<Tache> taches = query.list();

        session.close();
        return taches;
    }
}