package service;

import java.util.List;



import dao.CompteEpargneDAO;
import domaine.Epargne;

/**
 * @author Stagiaire
 *
 */
public class EpargneService {

	// La classe EpargneService permet de faire le lien entre la couche pr�sentation
	// et la classe CompteEpargneDAO

	// D�claration
	CompteEpargneDAO dao = new CompteEpargneDAO();

	/**
	 * @param compte
	 * @return
	 */
	public boolean creerEpargne(Epargne compte) {
		return dao.createEpargne(compte);
	}

	/**
	 * @param compte
	 * @return
	 */
	public Epargne getEpargne(Epargne compte) {
		return dao.getEpargne(compte);
	}

	/**
	 * @param compte
	 * @return
	 */
	public Epargne updateEpargne(Epargne compte) {
		return dao.updateEpargne(compte);
	}

	/**
	 * @param compte
	 * @return
	 */
	public boolean deleteEpargne(Epargne compte) {
		return dao.deleteEpargne(compte);
	}

	/**
	 * @param compte
	 * @return
	 */
	public List<Epargne> getAllEpargne(Epargne compte) {
		return dao.getAllEpargne();
	}
}
