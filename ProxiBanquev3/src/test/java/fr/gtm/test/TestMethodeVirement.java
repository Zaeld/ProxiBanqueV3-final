package fr.gtm.test;

import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.gtm.domaine.Courant;
import fr.gtm.domaine.Epargne;
import fr.gtm.service.CourantService;
import fr.gtm.service.EpargneService;
import fr.gtm.service.GestionCompteService;
import junit.framework.TestCase;

/**
 * TestMethodeVirement est un classe de tests parametree pour tester la methode
 * virementCompteACompte
 * 
 * @author Demolis A., Moreno B.
 *
 */
@RunWith(Parameterized.class)
public class TestMethodeVirement extends TestCase {

	// Declaration
	private double soldeCourant;
	private double soldeEpargne;
	private double montantVirement;
	private double soldeCourantApres;
	private double soldeEpargneApres;
	private static Courant courant = new Courant();
	private static Epargne epargne = new Epargne();
	static CourantService serviceCourant = new CourantService();
	static EpargneService serviceEpargne = new EpargneService();
	GestionCompteService service = new GestionCompteService();

	/**
	 * Constructeur parametre
	 * 
	 * @param soldeCourant
	 * @param soldeEpargne
	 * @param montantVirement
	 * @param soldeCourantApres
	 * @param soldeEpargneApres
	 */
	public TestMethodeVirement(double soldeCourant, double soldeEpargne, double montantVirement,
			double soldeCourantApres, double soldeEpargneApres) {
		super();
		this.soldeCourant = soldeCourant;
		this.soldeEpargne = soldeEpargne;
		this.montantVirement = montantVirement;
		this.soldeCourantApres = soldeCourantApres;
		this.soldeEpargneApres = soldeEpargneApres;
	}

	/**
	 * Methode permettant d'initialiser les proprietees des objets
	 * TestMethodeVirement
	 * 
	 * @return Array de paramÃ¨tres
	 */
	@Parameters
	static public List<Object[]> getParameters() {
		Object[][] parameters = { { 100000, 100000, 100000, 0, 200000 }, { -100000, 0, 100000, -200000, 100000 },
				{ 0, -100000, 100000, -100000, 0 } };
		return Arrays.asList(parameters);
	}

	@BeforeClass
	public static void CreationCourant() {
		// Creation des objets en base de donnee au debut des tests
		serviceCourant.createCourant(courant);
		serviceEpargne.creerEpargne(epargne);
	}

	@Before
	public void before() {
		// Affectation des attributs des objets avec les parametres du test avant chaque
		// tests
		System.out.println("Debut du test");
		System.out.println(courant);
		courant = serviceCourant.getCourantNumCompte(courant);
		epargne = serviceEpargne.getEpargneNumCompte(epargne);
		courant.setSolde(soldeCourant);
		epargne.setSolde(soldeEpargne);

	}

	// Test de la methode crediter
	@Test
	public void TestCrediter() {
		service.crediter(epargne, montantVirement);
		assertThat(epargne.getSolde(), IsEqual.equalTo(soldeEpargneApres));

	}

	// Test de la methode debiter
	@Test
	public void TestDebiter() {
		service.debiter(courant, montantVirement);
		assertThat(courant.getSolde(), IsEqual.equalTo(soldeCourantApres));

	}

	// Test de la methode virementCourantAEpargne
	@Test
	public void TestVirementCourantAEpargne() {

		service.virementCompteACompte(courant, epargne, montantVirement);

		assertThat(courant.getSolde(), IsEqual.equalTo(soldeCourantApres));
		assertThat(epargne.getSolde(), IsEqual.equalTo(soldeEpargneApres));
	}

	@After
	public void after() {
		System.out.println("Fin du test");
	}

	// Suppression des objets en base de donnee
	@AfterClass
	public static void SupressionCourant() {
		serviceCourant.deleteCourant(courant);
		serviceEpargne.deleteEpargne(epargne);
	}

}
