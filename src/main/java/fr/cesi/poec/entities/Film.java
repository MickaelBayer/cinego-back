package fr.cesi.poec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.org.apache.regexp.internal.StreamCharacterIterator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity(name="film")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column( name = "IDFilm" )
    private Long id;
    @Column( name = "NomFilm" )
    private String nom;
    @Column( name = "Image" )
    private String imgUrl;
    @Column( name = "Genre" )
    private String genre;
    @Column( name = "Duree" )
    private int duree;
    @Column( name = "Realisateur" )
    private String realisateur;
    @Column( name = "ListeActeurs" )
    private String acteurs;
    @OneToMany(mappedBy = "Film")
    @JsonBackReference
    private List<Seance> seances;

}
