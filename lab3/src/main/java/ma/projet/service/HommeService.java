package ma.projet.service;


import ma.projet.beans.*;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.*;

public class HommeService implements IDao<Homme> {

    @Override
    public boolean create(Homme o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(o);
        tx.commit();
        s.close();
        return true;
    }

    @Override
    public List<Homme> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Homme> list = s.createQuery("from Homme").list();
        s.close();
        return list;
    }

    // Épouses entre deux dates
    public List<Femme> getEpousesBetweenDates(int hommeId, Date d1, Date d2) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Femme> list = s.createQuery(
                        "SELECT m.femme FROM Mariage m WHERE m.homme.id=:id AND m.dateDebut BETWEEN :d1 AND :d2",
                        Femme.class)
                .setParameter("id", hommeId)
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .list();
        s.close();
        return list;
    }

    // Affichage détaillé des mariages
    public void afficherMariages(int hommeId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Mariage> list = s.createQuery(
                        "FROM Mariage m WHERE m.homme.id=:id", Mariage.class)
                .setParameter("id", hommeId)
                .list();

        for (Mariage m : list) {
            System.out.println("Femme : " + m.getFemme().getNom() +
                    " Date début : " + m.getDateDebut() +
                    " Date fin : " + m.getDateFin() +
                    " Enfants : " + m.getNbrEnfant());
        }
        s.close();
    }

    @Override public boolean update(Homme o){return false;}
    @Override public boolean delete(Homme o){return false;}
    @Override public Homme findById(int id){return null;}
}