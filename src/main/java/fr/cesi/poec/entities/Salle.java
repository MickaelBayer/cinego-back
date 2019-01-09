package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity(name="salle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salle {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column( name = "IDSalle" )
    private Long id;
    @ManyToOne
    @JsonBackReference
    private PlanSalle plan;
    @OneToOne
    @JsonBackReference
    private Seance seance;
    @OneToMany(mappedBy = "Salle")
    @JsonBackReference
    private List<Siege> sieges;
}
