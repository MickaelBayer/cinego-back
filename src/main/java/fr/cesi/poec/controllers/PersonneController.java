package fr.cesi.poec.controllers;

import fr.cesi.poec.entities.Personne;
import fr.cesi.poec.repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Annotation qui permet de mapper des méthodes à des urls
@RestController
//La racine de toutes les routes de ce controller
@RequestMapping("/user")
public class PersonneController {

    private PersonneRepository personneRepository;

    @Autowired
    public PersonneController(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    @GetMapping
    public ResponseEntity<List<Personne>> getPersonnes() {
        return new ResponseEntity<List<Personne>>(
                    (List<Personne>)this.personneRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personne> getSceanceById(@PathVariable Long id) {
        //this.sceanceRepository.findById(id) => renvoie un optional qui donne accès à des fonctions de controle
        Personne personne = this.personneRepository.findById(id).orElse(null);
        if (personne != null) {
            return new ResponseEntity<Personne>(personne, HttpStatus.OK);
        } else {
            return new ResponseEntity<Personne>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    //@RequestBody -> pour dire d'aller le chercher dans le body de la req
    //ResponseEntity<Void> => renvoyer un statut particulier
    //ResponseEntity<Seance> => renvoye une Seance
    public ResponseEntity<Personne> createSceance(@RequestBody Personne personne) {
        personne = this.personneRepository.save(personne);
        System.out.println("Personne created : " + personne.getId());
        return new ResponseEntity<Personne>(personne, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Personne> updatePersonne(@RequestBody Personne s) {
        //recup l'elem existant
        //sauver l'elem avec les modifs
        if ( this.personneRepository.existsById(s.getId()) ) {
            this.personneRepository.save(s);
            return new ResponseEntity<Personne>(s, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonnne(@PathVariable Long id) {
        Personne personne = this.personneRepository.findById(id).orElse(null);
        if (personne != null) {
            this.personneRepository.delete(personne);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
