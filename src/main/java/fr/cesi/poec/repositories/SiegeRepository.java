package fr.cesi.poec.repositories;


import fr.cesi.poec.entities.Siege;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//l'interface Crudrepository implémente les methodes CRUD classiques
//CrudRepository<Type de l'entité, type de l'ID>
@Repository
public interface SiegeRepository extends CrudRepository<Siege, Long> {

        @Query(value = "SELECT * FROM siege s WHERE s.idSeance = ?1 GROUP BY s.idsiege", nativeQuery = true)
        public List<Siege> findWithSeance (Long idSeance);
}
