package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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
    @Column( name = "numsiege" )
    private int numSiege;
    @Column( name = "numrangee" )
    private int numRangee;
    @JoinColumn(name = "idseance")
    @OneToOne
    @JsonBackReference(value = "siege_seance_fk")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private Seance seance;
}
