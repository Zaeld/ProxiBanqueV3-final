# README ProxibanqueV3 
Ce document décrit les démarches à effectuer afin de pouvoir lancer l’application **ProxiBanqueV3** dans un environnement Windows ayant pour serveur d'application TomCat
- Auteurs: *Alexandre DEMOLIS*, *Beatriz MORENO*
- Societé: *Olétri conception informatique*
- Dernière release: *release N°1*

## Localisation du projet 
- https://github.com/melomanab/ProxiBanqueV3.git

## Pré-requis de configuration
- Installer le **JDK 8** (ou supérieur) sur la machine cible,
	- Téléchargé depuis: http://www.oracle.com/technetwork/java/javase/downloads/index.html
- Installer **Tomcat 9** sur la machine cible, 
	- Téléchargé depuis: https://tomcat.apache.org/download-90.cgil
- Installer **WampServer 3.0.4**  (fournissant le SGBDR: MySQL et l’application web de gestion de SGBDR:  PhPMyAdmin) sur la machine cible, 
	- Téléchargé depuis: http://www.wampserver.com
- Importer la **base de données 'proxibanque'** dans MySQL à partir du fichier ‘proxibanqueV3.sql’ founi à la racine du projet
	- Accéder à la page d’accueil WampServer en tapant ‘localhost’ dans le navigateur
		- Verifier que le port défini pour MySQL est le '3306'
	- Clicker sur le lien 'phpmyadmin' dans le menu 'Outils' en bas à gauche la page
	- Se loger sur un compte utilisateur ayant impérativement le login utilisateur ‘root’ et le mot de passe ‘root’
	- Importer la base de données ‘proxibanque’ à partir du fichier ‘proxibanquev3.sql’
		- Cliquer sur le bouton ‘Importer’ du menu en haut de la page
		- Clicker sur 'Choisir un fichier' et sélectionner l'emplacement local du fichier ‘proxibanquev3.sql’ 
		- Cliquer sur le bouton ‘Exécuter’ pour compléter l’importation
		- Vérifier qu’une base de données nommé ‘proxibanque’ a bien été créé sur le menu déroulant à gauche de la page
	

## Exécution sur Tomcat
- **Démarrer** le serveur d'applications **Tomcat** en double-cliquant le fichier 'startup.bat' du dossier 'bin' d'installation de votre serveur Tomcat
	- Exemple du chemin : "C:\...\apache-tomcat-9.0.6\bin"
- **Démarrer** le serveur de base de données **Wamp** en double-cliquant sur le fichier 'wampmanager.exe' du répertoire d'installation de votre serveur Wamp
- **Déployer** l’application **ProxiBanqueV3** à partir du fichier ‘ProxiBanqueV3.war’
	- Copier le fichier ‘ProxiBanqueV3.war’en local sur la machine dans le **dossier webapps** du répertoire d'installation 		de Tomcat,
		- Exemple du chemin : "C:\...\apache-tomcat-9.0.6\webapps"
	- Vérifier que le fichier ‘ProxiBanqueV3.war’ se dézippe dans un répertoire portant le nom du war, ce qui voudra dire 		que votre application a été bien déployée
- **Configuration de log4j**
	- Dans le répertoire 'ProxiBanqueV3' généré a partir du fichier ‘ProxiBanqueV3.war’ dans 'webapp':
		- Rentrer dans le dossier ‘WEB-INF‘, puis le dossier ‘classes‘.
		- Editer le fichier ‘log4j.properties‘ à l'aide d'un logiciel de traitement de texte tel que notepad++
		- Taper à la ligne 10 après "File=" le chemin vers le fichier log où vont être enregistré les logs.
			- Exemple du chemin : "C:\...\WEB-INF\log.log"
- **Lancer** l’application **ProxiBanqueV3**
	- Ouvrir un navigateur web et taper ‘localhost:8080/ProxiBanqueV3’* dans la barre d'adresse

## Utilisation
### Ecran N°1 : Authentification Conseiller
<img src="https://drive.google.com/uc?id=1X-KFBUHqlOupCuZHQXcBxlW2KgKCIIUC">	

- Permet de se connecter à l'espace conseiller à l'aide d'un **identifiant** et un **mot de passe** conseiller
	- 3 conseillers existent en base de données avec comme identifiants :
		- login = theboss et password = 412563 avec 6 clients
		- login = championdu69 et password = 782541 avec 3 clients
		- login = laforce et password = 584589 avec 0 client
- Si l'identification est correcte, cet écran donne accès à la liste de clients du conseiller (écran N°2)
	- De plus, l'évennement 'connection réussie' est enregistré dans un fichier .log grâce à la fonctionnalitée Log4J
- En cas d'erreur d'identification, l'écran est affiché avec le message 'Erreur d'identification. Veuillez reesayer'.
	- De plus, l'évennement 'échec de connection' est enregistré dans un fichier .log grâce à la fonctionnalitée Log4J

### Ecran N°2 : Liste des clients
<img src="https://drive.google.com/uc?id=1oy_IIxFcgy0jnftZY7DPYw-9A9kWXMJg">	

- Accesible à travers de l'écran N°1
- Affiche la liste de clients du conseiller loggé 
- Pour chaque client il est possible d'acceder aux fonctionnalités suivantes à travers de boutons à coté du client ciblé:
	- Bouton **Modifier**: permet de modifier les informations du client (via l'Ecran 3)
	- Bouton **Liste de comptes**: permet d'afficher les comptes associés au client (via l'Ecran 4)
	- Bouton **Virement**: permet d'afficher les comptes associés au client (via l'Ecran 5)
	- Bouton **Supprimer**: permet de supprimer le client selectionné

			
### Ecran N°3 : Edition d'un client
<img src="https://drive.google.com/uc?id=1wyOLzUXMb0EC1_SS8KNzy3Lp_gGo1YLu">

- Accesible à travers de l'écran N°2
- Affiche un formulaire permettant la modification des informations du client selectionné 
	- Ce formulaire est pre-rempli avec les informations courantes du client
	- Les champs modifiables sont: nom, prenom, adresse, code postal, ville, email, téléphone
	- Tous ces champs sont réquis
	- Le bouton **Valider** permet de valider les modifications realisées

### Ecran N°4 : Liste comptes d'un client 
<img src="https://drive.google.com/uc?id=1afQVRNrAPibC4YO_HO4pFdICHjWD4_iv">

- Accesible à travers de l'écran N°2
- Affiche la liste des comptes du client selectionné
	- Les informations affichées pour chache compte sont: numéro de compte, solde et type de compte (épargne ou courant) 

### Ecran N°5 : Virement Compte à Compte
<img src="https://drive.google.com/uc?id=1ozTnTGkiFmIYESscqgHCavAyXJeYK-3-">

- Accesible à travers de l'écran N°2
- Permet d'effectuer un **virement compte à compte**
	- ayant a pour **compte emmeteur** un des comptes du client selectionné 
	- vers un **compte beneficiaire** parmi la liste de comptes des client associés au conseiller
		- La selection de comptes se fait à travers un menu deroulant
	- Le montant du virement doit être specifié par le conseiller dans le champ **Montant**
	- Le bouton **Valider** permet la validation du virement
- De plus, les les informations du virement sont enregistrées dans un fichier .log grâce à la fonctionnalitée Log4J
- Cas d'erreur prévus
	- Si le montant especifié est égal à 0 EUR, une page d'erreur est affiché avec le message suivant: 'Une erreur à été rencontrée 	et l'opération n'a pas pu être effectuée'.
	- Si le compte beneficiare coincide avec le compte émetteur selectionné, une page d'erreur est affiché avec le message suivant: 	'Attention, vous ne pouvez pas faire de virement d'un compte vers lui même'.
	- Dans les deux cas, les informations sur les virements non-effectués sont enregistrées dans un fichier .log en précisant les id des comptes et le montant du virement
## Outils et frameworks
### Release 1
- Front Office: 
	- Technologie JSF 2 avec framework Mojarra 
	- Bootstrap 3
	- Primefaces
- Back Office :
	- Log4j 
	- JDBC (accès aux données)
	- PreparedStatement 
- Tests unitaires
	- JUnit
- Build
	- Maven
- Déploiement
	- Tomcat (serveur d'application)
### Release 2
- Le service authentification conseiller sera reimplementé avec la technologie **WebServices JAX-RS 2** avec Jersey et livré dans un delais de 3 jours

## Glosaire
- SGBDR: *Système de Gestion de Bases de Données Relationnelles*

