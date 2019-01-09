package fr.cesi.poec.controllers;

import fr.cesi.poec.entities.PlanSalle;
import fr.cesi.poec.entities.PlanSalle;
import fr.cesi.poec.repositories.PlanSalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/planSalle")
public class PlanSalleController {

    private PlanSalleRepository planSalleRepository;

    @Autowired
    public PlanSalleController(PlanSalleRepository planSalleRepository) {
        this.planSalleRepository = planSalleRepository;
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<PlanSalle>> getPlanSalles() {
        return new ResponseEntity<List<PlanSalle>>(
                (List<PlanSalle>)this.planSalleRepository.findAll(),
                HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlanSalle> getPlanSalleById(@PathVariable Long id) {
        PlanSalle planSalle = this.planSalleRepository.findById(id).orElse(null);
        if (planSalle != null) {
            return new ResponseEntity<PlanSalle>(planSalle, HttpStatus.OK);
        } else {
            return new ResponseEntity<PlanSalle>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     *
     * @param planSalle
     * @return
     */
    @PostMapping
    public ResponseEntity<PlanSalle> createPlanSalle(@RequestBody PlanSalle planSalle) {
        planSalle = this.planSalleRepository.save(planSalle);
        System.out.println("PlanSalle created : " + planSalle.getId());
        return new ResponseEntity<PlanSalle>(planSalle, HttpStatus.CREATED);
    }

    /**
     *
     * @param planSalle
     * @return
     */
    @PutMapping
    public ResponseEntity<PlanSalle> updatePlanSalle(@RequestBody PlanSalle planSalle) {
        if ( this.planSalleRepository.existsById(planSalle.getId()) ) {
            this.planSalleRepository.save(planSalle);
            return new ResponseEntity<PlanSalle>(planSalle, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    
    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanSalle(@PathVariable Long id) {
        PlanSalle planSalle = this.planSalleRepository.findById(id).orElse(null);
        if (planSalle != null) {
            this.planSalleRepository.delete(planSalle);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
