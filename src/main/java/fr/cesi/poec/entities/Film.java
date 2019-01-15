package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;


@Entity(name="film")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column( name = "idfilm" )
    private Long id;
    @Column( name = "nomfilm" )
    private String nom;
    @Column( name = "image" )
    private String imgUrl;
    @Column( name = "genre" )
    private String genre;
    @Column( name = "duree" )
    private int duree;
    @Column( name = "realisateur" )
    private String realisateur;
    @Column( name = "listeacteurs" )
    private String acteurs;
    @Column( name = "resume" )
    private String resume;
    @OneToMany(mappedBy = "film")
    @JsonBackReference
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Seance> seances;

}
