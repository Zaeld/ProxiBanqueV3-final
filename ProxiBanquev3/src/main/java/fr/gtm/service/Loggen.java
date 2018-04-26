package fr.gtm.service;

import org.apache.log4j.Logger;

/** La classe Loggen contient la méthode static Logger qui instancie l'objet logger permettant l'enregistrement de log
 * @author ademolis
 *
 */
public abstract class Loggen {
	public static Logger logger = Logger.getLogger(Loggen.class);
}
