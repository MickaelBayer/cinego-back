package fr.cesi.poec.repositories;


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
    public List<Seance> findByDateLike (Date d);
}
