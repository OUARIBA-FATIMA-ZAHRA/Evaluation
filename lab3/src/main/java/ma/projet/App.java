package ma.projet;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;
import java.text.ParseException;

import java.text.SimpleDateFormat;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {

        HommeService hs = new HommeService();
        FemmeService fs = new FemmeService();
        MariageService ms = new MariageService();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Homme h = new Homme();
        h.setNom("SAFI");
        h.setPrenom("SAID");
        hs.create(h);

        // Création femme
        Femme f = new Femme();
        f.setNom("SALIMA");
        f.setPrenom("RAMI");
        fs.create(f);

        Mariage m = new Mariage();
        m.setHomme(h);
        m.setFemme(f);
        try {
            m.setDateDebut(sdf.parse("03/09/2020"));
        } catch (ParseException e) {
            e.printStackTrace();
        }        m.setNbrEnfant(4);
        ms.create(m);

        hs.afficherMariages(h.getId());
    }
}
