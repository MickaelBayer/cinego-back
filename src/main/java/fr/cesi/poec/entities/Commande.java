package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity(name="commande")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column( name = "IDCommande" )
    private Long id;
    @Column( name = "NbrPlaceReservees" )
    private int nbPlaces;
    @Column( name = "EstPaye" )
    private boolean estPaye;
    @Column( name = "CoutReservation" )
    private int CoutReservation;
    @ManyToOne
    @JsonBackReference
    private Personne personne;
    @ManyToOne
    @JsonBackReference
    private Seance seance;
    //JoinTable => joinColumns = colonne de la join table corresponsant à l'id de la classe en cours
    //          => inverseJoinColumns = colonne de l'id correspondant à l'item dans la liste
    @JoinTable(
            name = "reserve",
            joinColumns = @JoinColumn(
                    name = "IDCommande",
                    referencedColumnName = "IDCommande"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "IDSiege",
                    referencedColumnName = "IDSiege"
            )
    )
    @OneToMany
    private List<Siege> sieges;
}
