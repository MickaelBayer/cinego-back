package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;


@Entity(name="salle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salle {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column( name = "idsalle" )
    private Long id;
    @JoinColumn(name = "idplansalle")
    @ManyToOne
    @JsonBackReference(value = "plansalle_salle_fk")
    private PlanSalle plan;
    @JoinColumn(name = "idseance")
    @OneToOne
    @JsonManagedReference(value = "salle_seance_fk")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private Seance seance;
    /*@OneToMany(mappedBy = "salle")
    @JsonManagedReference(value = "sieges_salle_fk")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Siege> sieges;*/
}
