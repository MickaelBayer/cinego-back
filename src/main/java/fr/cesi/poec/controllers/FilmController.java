package fr.cesi.poec.controllers;

import fr.cesi.poec.entities.Film;
import fr.cesi.poec.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/film")
public class FilmController {

    private FilmRepository filmRepository;

    @Autowired
    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Film>> getFilms() {
        return new ResponseEntity<List<Film>>(
                (List<Film>)this.filmRepository.findAll(),
                HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        Film film = this.filmRepository.findById(id).orElse(null);
        if (film != null) {
            return new ResponseEntity<Film>(film, HttpStatus.OK);
        } else {
            return new ResponseEntity<Film>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     *
     * @param film
     * @return
     */
    @PostMapping
    public ResponseEntity<Film> createFilm(@RequestBody Film film) {
        film = this.filmRepository.save(film);
        System.out.println("Film created : " + film.getId());
        return new ResponseEntity<Film>(film, HttpStatus.CREATED);
    }

    /**
     *
     * @param film
     * @return
     */
    @PutMapping
    public ResponseEntity<Film> updateFilm(@RequestBody Film film) {
        if ( this.filmRepository.existsById(film.getId()) ) {
            this.filmRepository.save(film);
            return new ResponseEntity<Film>(film, HttpStatus.CREATED);
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
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        Film film = this.filmRepository.findById(id).orElse(null);
        if (film != null) {
            this.filmRepository.delete(film);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}