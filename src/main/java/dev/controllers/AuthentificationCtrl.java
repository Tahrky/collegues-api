/**
 *
 */
package dev.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.entities.CollegueMatriculeNomPrenomsRoles;
import dev.entities.InfosAuthentification;
import dev.entities.UtilisateurSession;
import dev.exception.CollegueNonTrouveException;
import dev.repository.UtilisateurRepository;
import io.jsonwebtoken.Jwts;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@RestController
//@CrossOrigin(origins= {"http://localhost:4200", "https://tahrky.github.io" }, allowCredentials = "true")
public class AuthentificationCtrl {

    @Value("${jwt.expires_in}")
    private Integer EXPIRES_IN;

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Value("${jwt.secret}")
    private String SECRET;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Permet de s'authentifier, en générant un cookie pour maintenir la session en cours
    @PostMapping(value = "/auth")
    public ResponseEntity<String> authenticate(@RequestBody InfosAuthentification authenticationRequest, HttpServletResponse response) {
		// encapsulation des informations de connexion
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getMotDePasse());
	
		// vérification de l'authentification
		// une exception de type `BadCredentialsException` en cas d'informations non valides
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	
		User user = (User) authentication.getPrincipal();
	
		String rolesList = user.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(","));
	
		Map<String, Object> infosSupplementaireToken = new HashMap<>();
		infosSupplementaireToken.put("roles", rolesList);
	
		String jetonJWT = Jwts.builder()
			.setSubject(user.getUsername())
			.addClaims(infosSupplementaireToken)
			.setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
			.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET)
			.compact();
	
		Cookie authCookie = new Cookie(TOKEN_COOKIE, jetonJWT);
		authCookie.setHttpOnly(true);
		authCookie.setMaxAge(EXPIRES_IN * 1000);
		authCookie.setPath("/");
		response.addCookie(authCookie);
	
		return ResponseEntity.ok().body("coucou");
    }

    @GetMapping(value = "/me")
    public CollegueMatriculeNomPrenomsRoles getMe () {
		UtilisateurSession col = utilisateurRepository.findByCollegueEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(CollegueNonTrouveException::new);
		return new CollegueMatriculeNomPrenomsRoles(col.getCollegue().getMatricule(), col.getCollegue().getNom(), col.getCollegue().getPrenoms(), col.getRoles());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity mauvaiseInfosConnexion(BadCredentialsException e) {
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
