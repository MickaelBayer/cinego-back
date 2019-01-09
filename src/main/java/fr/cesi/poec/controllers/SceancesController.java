package fr.cesi.poec.controllers;

import fr.cesi.poec.entities.Seance;
import fr.cesi.poec.repositories.SceanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Annotation qui permet de mapper des méthodes à des urls
@RestController
//La racine de toutes les routes de ce controller
@RequestMapping("/sceances")
public class SceancesController {

    private SceanceRepository sceanceRepository;

    //Pour que Springboot instancie sceancerepo, on l'injecte dans le constructeur
    //@Autowired => injection de dépendances
    @Autowired
    public SceancesController(SceanceRepository sceanceRepository) {
        this.sceanceRepository = sceanceRepository;
    }

    //http://localhost:8282/sceances
    //permet de lier la méthode à une requète http get
    @GetMapping
    public ResponseEntity<List<Seance>> getSceances() {
        return new ResponseEntity<List<Seance>>(
                        (List<Seance>)this.sceanceRepository.findAll(),
                        HttpStatus.OK);
    }

    @PostMapping
    //@RequestBody -> pour dire d'aller le chercher dans le body de la req
    //ResponseEntity<Void> => renvoyer un statut particulier
    //ResponseEntity<Seance> => renvoye une Seance
    public ResponseEntity<Seance> createSceance(@RequestBody Seance s) {
        s = this.sceanceRepository.save(s);
        System.out.println("Seance created : " + s.getId());
        return new ResponseEntity<Seance>(s, HttpStatus.CREATED);
    }

    //
    @GetMapping("/{id}")
    public ResponseEntity<Seance> getSceanceById(@PathVariable Long id) {
        //this.sceanceRepository.findById(id) => renvoie un optional qui donne accès à des fonctions de controle
        Seance s = this.sceanceRepository.findById(id).orElse(null);
        if (s != null) {
            return new ResponseEntity<Seance>(s, HttpStatus.OK);
        } else {
            return new ResponseEntity<Seance>(HttpStatus.NO_CONTENT);
        }
    }


    @PutMapping
    public ResponseEntity<Seance> updateSceance(@RequestBody Seance s) {
        //recup l'elem existant
        //sauver l'elem avec les modifs
        if ( this.sceanceRepository.existsById(s.getId()) ) {
            this.sceanceRepository.save(s);
            return new ResponseEntity<Seance>(s, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSceance(@PathVariable Long id) {
        Seance s = this.sceanceRepository.findById(id).orElse(null);
        if (s != null) {
            this.sceanceRepository.delete(s);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}