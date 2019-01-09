package fr.cesi.poec.controllers;

import fr.cesi.poec.entities.Salle;
import fr.cesi.poec.repositories.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/salle")
public class SalleController {

    private SalleRepository salleRepository;

    @Autowired
    public SalleController(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Salle>> getSalles() {
        return new ResponseEntity<List<Salle>>(
                (List<Salle>)this.salleRepository.findAll(),
                HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Salle> getSalleById(@PathVariable Long id) {
        Salle salle = this.salleRepository.findById(id).orElse(null);
        if (salle != null) {
            return new ResponseEntity<Salle>(salle, HttpStatus.OK);
        } else {
            return new ResponseEntity<Salle>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     *
     * @param salle
     * @return
     */
    @PostMapping
    public ResponseEntity<Salle> createSalle(@RequestBody Salle salle) {
        salle = this.salleRepository.save(salle);
        System.out.println("Salle created : " + salle.getId());
        return new ResponseEntity<Salle>(salle, HttpStatus.CREATED);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalle(@PathVariable Long id) {
        Salle salle = this.salleRepository.findById(id).orElse(null);
        if (salle != null) {
            this.salleRepository.delete(salle);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
