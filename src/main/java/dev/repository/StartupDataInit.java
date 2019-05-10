/**
 *
 */
package dev.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.entities.Collegue;
import dev.entities.UtilisateurSession;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@Component
public class StartupDataInit {
    @Autowired
    CollegueRepository collegueRepo;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    static final String TIME_PATTERN = "yyyy-MM-d";

    // La méthode init va être invoquée au démarrage de l'application.
    @EventListener(ContextRefreshedEvent.class)
    public void init() {
	LocalDate date1 = LocalDate.parse("1950-11-14", DateTimeFormatter.ofPattern(TIME_PATTERN));

	String matriculeTemp = UUID.randomUUID().toString();
	collegueRepo.save(new Collegue(matriculeTemp, "Bob", "Robert", "a@a.a", "https://tinyurl.com/y5m5tzmc", date1));

	matriculeTemp = UUID.randomUUID().toString();
	collegueRepo.save(new Collegue(matriculeTemp, "Adeline", "Noelle", "b@b.b",
		"https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Vicugna-ESO.jpg/1024px-Vicugna-ESO.jpg",
		date1));

	matriculeTemp = UUID.randomUUID().toString();
	collegueRepo.save(new Collegue(matriculeTemp, "Bob", "Arthur", "c@c.c", "https://tinyurl.com/y4jpek4r", date1));

	collegueRepo.save(new Collegue("1", "Bobbi", "Albert", "aa@a.a",
		"https://www.chiens-de-france.com/photo/chiens/2016_08/chiens-Levrier-afghan-4e556834-b145-ab44-fd3e-eacdd072db00.jpg",
		date1));

	collegueRepo.save(new Collegue("2", "Bobbi", "Mylène", "ab@a.a", "https://tinyurl.com/y5dj758o", date1));

	collegueRepo.save(new Collegue("3", "Paul", "Gurpratap Singh", "god@himself.universe",
		"https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Ara_ararauna_-Alabama_Zoo-6.jpg/732px-Ara_ararauna_-Alabama_Zoo-6.jpg",
		date1));

	collegueRepo.save(new Collegue("4", "Paul", "Petit", "small@paul.india",
		"https://cdn-media.rtl.fr/cache/M8_VtQImevlQYitJXvaCjg/880v587-0/online/image/2014/0122/7769048639_un-lionceau-en-colombie-le-25-octobre-2012-illustration.jpg",
		date1));

	matriculeTemp = UUID.randomUUID().toString();
	collegueRepo.save(
		new Collegue(matriculeTemp, "Bobby", "Alicia", "azea@a.a", "https://tinyurl.com/y5htjhfb", date1));

	matriculeTemp = UUID.randomUUID().toString();
	collegueRepo
	.save(new Collegue(matriculeTemp, "Bobby", "Mickael", "ba@a.a", "https://tinyurl.com/yyzcpp4s", date1));

	matriculeTemp = UUID.randomUUID().toString();
	collegueRepo
	.save(new Collegue(matriculeTemp, "Bon", "Jean", "bafd@a.a", "https://tinyurl.com/y6zf8gxz", date1));

	matriculeTemp = UUID.randomUUID().toString();
	collegueRepo.save(
		new Collegue(matriculeTemp, "Jean", "Racine", "bazeqa@a.a", "https://tinyurl.com/y462zoed", date1));

	matriculeTemp = UUID.randomUUID().toString();
	collegueRepo.save(new Collegue(matriculeTemp, "Jeltsch", "Julie", "ba@a.a",
		"https://www.enviesanimales.fr/img/cms/Page%20Guides%20&%20conseils/TT/TT%20faite%20pour%20moi/Tortue%20maison.jpg",
		date1));

	utilisateurRepository.save(new UtilisateurSession(collegueRepo.findById("1").orElseThrow(Error::new), passwordEncoder.encode("pass1"), Arrays.asList("ROLE_ADMIN", "ROLE_USER")));
	utilisateurRepository.save(new UtilisateurSession(collegueRepo.findById("2").orElseThrow(Error::new),  passwordEncoder.encode("pass2"), Arrays.asList("ROLE_USER")));
    }
}
