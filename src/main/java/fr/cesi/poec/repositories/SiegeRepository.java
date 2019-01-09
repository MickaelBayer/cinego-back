package fr.cesi.poec.repositories;


        import fr.cesi.poec.entities.Siege;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Repository;


//l'interface Crudrepository implémente les methodes CRUD classiques
//CrudRepository<Type de l'entité, type de l'ID>
@Repository
public interface SiegeRepository extends CrudRepository<Siege, Long> {
}
