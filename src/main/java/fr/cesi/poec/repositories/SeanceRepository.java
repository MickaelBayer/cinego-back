package fr.cesi.poec.repositories;


import fr.cesi.poec.entities.Seance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


//l'interface Crudrepository implémente les methodes CRUD classiques
//CrudRepository<Type de l'entité, type de l'ID>
@Repository
public interface SeanceRepository extends CrudRepository<Seance, Long> {
}
