package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;


@Entity(name="plansalle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanSalle {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column( name = "idplansalle" )
    private Long id;
    @Column( name = "plan" )
    private String plan;
    @JoinColumn(name = "idcinema")
    @ManyToOne
    @JsonBackReference(value = "cinema_plan_fk")
    private Cinema cinema;
    @OneToMany(mappedBy = "plan")
    @JsonManagedReference(value = "plansalle_salle_fk")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Salle> salles;

}
