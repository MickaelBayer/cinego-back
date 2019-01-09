package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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
    @Column( name = "idseance" )
    private Long id;
    @Column( name = "dateseance" )
    private Date date;
    @JoinColumn(name = "idfilm")
    @ManyToOne()
    @JsonBackReference(value = "film_seance_fk")
    private Film film;
    @JoinColumn(name = "idsalle")
    @OneToOne
    @JsonBackReference(value = "salle_seance_fk")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private Salle salle;

}
