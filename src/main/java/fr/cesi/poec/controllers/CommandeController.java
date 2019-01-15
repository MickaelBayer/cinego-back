package fr.cesi.poec.controllers;

import fr.cesi.poec.entities.Commande;
import fr.cesi.poec.entities.Personne;
import fr.cesi.poec.repositories.CommandeRepository;
import fr.cesi.poec.repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/commande")
public class CommandeController {

    private CommandeRepository commandeRepository;

    @Autowired
    public CommandeController(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Commande>> getCommandes() {
        return new ResponseEntity<List<Commande>>(
                (List<Commande>)this.commandeRepository.findAll(),
                HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        Commande commande = this.commandeRepository.findById(id).orElse(null);
        if (commande != null) {
            return new ResponseEntity<Commande>(commande, HttpStatus.OK);
        } else {
            return new ResponseEntity<Commande>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     *
     * @param commande
     * @return
     */
    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
        commande = this.commandeRepository.save(commande);
        System.out.println("Commande created : " + commande.getId());
        return new ResponseEntity<Commande>(commande, HttpStatus.CREATED);
    }

    /**
     *
     * @param commande
     * @return
     */
    @PutMapping
    public ResponseEntity<Commande> updateCommande(@RequestBody Commande commande) {
        if ( this.commandeRepository.existsById(commande.getId()) ) {
            this.commandeRepository.save(commande);
            return new ResponseEntity<Commande>(commande, HttpStatus.CREATED);
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
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        Commande commande = this.commandeRepository.findById(id).orElse(null);
        if (commande != null) {
            this.commandeRepository.delete(commande);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
