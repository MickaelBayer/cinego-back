package fr.cesi.poec.controllers;

import fr.cesi.poec.entities.Personne;
import fr.cesi.poec.repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class PersonneController {

    private PersonneRepository personneRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PersonneController(PersonneRepository personneRepository,
                              BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personneRepository = personneRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    @GetMapping("/updateUserId/{mail}")
    public ResponseEntity<Personne> getPersonneByIdWithMail(@PathVariable String mail) {
        Personne personne = this.personneRepository.findPersonneByMail(mail);
        if (personne != null) {
            return new ResponseEntity<Personne>(personne, HttpStatus.OK);
        } else {
            return new ResponseEntity<Personne>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Personne> createPersonne(@RequestBody Personne personne) {
        personne.setMdph5(bCryptPasswordEncoder.encode(personne.getMdph5()));
        personne = this.personneRepository.save(personne);
        return new ResponseEntity<Personne>(personne, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Personne> updatePersonne(@RequestBody Personne personne) {
        if ( this.personneRepository.existsById(personne.getId()) ) {
            this.personneRepository.save(personne);
            return new ResponseEntity<Personne>(personne, HttpStatus.CREATED);
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
