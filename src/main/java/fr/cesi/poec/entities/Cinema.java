package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity(name="cinema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column( name = "IDPersonne" )
    private Long id;
    @Column( name = "NomCinema" )
    private String nomCinema;
    @Column( name = "AdresseCinema" )
    private String adresse;
    @Column( name = "CPCinema" )
    private int CPCinema;
    @Column( name = "VilleCinema" )
    private String VilleCinema;
    @Column( name = "PaysCinema" )
    private String PaysCinema;
    @OneToMany(mappedBy = "Cinema")
    @JsonBackReference
    private List<PlanSalle> planSalles;

}
