/**
 *
 */
package dev.config;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.entities.UtilisateurSession;
import dev.repository.UtilisateurRepository;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UtilisateurRepository utilisateurRepository;

    public UserDetailsServiceImpl(UtilisateurRepository utilisateurRepository) {
	this.utilisateurRepository = utilisateurRepository;
    }

    /** cette méthode va permettre à Spring Security d'avoir accès
     * aux informations d'un utilisateur (mot de passe, roles) à partir
     * d'un nom utilisateur.
     * L'interface UserDetails détaille le contrat attendu par Spring Security. */
    @Override
    public UserDetails loadUserByUsername(String email) {

	// Recherche d'utilisateur par son email (qui est l'identifiant d'un utilisateur)
	UtilisateurSession utilisateurTrouve = this.utilisateurRepository.findByCollegueEmail(email).orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

	// Création d'un objet User (implémentant UserDetails)
	return new User(utilisateurTrouve.getCollegue().getEmail(), utilisateurTrouve.getMotDePasse(), utilisateurTrouve.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

    }
}
