package ma.projet;

import ma.projet.classes.*;
import ma.projet.services.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        EmployeService employeService = new EmployeService();
        ProjetService projetService = new ProjetService();
        TacheService tacheService = new TacheService();
        EmployeTacheService etService = new EmployeTacheService();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        Employe e1 = new Employe();
        e1.setNom("Ali");
        e1.setPrenom("Karim");
        e1.setTelephone("0600000000");

        employeService.save(e1);


        Projet p1 = new Projet();
        p1.setNom("Gestion de stock");
        p1.setDateDebut(sdf.parse("14/01/2013"));
        p1.setEmploye(e1);

        projetService.save(p1);



        Tache t1 = new Tache();
        t1.setNom("Analyse");
        t1.setPrix(1500.0);
        t1.setDateDebut(sdf.parse("10/02/2013"));
        t1.setDateFin(sdf.parse("20/02/2013"));
        t1.setProjet(p1);

        tacheService.save(t1);

        Tache t2 = new Tache();
        t2.setNom("Conception");
        t2.setPrix(1200.0);
        t2.setDateDebut(sdf.parse("10/03/2013"));
        t2.setDateFin(sdf.parse("15/03/2013"));
        t2.setProjet(p1);

        tacheService.save(t2);



        EmployeTache et1 = new EmployeTache();
        et1.setEmploye(e1);
        et1.setTache(t1);
        et1.setDateDebutReelle(sdf.parse("10/02/2013"));
        et1.setDateFinReelle(sdf.parse("20/02/2013"));

        etService.save(et1);



        System.out.println("\n===== AFFICHAGE PROJET =====");

        Projet projet = projetService.findById(p1.getId());

        System.out.println(
                "Projet : " + projet.getId()
                        + " Nom : " + projet.getNom()
                        + " Date début : " + projet.getDateDebut()
        );

        System.out.println("\nListe des tâches :");

        List<Tache> taches = tacheService.findAll();

        for (Tache t : taches) {
            System.out.println(
                    t.getId() + "  "
                            + t.getNom() + "  "
                            + t.getDateDebut() + "  "
                            + t.getDateFin()
            );
        }



        System.out.println("\nTaches prix > 1000 DH");

        List<Tache> tachesCheres =
                tacheService.findPrixSup(1000);

        tachesCheres.forEach(t ->
                System.out.println(t.getNom() + " : " + t.getPrix())
        );



        System.out.println("\nTaches réalisées par employé :");

        List<Tache> tEmploye =
                employeService.getTachesByEmploye(e1.getId());

        tEmploye.forEach(t ->
                System.out.println(t.getNom())
        );

        System.out.println("\n===== FIN TEST =====");
    }
}
