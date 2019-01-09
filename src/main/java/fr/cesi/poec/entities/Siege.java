package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity(name="siege")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Siege {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column( name = "IDSiege" )
    private Long id;
    @Column( name = "EstReserve" )
    private boolean estReserve;
    @Column( name = "TypeSiege" )
    private String type;
    @Column( name = "NumRangee" )
    private int numRangee;
    @ManyToOne
    @JsonBackReference
    private Salle salle;
}
