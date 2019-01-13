package fr.cesi.poec.controllers;

import fr.cesi.poec.entities.Cinema;
import fr.cesi.poec.repositories.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/cinema")
public class CinemaController {

    private CinemaRepository cinemaRepository;

    @Autowired
    public CinemaController(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Cinema>> getCinemas() {
        return new ResponseEntity<List<Cinema>>(
                (List<Cinema>)this.cinemaRepository.findAll(),
                HttpStatus.OK);
    }

    /**findWithIdFilm
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cinema> getCinemaById(@PathVariable Long id) {
        Cinema cinema = this.cinemaRepository.findById(id).orElse(null);
        if (cinema != null) {
            return new ResponseEntity<Cinema>(cinema, HttpStatus.OK);
        } else {
            return new ResponseEntity<Cinema>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/film/{idFilm}")
    public ResponseEntity<List<Cinema>> getCinemasWithIdFilm(@PathVariable Long idFilm) {
        return new ResponseEntity<List<Cinema>>(
                (List<Cinema>)this.cinemaRepository.findWithIdFilm(idFilm),
                HttpStatus.OK);
    }

    /**
     *
     * @param cinema
     * @return
     */
    @PostMapping
    public ResponseEntity<Cinema> createCinema(@RequestBody Cinema cinema) {
        cinema = this.cinemaRepository.save(cinema);
        System.out.println("Cinema created : " + cinema.getId());
        return new ResponseEntity<Cinema>(cinema, HttpStatus.CREATED);
    }

    /**
     *
     * @param cinema
     * @return
     */
    @PutMapping
    public ResponseEntity<Cinema> updateCinema(@RequestBody Cinema cinema) {
        if ( this.cinemaRepository.existsById(cinema.getId()) ) {
            this.cinemaRepository.save(cinema);
            return new ResponseEntity<Cinema>(cinema, HttpStatus.CREATED);
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
    public ResponseEntity<Void> deleteCinema(@PathVariable Long id) {
        Cinema cinema = this.cinemaRepository.findById(id).orElse(null);
        if (cinema != null) {
            this.cinemaRepository.delete(cinema);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
