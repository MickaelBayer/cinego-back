package fr.cesi.poec.controllers;

import fr.cesi.poec.entities.Siege;
import fr.cesi.poec.repositories.SiegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/siege")
public class SiegeController {

    private SiegeRepository siegeRepository;

    @Autowired
    public SiegeController(SiegeRepository siegeRepository) {
        this.siegeRepository = siegeRepository;
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Siege>> getSieges() {
        return new ResponseEntity<List<Siege>>(
                (List<Siege>)this.siegeRepository.findAll(),
                HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Siege> getSiegeById(@PathVariable Long id) {
        Siege siege = this.siegeRepository.findById(id).orElse(null);
        if (siege != null) {
            return new ResponseEntity<Siege>(siege, HttpStatus.OK);
        } else {
            return new ResponseEntity<Siege>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     *
     * @param siege
     * @return
     */
    @PostMapping
    public ResponseEntity<Siege> createSiege(@RequestBody Siege siege) {
        siege = this.siegeRepository.save(siege);
        System.out.println("Siege created : " + siege.getId());
        return new ResponseEntity<Siege>(siege, HttpStatus.CREATED);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSiege(@PathVariable Long id) {
        Siege siege = this.siegeRepository.findById(id).orElse(null);
        if (siege != null) {
            this.siegeRepository.delete(siege);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
