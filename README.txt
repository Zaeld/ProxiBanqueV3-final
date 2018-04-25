# README ProxibanqueV3
Ce document décrit les démarches à effectuer afin de pouvoir lancer l’application ProxiBanqueV3 dans un environnement Windows ayant pour serveur d'application TomCat
- Auteurs: Alex DEMOLIS, Beatriz MORENO

## Pré-requis de configuration
- Installer le **JDK 8** (ou supérieur) sur la machine cible,
	- Téléchargé depuis: http://www.oracle.com/technetwork/java/javase/downloads/index.html
- Installer **Tomcat 9** sur la machine cible, 
	- Téléchargé depuis: https://tomcat.apache.org/download-90.cgil
- Installer **Wampserver 3.0.4**  (fournissant le SGBDR: MySQL et l’application web de gestion de SGBDR:  PhPMyAdmin) sur la machine cible, 
	- Téléchargé depuis: http://www.wampserver.com
- Importer la **base de données 'proxibanque'** sur MySQL à partir du fichier .sql 
	- Accéder à la page d’accueil PhPMyAdmin en tapant ‘localhost’ dans le navigateur
		- Le port utilisé doit être 3306
	- Se loger sur un compte utilisateur ayant impérativement le login utilisateur ‘root’ et le mot de passe ‘root’
	- Importer la base de données ‘proxibanque’ à partir du fichier ‘proxibanqueV3.sql’
		- Cliquer sur l’option ‘import’ du menu en haut de la page
		- Sélectionner le fichier ‘proxibanqueV3.sql’
		- Cliquer sur le bouton ‘exécuter’ pour compléter l’importation
		- Vérifier qu’une base de données nommé ‘proxibanque’ a bien été créé sur le déployeur à gauche de la page
	
## Exécution sur Tomcat
- **Démarrer** le serveur d'applications **Tomcat** en double-cliquant le fichier "startup.bat" du dossier "bin" d'installation de votre serveur Tomcat
Exemple du chemin : "C:\...\apache-tomcat-9.0.6\bin"
- **Démarrer** le serveur de base de données **Wamp** en double-cliquant sur le fichier "wampmanager.exe" du répertoire d'installation de votre serveur Wamp
- **Déployer** l’application **‘ProxiBanqueV3’** à partir du fichier *‘ProxiBanqueV3.war’*
	- Copier le fichier ‘ProxiBanqueV3.war’en local sur la machine dans le dossier "webapps" du répertoire d'installation 		de Tomcat,
		- Exemple du chemin : "C:\...\apache-tomcat-9.0.6\webapps"
	- Vérifier que le fichier ‘ProxiBanqueV3.war’ se dézippe dans un répertoire portant le nom du war, ce qui voudra dire 		que votre application a été bien déployée
- **Lancer** l’application **‘ProxiBanqueV3’**
	- Ouvrir un navigateur web et taper ‘localhost:8080/ProxiBanqueV3’* dans la barre d'adresse

## Utilisation
### Ecran N°1 : Authentification Conseiller
<img src="https://drive.google.com/uc?id=13emDDSlzR0uNgrbJu4ui7oDbTBK4-FZN">	

- Permet de se connecter à l'espace conseiller à l'aide d'un **identifiant** et un **mot de passe** conseiller
	- 3 conseillers existent en base de données avec comme identifiants :
		- login = theboss et password = 412563 avec 6 clients
		- login = championdu69 et password = 782541 avec 3 clients
		- login = laforce et password = 584589 avec 0 client
- Si l'identification est correcte, cet écran donne accès à la liste de clients du conseiller (écran N°2)
- Cas ERREUR..

### Ecran N°2 : Liste des clients
<img src="https://drive.google.com/uc?id=1lD7Yd7-yMoZ4rfg5N_vToicd1ErU04pD">	

- Accesible à travers de l'écran N°1
- Affiche la liste de clients du conseiller loggé 
- Pour chaque client il est possible d'acceder aux fonctionnalités suivantes à travers de boutons à coté du client ciblé:
	- Bouton **Modifier**: permet de modifier les informations du client (via l'Ecran 3)
	- Bouton **Liste de comptes**: permet d'afficher les comptes associés au client (via l'Ecran 4)
	- Bouton **Virement**: permet d'afficher les comptes associés au client (via l'Ecran 5)
	- PAS DEMANDE: Bouton **Supprimer**: permet de supprimer le client (via l'Ecran ...)
	
-  PAS DEMANDE:Le bouton **Créer un nouveau client** en bas de l'écran permet d'acceder à un formulaire d'inscription du client. Il suffit ensuite de remplir le formulaire qui s'affiche et de cliquer sur Enregistrer. Il est nécessaire de remplir tout les champs pour pouvoir enregistrer un nouveau client.
			

### Ecran N°3 : Edition d'un client
- Accesible à travers de l'écran N°2
- Affiche un formulaire permettant la modification des informations du client selectionné 
	- Ce formulaire est pre-rempli avec les informations courantes du client
	- Les champs modifiables sont: ???????????????????????????????????????,
	- Le bouton **'Modifier'** permet de valider les modifications realisées
- En cas d'ERREUR...

### Ecran N°4 : Liste comptes d'un client 
- Accesible à travers de l'écran N°2
- Affiche les comptes disponibles pour le client selectionné (épargne ou courant)
- En cas d'ERREUR...

### Ecran N°5 : Virement Compte à Compte
- Accesible à travers de l'écran N°2
- Permet d'effectuer un **virement compte à compte**
	- ayant a pour **compte emmeteur** un des comptes du client selectionné 
	- vers un **compte beneficiaire** parmi la liste de comptes des client associés au conseiller
		- La selection de comptes se fait à travers un menu deroulant
	- Le montant du virement doit être specifié par le conseiller dans le champ **Montant**
		- Il est possible d'entrer un montant négatif
		- ? Il n'est pas possible d'entrer un montant décimal en raison des technologies de front office imposées.
...
- Le bouton **Valider** permet la validation du virement

### ECRAN N°6 : Erreur
- Renvoyé en cas d'échec à partir des écrans  N°....
- Affiche un message personnalisé pour chaque erreur
- En cas d'ERREUR...

## Outils et frameworks utilisés (A METTRE A JOUR)
- Java 8
  - Servlet & HTTP
  - JSP et l'Expression Language (EL)
- Eclipse Neon
- Maven 3
- Spring 4
  - context : core, beans, aop
  - webmvc : web, expression
  - data-jpa : data-commons, orm, jdbc, tx
- Hibernate 5 (avec validator)
- Webjars
  - Bootstrap 3
  - JQuery & JQuery-UI
  - Datatables (plugin de JQuery)


## Glosaire
- SGBDR: système de gestion de bases de données relationnelles 
