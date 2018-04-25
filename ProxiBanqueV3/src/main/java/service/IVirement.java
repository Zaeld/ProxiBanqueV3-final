package service;

import domaine.Courant;
import domaine.Epargne;

public interface IVirement {
public boolean virementCompteACompte(Courant courant, Epargne epargne, double somme);
public boolean virementCompteACompte(Courant courant, Courant courant2, double somme);
public boolean virementCompteACompte(Epargne epargne, Epargne epargne2, double somme);
public boolean virementCompteACompte(Epargne epargne, Courant courant, double somme);

}