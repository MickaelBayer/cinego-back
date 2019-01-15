package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
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
    private Film film;
    @JoinColumn(name = "idplansalle")
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private PlanSalle planSalle;
    @JsonBackReference
    @OneToMany(mappedBy = "seance")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Siege> sieges;

}
