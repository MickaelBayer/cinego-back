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
    @Column( name = "idsiege" )
    private Long id;
    @Column( name = "estreserve" )
    private boolean estReserve;
    @Column( name = "typesiege" )
    private String type;
    @Column( name = "numrangee" )
    private int numRangee;
    @JoinColumn(name = "idsalle")
    @ManyToOne
    @JsonBackReference(value = "salle")
    private Salle salle;
}
