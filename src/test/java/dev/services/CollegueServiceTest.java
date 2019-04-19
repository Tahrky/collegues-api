/**
 * 
 */
package dev.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dev.entities.Collegue;
import dev.entities.ColleguePojo;
import dev.exception.CollegueInvalideException;
import dev.exception.CollegueNonTrouveException;
import dev.repository.CollegueRepository;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public class CollegueServiceTest
{
	CollegueRepository mockedRepository = Mockito.mock(CollegueRepository.class);
	CollegueService colServ = new CollegueService();

	String nomCorrect = "Denis";
	String prenomCorrect = "Bob";
	String emailCorrect = "email@a.com";
	String photoUrlCorrect = "http://photoUrl.com";
	String matricule = "1";

	final static String TIME_PATTERN = "yyyy-MM-d";
	LocalDate dateDeNaissanceCorrect = LocalDate.parse("1982-12-11", DateTimeFormatter.ofPattern(TIME_PATTERN));

	@Before
	public void init()
	{
		colServ.setColRepo(mockedRepository);
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_NomTropCourt()
	{
		ColleguePojo temp = new ColleguePojo("a", prenomCorrect, emailCorrect, photoUrlCorrect, dateDeNaissanceCorrect);
		Mockito.when(colServ.ajouterUnCollegue(temp)).thenThrow(new CollegueInvalideException("Nom trop court"));

		colServ.ajouterUnCollegue(temp);
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_PrenomTropCourt()
	{
		ColleguePojo temp = new ColleguePojo(nomCorrect, "b", emailCorrect, photoUrlCorrect, dateDeNaissanceCorrect);
		Mockito.when(colServ.ajouterUnCollegue(temp)).thenThrow(new CollegueInvalideException("Prenom trop court"));

		colServ.ajouterUnCollegue(temp);
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_EmailTropCourt()
	{
		ColleguePojo temp = new ColleguePojo(nomCorrect, prenomCorrect, "a@", photoUrlCorrect, dateDeNaissanceCorrect);
		Mockito.when(colServ.ajouterUnCollegue(temp)).thenThrow(new CollegueInvalideException("Email trop court"));

		colServ.ajouterUnCollegue(temp);
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_EmailSansArobase()
	{
		ColleguePojo temp = new ColleguePojo(nomCorrect, prenomCorrect, "arthur.com", photoUrlCorrect,
				dateDeNaissanceCorrect);
		Mockito.when(colServ.ajouterUnCollegue(temp)).thenThrow(new CollegueInvalideException("Email sans @"));

		colServ.ajouterUnCollegue(temp);
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_photoUrlSansHttp()
	{
		ColleguePojo temp = new ColleguePojo(nomCorrect, prenomCorrect, emailCorrect, "photo.com",
				dateDeNaissanceCorrect);
		Mockito.when(colServ.ajouterUnCollegue(temp))
				.thenThrow(new CollegueInvalideException("L'url ne contient pas http"));

		colServ.ajouterUnCollegue(temp);
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_pasMajeur()
	{
		ColleguePojo temp = new ColleguePojo(nomCorrect, prenomCorrect, emailCorrect, photoUrlCorrect, LocalDate.now());
		Mockito.when(colServ.ajouterUnCollegue(temp)).thenThrow(new CollegueInvalideException("Pas majeur"));

		colServ.ajouterUnCollegue(temp);
	}

	@Test(expected = CollegueInvalideException.class)
	public void modifierEmail_EmailTropCourt()
	{
		String emailTropCourt = "a@";

		Mockito.when(mockedRepository.findById(matricule)).thenReturn(Optional.of(new Collegue(matricule, nomCorrect,
				prenomCorrect, emailTropCourt, photoUrlCorrect, dateDeNaissanceCorrect)));
		Mockito.when(colServ.modifierEmail(matricule, emailTropCourt))
				.thenThrow(new CollegueInvalideException("Email trop court"));
		colServ.modifierEmail(matricule, emailTropCourt);
	}

	@Test(expected = CollegueInvalideException.class)
	public void modifierEmail_EmailSansArobase()
	{
		String emailSansArobase = "aaazeazea";

		Mockito.when(mockedRepository.findById(matricule)).thenReturn(Optional.of(new Collegue(matricule, nomCorrect,
				prenomCorrect, emailSansArobase, photoUrlCorrect, dateDeNaissanceCorrect)));
		Mockito.when(colServ.modifierEmail(matricule, emailSansArobase))
				.thenThrow(new CollegueInvalideException("Email sans @"));
		colServ.modifierEmail(matricule, emailSansArobase);
	}

	@Test(expected = CollegueNonTrouveException.class)
	public void modifierEmail_mauvaisMatricule()
	{
		Mockito.when(colServ.modifierEmail("2", "a@a.a")).thenThrow(new CollegueNonTrouveException());
		colServ.modifierEmail("2", "a@a.a");
	}

	@Test(expected = CollegueInvalideException.class)
	public void modifierPhotoUrl_photoUrlMauvaise()
	{
		String photoUrlMauvaise = "azeazea.com";

		Mockito.when(mockedRepository.findById(matricule)).thenReturn(Optional.of(new Collegue(matricule, nomCorrect,
				prenomCorrect, emailCorrect, photoUrlMauvaise, dateDeNaissanceCorrect)));
		Mockito.when(colServ.modifierPhotoUrl(matricule, photoUrlMauvaise))
				.thenThrow(new CollegueInvalideException("Mauvais lien, pas de http"));
		colServ.modifierPhotoUrl(matricule, photoUrlMauvaise);
	}

	@Test(expected = CollegueNonTrouveException.class)
	public void modifierPhotoUrl_mauvaisMatricule()
	{
		Mockito.when(colServ.modifierPhotoUrl("2", "http://photo.com")).thenThrow(new CollegueNonTrouveException());
		colServ.modifierPhotoUrl("2", "http://photo.com");
	}
}
