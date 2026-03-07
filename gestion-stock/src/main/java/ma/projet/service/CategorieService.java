package ma.projet.service;

import ma.projet.classes.Categorie;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class CategorieService implements IDao<Categorie> {

    @Override
    public Categorie save(Categorie c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
        session.close();
        return c;
    }

    @Override
    public Categorie update(Categorie c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(c);
        session.getTransaction().commit();
        session.close();
        return c;
    }

    @Override
    public void delete(Categorie c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(c);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Categorie findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Categorie c = session.get(Categorie.class, id);
        session.close();
        return c;
    }

    @Override
    public List<Categorie> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Categorie> categories = session.createQuery("from Categorie", Categorie.class).list();
        session.close();
        return categories;
    }
}
