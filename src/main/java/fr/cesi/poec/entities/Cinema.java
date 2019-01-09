package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;


@Entity(name="cinema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column( name = "idcinema" )
    private Long id;
    @Column( name = "nomcinema" )
    private String nomCinema;
    @Column( name = "adressecinema" )
    private String adresse;
    @Column( name = "cpcinema" )
    private int cpCinema;
    @Column( name = "villecinema" )
    private String villeCinema;
    @Column( name = "payscinema" )
    private String paysCinema;
    //mappedBy => attribut sur lequel il est mappé dans l'autre classe
    @OneToMany(mappedBy = "cinema")
    //@JsonBackReference(value = "plans")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<PlanSalle> plans;

}
