package ma.projet.beans;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "femme")
@NamedQuery(
        name = "Femme.findMarriedTwice",
        query = "SELECT f FROM Femme f WHERE SIZE(f.mariages) >= 2"
)
@NamedNativeQuery(
        name = "Femme.countEnfantsBetweenDates",
        query = "SELECT SUM(nbrEnfant) FROM mariage WHERE femme_id = ? AND dateDebut BETWEEN ? AND ?"
)
public class Femme extends Personne {

    @OneToMany(mappedBy = "femme")
    private List<Mariage> mariages;

    public Femme (){}


    public Femme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
        this.mariages = mariages;
    }

    public List<Mariage> getMariages() {
        return mariages;
    }

    public void setMariages(List<Mariage> mariages) {
        this.mariages = mariages;
    }
}


