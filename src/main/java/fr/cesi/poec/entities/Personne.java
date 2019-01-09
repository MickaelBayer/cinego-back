package fr.cesi.poec.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity(name="personne")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personne {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column( name = "IDPersonne" )
    private Long id;
    @Column( name = "Nom" )
    private String nom;
    @Column( name = "Prenom" )
    private String prenom;
    @Column( name = "Adresse" )
    private String adresse;
    @Column( name = "Ville" )
    private String ville;
    @Column( name = "CodePostal" )
    private int codePostal;
    @Column( name = "Pays" )
    private String pays;
    @Column( name = "Mail" )
    private String mail;
    @Column( name = "Tel" )
    private int tel;
    @Column( name = "Statut" )
    private String statut;
    @Column( name = "MdpH5" )
    private String mdpH5;
    @Column( name = "GrainDeSel" )
    private String grainDeSel;

}
