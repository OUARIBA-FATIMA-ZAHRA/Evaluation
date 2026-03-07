package ma.projet.services;

import ma.projet.classes.Projet;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ma.projet.dao.IDao;
import java.util.List;

public class ProjetService implements IDao<Projet>  {

    @Override
    public Projet save(Projet p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
        return p;
    }

    @Override
    public Projet update(Projet p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(p);
        session.getTransaction().commit();
        session.close();
        return p;
    }

    @Override
    public void delete(Projet p){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Projet findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Projet p = session.get(Projet.class, id);
        session.close();
        return p;
    }

    @Override
    public List<Projet> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Projet> projets = session.createQuery("from Projet", Projet.class).list();
        session.close();
        return projets;
    }

    public List<Projet> findByTache(int tacheId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Projet> query = session.createQuery("select p from Projet p join p.taches t where t.id = :id",
                Projet.class);
        query.setParameter("id", tacheId);
        List<Projet> projets = query.list();
        session.close();
        return projets;
    }



}
