package ma.projet.service;

import ma.projet.classes.Produit;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;

public class ProduitService implements IDao<Produit> {

    @Override
    public Produit save(Produit p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
        return p;
    }

    @Override
    public Produit update(Produit p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(p);
        session.getTransaction().commit();
        session.close();
        return p;
    }

    @Override
    public void delete(Produit p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Produit findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Produit p = session.get(Produit.class, id);
        session.close();
        return p;
    }

    @Override
    public List<Produit> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Produit> produits = session.createQuery("from Produit", Produit.class).list();
        session.close();
        return produits;
    }

    public List<Produit> findByCategorie(int categorieId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Produit> query = session.createQuery("from Produit p where p.categorie.id = :id", Produit.class);
        query.setParameter("id", categorieId);
        List<Produit> produits = query.list();
        session.close();
        return produits;
    }

    public List<Produit> findProduitsByDate(Date start, Date end) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Produit> query = session.createQuery(
                "select l.produit from LigneCommandeProduit l where l.commande.date between :start and :end", Produit.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        List<Produit> produits = query.list();
        session.close();
        return produits;
    }

    public List<Produit> findProduitsByCommande(int commandeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Produit> query = session.createQuery(
                "select l.produit from LigneCommandeProduit l where l.commande.id = :id", Produit.class);
        query.setParameter("id", commandeId);
        List<Produit> produits = query.list();
        session.close();
        return produits;
    }

    public List<Produit> findProduitsPrixSup100() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Produit> query = session.createQuery("from Produit p where p.prix > 100", Produit.class);
        List<Produit> produits = query.list();
        session.close();
        return produits;
    }
}