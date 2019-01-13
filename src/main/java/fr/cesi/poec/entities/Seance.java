package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


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
    private String date;
    @JoinColumn(name = "idfilm")
    @ManyToOne()
    @JsonBackReference(value = "film_seance_fk")
    private Film film;
    @JoinColumn(name = "idplansalle")
    @OneToOne
    @JsonBackReference(value = "plansalle_seance_fk")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private PlanSalle planSalle;
    @OneToMany(mappedBy = "seance")
    @JsonManagedReference(value = "siege_seance_fk")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Siege> sieges;

}
