package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


//@Entity avec name = le nom de la table dans la DB, mapping avec la DB
//@Data => Lombok annotation qui permet de creer toutes
// les fonctions de bases nécessaires
// constricteur sans arg
// getteurs setteurs
@Entity(name="seance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seance {
    //precision avec annotation de l'ID
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column( name = "IDSeance" )
    private Long id;
    @Column( name = "DateSeance" )
    private Date date;
    @ManyToOne
    @JsonBackReference
    private Film film;
    @OneToOne
    @JsonBackReference
    private Salle salle;

}
