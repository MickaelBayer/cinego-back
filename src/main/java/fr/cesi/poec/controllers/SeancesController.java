package fr.cesi.poec.controllers;

import fr.cesi.poec.entities.Film;
import fr.cesi.poec.entities.Seance;
import fr.cesi.poec.repositories.SeanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Annotation qui permet de mapper des méthodes à des urls
@RestController
//La racine de toutes les routes de ce controller
@RequestMapping("/seances")
public class SeancesController {

    private SeanceRepository seanceRepository;

    //Pour que Springboot instancie seancerepo, on l'injecte dans le constructeur
    //@Autowired => injection de dépendances
    @Autowired
    public SeancesController(SeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
    }

    //http://localhost:8282/seances
    //permet de lier la méthode à une requète http get
    @GetMapping
    public ResponseEntity<List<Seance>> getSeances() {
        return new ResponseEntity<List<Seance>>(
                        (List<Seance>)this.seanceRepository.findAll(),
                        HttpStatus.OK);
    }

    @PostMapping
    //@RequestBody -> pour dire d'aller le chercher dans le body de la req
    //ResponseEntity<Void> => renvoyer un statut particulier
    //ResponseEntity<Seance> => renvoye une Seance
    public ResponseEntity<Seance> createSeance(@RequestBody Seance s) {
        s = this.seanceRepository.save(s);
        System.out.println("Seance created : " + s.getId());
        return new ResponseEntity<Seance>(s, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Seance> getSeanceById(@PathVariable Long id) {
        //this.seanceRepository.findById(id) => renvoie un optional qui donne accès à des fonctions de controle
        Seance s = this.seanceRepository.findById(id).orElse(null);
        if (s != null) {
            return new ResponseEntity<Seance>(s, HttpStatus.OK);
        } else {
            return new ResponseEntity<Seance>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/film/{idFilm}")
        public ResponseEntity<List<Seance>> getSeanceWithFilm(@PathVariable Long idFilm) {
            return new ResponseEntity<List<Seance>>(
                    (List<Seance>)this.seanceRepository.findWithFilm(idFilm),
                    HttpStatus.OK);
    }

    @GetMapping("/{idCinema}/{idFilm}")
    public ResponseEntity<List<Seance>> getSeancesByCinemaAndFilm(@PathVariable Long idCinema, @PathVariable Long idFilm) {
       return new ResponseEntity<List<Seance>>(
                (List<Seance>)this.seanceRepository.findWithIdCinemaAndIdFilm(idCinema,idFilm),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Seance> updateSeance(@RequestBody Seance s) {
        if ( this.seanceRepository.existsById(s.getId()) ) {
            this.seanceRepository.save(s);
            return new ResponseEntity<Seance>(s, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeance(@PathVariable Long id) {
        Seance s = this.seanceRepository.findById(id).orElse(null);
        if (s != null) {
            this.seanceRepository.delete(s);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}