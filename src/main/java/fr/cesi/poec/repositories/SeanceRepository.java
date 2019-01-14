package fr.cesi.poec.repositories;


import fr.cesi.poec.entities.Film;
import fr.cesi.poec.entities.Seance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


//l'interface Crudrepository implémente les methodes CRUD classiques
//CrudRepository<Type de l'entité, type de l'ID>
@Repository
public interface SeanceRepository extends CrudRepository<Seance, Long> {

    //Query(value = "SELECT * FROM seance WHERE dateseance LIKE %?1%", nativeQuery = true)
    //public List<Seance> findByDateLike (Date d);

    @Query(value = "SELECT * FROM seance c WHERE c.idfilm = ?1 GROUP BY c.idseance", nativeQuery = true)
    public List<Seance> findWithFilm(Long idFilm);

    @Query(value = "SELECT * FROM seance c, plansalle p WHERE c.idfilm = ?2 AND c.idplansalle = p.idplansalle AND p.idcinema = ?1 GROUP BY c.idseance", nativeQuery = true)
    public List<Seance> findWithIdCinemaAndIdFilm (Long idCinema, Long idFilm);
}
