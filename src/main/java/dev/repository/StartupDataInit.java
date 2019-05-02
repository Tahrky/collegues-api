/**
 * 
 */
package dev.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.entities.Collegue;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@Component
public class StartupDataInit {
    @Autowired
    CollegueRepository collegueRepo;
    static final String TIME_PATTERN = "yyyy-MM-d";

    // La méthode init va être invoquée au démarrage de l'application.
    @EventListener(ContextRefreshedEvent.class)
    public void init() {
	LocalDate date1 = LocalDate.parse("1950-11-14", DateTimeFormatter.ofPattern(TIME_PATTERN));

	String matriculeTemp = UUID.randomUUID().toString();
	collegueRepo.save(new Collegue(matriculeTemp, "Bob", "Robert", "a@a.a",
		"https://upload.wikimedia.org/wikipedia/commons/thumb/c/cf/Red_Brocket_%28Mazama_americana%29_-_Flickr_-_berniedup.jpg/1024px-Red_Brocket_%28Mazama_americana%29_-_Flickr_-_berniedup.jpg",
		date1));

	matriculeTemp = UUID.randomUUID().toString();
	collegueRepo.save(new Collegue(matriculeTemp, "Adeline", "Noelle", "b@b.b",
		"https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Vicugna-ESO.jpg/1024px-Vicugna-ESO.jpg",
		date1));

	matriculeTemp = UUID.randomUUID().toString();
	collegueRepo.save(new Collegue(matriculeTemp, "Bob", "Arthur", "c@c.c",
		"https://wildplanetphotomagazine.com/wp-content/uploads/2016/09/Pangolin-in-colour.jpg", date1));

	collegueRepo.save(new Collegue("1", "Bobbi", "Albert", "a@a.a",
		"https://www.chiens-de-france.com/photo/chiens/2016_08/chiens-Levrier-afghan-4e556834-b145-ab44-fd3e-eacdd072db00.jpg",
		date1));
    }
}
