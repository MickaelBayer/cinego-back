package fr.cesi.poec.services;

import fr.cesi.poec.entities.Personne;
import fr.cesi.poec.repositories.PersonneRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class PersonneDetailsServiceImpl implements UserDetailsService {
    private PersonneRepository personneRepository;

    public PersonneDetailsServiceImpl(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Personne personne = personneRepository.findPersonneByMail(mail);
        if (personne == null) {
            throw new UsernameNotFoundException(mail);
        }
        return new User(personne.getMail(), personne.getMdpH5(), emptyList());
    }
}