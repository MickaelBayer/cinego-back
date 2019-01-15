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


//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
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
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private Seance seance;
}
