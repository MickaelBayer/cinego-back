package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;


@Entity(name="commande")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column( name = "idcommande" )
    private Long id;

    @Column( name = "nbrplacereservees" )
    private int nbPlaces;

    @Column( name = "estpaye" )
    private boolean estPaye;

    @Column( name = "coutreservation" )
    private float coutReservation;

    //@JsonBackReference(value = "personne_commande_fk")
    @JoinColumn(name = "idpersonne")
    @ManyToOne
    private Personne personne;

    //@JsonBackReference(value = "commande_seance_fk")
    @JoinColumn(name = "idseance")
    @ManyToOne
    private Seance seance;
    //JoinTable => joinColumns = colonne de la join table corresponsant à l'id de la classe en cours
    //          => inverseJoinColumns = colonne de l'id correspondant à l'item dans la liste
    @JoinTable(
            name = "reserve",
            joinColumns = @JoinColumn(
                    name = "idcommande",
                    referencedColumnName = "idcommande"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "idsiege",
                    referencedColumnName = "idsiege"
            )
    )
    @OneToMany
    @JsonBackReference(value = "commande_siege_fk")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Siege> sieges;
}
