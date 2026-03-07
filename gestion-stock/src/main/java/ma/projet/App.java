package ma.projet;

import ma.projet.classes.*;
import ma.projet.service.*;
import java.util.Date;

public class App {
    public static void main(String[] args) {

        CategorieService cs = new CategorieService();
        ProduitService ps = new ProduitService();
        CommandeService cmdService = new CommandeService();
        LigneCommandeService lcs = new LigneCommandeService();

        Categorie cat1 = new Categorie();
        cat1.setCode("C001");
        cat1.setLibelle("Electronique");
        cs.save(cat1);

        Produit p1 = new Produit();
        p1.setReference("TV001");
        p1.setPrix(2500);
        p1.setCategorie(cat1);
        ps.save(p1);

        Produit p2 = new Produit();
        p2.setReference("Phone001");
        p2.setPrix(800);
        p2.setCategorie(cat1);
        ps.save(p2);

        Commande cmd1 = new Commande();
        cmd1.setDate(new Date());
        cmdService.save(cmd1);

        LigneCommandeProduit l1 = new LigneCommandeProduit();
        l1.setProduit(p1);
        l1.setCommande(cmd1);
        l1.setQuantite(2);
        lcs.save(l1);

        LigneCommandeProduit l2 = new LigneCommandeProduit();
        l2.setProduit(p2);
        l2.setCommande(cmd1);
        l2.setQuantite(3);
        lcs.save(l2);

        System.out.println(" Tous les produits :");
        ps.findAll().forEach(p -> System.out.println(p.getReference() + " - " + p.getPrix()));

        System.out.println("\n Produits de la catégorie Electronique :");
        ps.findByCategorie(cat1.getId()).forEach(p -> System.out.println(p.getReference()));

        System.out.println("\n Produits de la commande 1 :");
        ps.findProduitsByCommande(cmd1.getId()).forEach(p -> System.out.println(p.getReference()));

        System.out.println("\n Produits > 100 DH :");
        ps.findProduitsPrixSup100().forEach(p -> System.out.println(p.getReference()));
    }
}
