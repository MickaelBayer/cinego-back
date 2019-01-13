package fr.cesi.poec.repositories;


import fr.cesi.poec.entities.Film;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//l'interface Crudrepository implémente les methodes CRUD classiques
//CrudRepository<Type de l'entité, type de l'ID>
@Repository
public interface FilmRepository extends CrudRepository<Film, Long> {

    @Query(value = "SELECT * FROM film f INNER JOIN seance s ON f.idfilm = s.idfilm INNER JOIN plansalle p ON s.idplansalle = p.idplansalle WHERE p.idcinema = ?1 GROUP BY f.IDFilm", nativeQuery = true)
    public List<Film> findWithIdCinema (float idCinema);
}
