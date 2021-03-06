package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;


@Entity(name="personne")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personne {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column( name = "idpersonne" )
    private Long id;
    @Column( name = "nom" )
    private String nom;
    @Column( name = "prenom" )
    private String prenom;
    @Column( name = "adresse" )
    private String adresse;
    @Column( name = "ville" )
    private String ville;
    @Column( name = "codepostal" )
    private int codePostal;
    @Column( name = "pays" )
    private String pays;
    @Column( name = "mail" )
    private String mail;
    @Column( name = "tel" )
    private int tel;
    @Column( name = "admin" )
    private boolean admin;
    @Column( name = "mdph5" )
    private String mdph5;
    @OneToMany(mappedBy = "personne")
    @JsonBackReference
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Commande> commandes;

    public Personne(Long id, String nom, String prenom, String mail, String mdph5) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdph5 = mdph5;
    }

}
