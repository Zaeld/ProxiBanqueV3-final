package test;

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

import domaine.Courant;
import domaine.Epargne;
import junit.framework.TestCase;
import service.CourantService;
import service.EpargneService;
import service.GestionCompteService;

/**
 * TestMethodeVirement est un classe de tests parametrée
 * @author  Demolis A., Moreno B.
 *
 */
@RunWith(Parameterized.class)
public class TestMethodeVirement extends TestCase {
	private double soldeCourant;
	private double soldeEpargne;
	private double montantVirement;
	private double soldeCourantApres;
	private double soldeEpargneApres;
	private static Courant courant = new Courant();
	private static Epargne epargne = new Epargne();

	/**
	 * Constructeur 
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
	 * Méthode permettant d'initialiser les proprietées de la classe TestMethodeVirement
	 * @return Array de paramètres
	 */
	@Parameters
	static public List<Object[]> getParameters() {
		Object[][] parameters = { { 100000, 100000, 100000, 0, 200000 }, { -100000, 0, 100000, -200000, 100000 },
				{ 0, -100000, 100000, -100000, 0 }   };
		return Arrays.asList(parameters);
	}

	@BeforeClass
	public static void CreationCourant() {
		CourantService serviceCourant = new CourantService();
		serviceCourant.createCourant(courant);
		EpargneService serviceEpargne = new EpargneService();
		serviceEpargne.creerEpargne(epargne);
	}

	@Before
	public void before() {
		System.out.println("Début du test");
		CourantService serviceCourant = new CourantService();
		EpargneService serviceEpargne = new EpargneService();
		System.out.println(courant);

		courant = serviceCourant.getCourantNumCompte(courant);
		epargne = serviceEpargne.getEpargneNumCompte(epargne);
		courant.setSolde(soldeCourant);
		epargne.setSolde(soldeEpargne);
		System.out.println(courant);

	}

	@Test
	public void TestCréditer() {
		GestionCompteService service = new GestionCompteService();
		service.crediter(epargne, montantVirement);
		assertThat(epargne.getSolde(), IsEqual.equalTo(soldeEpargneApres));

	}

	@Test
	public void TestDébiter() {
		GestionCompteService service = new GestionCompteService();
		service.debiter(courant, montantVirement);
		assertThat(courant.getSolde(), IsEqual.equalTo(soldeCourantApres));

	}

	@Test
	public void TestVirementCourantAEpargne() {
		GestionCompteService service = new GestionCompteService();
		System.out.println(courant);

		service.virementCompteACompte(courant, epargne, montantVirement);
		System.out.println(courant);

		assertThat(courant.getSolde(), IsEqual.equalTo(soldeCourantApres));
		assertThat(epargne.getSolde(), IsEqual.equalTo(soldeEpargneApres));
	}

	@After
	public void after() {
		System.out.println("Fin du test");
	}

	@AfterClass
	public static void SupressionCourant() {
		CourantService serviceCourant = new CourantService();
		serviceCourant.deleteCourant(courant);
		EpargneService serviceEpargne = new EpargneService();
		serviceEpargne.deleteEpargne(epargne);
	}

}
