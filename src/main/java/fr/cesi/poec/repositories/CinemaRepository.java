package fr.cesi.poec.repositories;


import fr.cesi.poec.entities.Cinema;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//l'interface Crudrepository implémente les methodes CRUD classiques
//CrudRepository<Type de l'entité, type de l'ID>
@Repository
public interface CinemaRepository extends CrudRepository<Cinema, Long> {

    @Query(value = "SELECT * FROM cinema c INNER JOIN plansalle p ON c.idcinema = p.idcinema INNER JOIN seance s ON p.idplanSalle = s.idplanSalle WHERE s.idfilm = ?1 GROUP BY c.idcinema", nativeQuery = true)
    public List<Cinema> findWithIdFilm (Long idFilm);

    @Query(value = "SELECT * FROM cinema c INNER JOIN plansalle p ON c.idcinema = p.idcinema INNER JOIN seance s ON p.idplanSalle = s.idplanSalle WHERE s.idSeance = ?1 GROUP BY c.idcinema", nativeQuery = true)
    public Cinema findWithSeance (Long idSeance);
}