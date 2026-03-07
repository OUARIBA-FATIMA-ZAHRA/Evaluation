package ma.projet.service;

import ma.projet.classes.LigneCommandeProduit;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import java.util.List;

public class LigneCommandeService implements IDao<LigneCommandeProduit> {

    @Override
    public LigneCommandeProduit save(LigneCommandeProduit l) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(l);
        session.getTransaction().commit();
        session.close();
        return l;
    }

    @Override
    public LigneCommandeProduit update(LigneCommandeProduit l) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(l);
        session.getTransaction().commit();
        session.close();
        return l;
    }

    @Override
    public void delete(LigneCommandeProduit l) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(l);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public LigneCommandeProduit findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        LigneCommandeProduit l = session.get(LigneCommandeProduit.class, id);
        session.close();
        return l;
    }

    @Override
    public List<LigneCommandeProduit> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<LigneCommandeProduit> lignes = session.createQuery("from LigneCommandeProduit", LigneCommandeProduit.class).list();
        session.close();
        return lignes;
    }
}