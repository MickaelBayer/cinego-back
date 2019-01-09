package fr.cesi.poec.controllers;

import fr.cesi.poec.entities.Personne;
import fr.cesi.poec.repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
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
    public ResponseEntity<Personne> getPersonneById(@PathVariable Long id) {
        Personne personne = this.personneRepository.findById(id).orElse(null);
        if (personne != null) {
            return new ResponseEntity<Personne>(personne, HttpStatus.OK);
        } else {
            return new ResponseEntity<Personne>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<Personne> createPersonne(@RequestBody Personne personne) {
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
