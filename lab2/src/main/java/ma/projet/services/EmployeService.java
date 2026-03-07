package ma.projet.services;

import ma.projet.classes.Employe;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeService implements IDao<Employe> {
    @Override
    public Employe save(Employe e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e);
        session.getTransaction().commit();
        session.close();
        return e;
    }

    @Override
    public Employe update(Employe e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(e);
        session.getTransaction().commit();
        session.close();
        return e;
    }

    @Override
    public void delete(Employe e){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(e);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Employe findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Employe e = session.get(Employe.class, id);
        session.close();
        return e;
    }

    @Override
    public List<Employe> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employe> employes = session.createQuery("from Employe", Employe.class).list();
        session.close();
        return employes;
    }

    public List<Employe> findByTache(int tacheId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Employe> query = session.createQuery("from Employe e where e.tache.id = :id", Employe.class);
        query.setParameter("id", tacheId);
        List<Employe> employes = query.list();
        session.close();
        return employes;
    }

    public List<Employe> findByProjet(int projetId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Employe> query = session.createQuery("from Employe e where e.projet.id = :id", Employe.class);
        query.setParameter("id", projetId);
        List<Employe> employes = query.list();
        session.close();
        return employes;
    }
    public List<Tache> getTachesByEmploye(int employeId) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Tache> query =
                session.createQuery(
                        "select et.tache from EmployeTache et "
                                + "where et.employe.id = :id",
                        Tache.class);

        query.setParameter("id", employeId);

        List<Tache> taches = query.list();
        session.close();

        return taches;
    }
    public List<Projet> getProjetsByEmploye(int employeId) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Projet> query =
                session.createQuery(
                        "from Projet p where p.employe.id = :id",
                        Projet.class);

        query.setParameter("id", employeId);

        List<Projet> projets = query.list();
        session.close();

        return projets;
    }



    }
