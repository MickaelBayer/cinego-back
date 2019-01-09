package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity(name="plansalle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanSalle {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column( name = "IDPlanSalle" )
    private Long id;
    @OneToOne
    @JsonBackReference
    private Salle salle;
    @Column( name = "Plan" )
    private String plan;

}
