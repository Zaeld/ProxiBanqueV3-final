Comment lancer l'application Java GestionFormations

Pré-requis:

		- Installer le JDK 8 (ou superieur) sur la machine cible, 
		    (Téléchargé depuis: http://www.oracle.com/technetwork/java/javase/downloads/index.html).
			
		- Installer Wampserver 3 sur la machine cible, 
		
			(Téléchargé depuis: http://www.wampserver.com/).

	    - Installer Tomcat 9 sur la machine cible, 

		    (Téléchargé depuis: https://tomcat.apache.org/download-90.cgil).

	    - Machine cible dote d'un systeme d'exploitation Windows.
		
Consulter la documentation: 

	- Les diagrammes UML se trouvent dans le dossier "Diagrammes UML" sous le format jpg
	- Les tests JUnit se trouvent dans le dossier test du module ProxiBanque-service du projet. Pour y accéder, il suffit de décompresser ProxiBanque-service-0.0.1-SNAPSHOT.jar se trouvant dans le dossier lib du dossier ProxiBanquev2 décompressé dans Tomcat. 
	- Pour consulter la javadoc, il suffit de double-cliquer sur le fichier index.html se trouvant dans les répertoires doc des différents modules.

Execution :

		- Importer le .sql dans votre serveur de gestion de base de donnée,
			
		- Lancer Tomcat en double cliquant sur le fichier "startup.bat" du dossier "bin" de Tomcat
		
			Exemple du chemin : "C:\...\apache-tomcat-9.0.6\bin"

	    - Copier le fichier ProxiBanquev2.war en local sur la machine dans le dossier "webapps" de Tomcat,
	    
			Exemple du chemin : "C:\...\apache-tomcat-9.0.6\webapps"
		
		- Tomcat décompresse le fichier .war dans le dossier "webapps"
		
	    - Dans votre navigateur, taper dans la barre de navigation : http://localhost:8080/ProxiBanquev2

Utilisation :

		- Une fois sur la page d'acceuil du site, il est nécessaire d'entrer les identifiants conseiller de l'utilisateur. Trois Conseillers existent en base de données avec comme identifiants :
			- login = theboss et password = 412563 avec 6 clients
			- login = championdu69 et password = 782541 avec 3 clients
			- login = laforce et password = 584589 avec 0 client
		
		- Une fois connecté, la liste des clients s'affiche avec les informations des clients. Sur cette page il est possible de :
		
			- Créer un client en cliquant sur "Créer un client" dans la barre de navigation en en-tête. Il suffit ensuite de remplir le formulaire qui s'affiche et de cliquer sur Enregistrer. Il est nécessaire de remplir tout les champs pour pouvoir enregistrer un nouveau client.
			
			- Afficher les comptes associés à un client et leurs informations en cliquant sur le bouton "Liste des comptes" à coté du client ciblé.
			
			- Faire un virement compte à compte depuis un compte du client vers un autre compte de la banque en cliquant sur le bouton "Virement compte à compte" à coté du client ciblé. Sur la nouvelle page, l'utilisateur doit sélectionner un compte à débiter parmis les comptes du client, sélectionner un compte à créditer dans le menu déroulant, spécifier un montant pour le virement et cliquer sur Enregistrer. Remarque : Il est possible d'entrer un montant négatif. Il n'est pas possible d'entrer un montant décimal en raison des technologies de front office imposées.

			- Modifier les informations d'un client en cliquant sur le bouton "Modifier les informations du client" à coté du client ciblé.
			
			- Supprimer un client en cliquant sur le bouton "Supprimer le client" à coté du client ciblé.
			
		- Il est possible de revenir à tout moment sur l'écran de liste des clients en cliquant sur "Acceuil" ou "Retour" selon la page en cours dans la barre de navigation en en-tête.
		
		- Il est possible à tout moment pour l'utilisateur de cliquer sur "Quitter" dans la barre de taches en en-tête pour revenir à l'écran de connexion.
		
		- Sans action de l'utilisateur pendant un temps par défaut de 30min, la session de l'utilisateur se termine.
		
Maintenance :

		- Tout les composants du projet, que ce soit le fichier war ou les fichiers contenants le code sont en ligne sur la plateforme github à l'adresse https://github.com/Zaeld/ProxiBanqueV2.git
		- L'équipe est en train de développer une nouvelle fonctionnalité permettant de modifier le statut financier des clients en fonction d'une limite de solde total, le code correspondant a déjà été en partie ajouté au projet.