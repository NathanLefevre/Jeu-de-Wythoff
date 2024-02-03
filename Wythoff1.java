/**
* Ce programme permet de jouer au Wythoff a deux joueur contre joueur.
* @author LEFEVRE Nathan
*/

class Wythoff1 {
	void principal () {
		//char[][] plateau = new char [4][4];
		//affichePlateau(plateau);
		//testCreerPlateau();
		//testChangeJoueur();
		//testAGagner();
		jouer();
	}
	
	/**
	* Affichage du plateau de Wythoff avec les indices de lignes
	* et de colonnes
	* @param plateau le tableau a afficher
	*/
	void affichePlateau(char[][] plateau){
		
		for(int i = plateau.length-1; i >= 0; i--){
			System.out.print(i + " ");
			System.out.print("|");
			for(int j = 0; j < plateau[i].length; j++){
				System.out.print(" " + plateau[i][j] + " |");
			}
			System.out.println();
		}
		System.out.print("  ");
		for(int k = 0; k < plateau.length; k++){
			System.out.print("  " + k + " ");
		}
		System.out.println();
	}
	
	/**
	* choisi un placement pour le premire point
	* @param plateau le tableau du jeux
	* @return coordonne un tableau avec les coordonnes du pion 
	*/
	int[] pointDepart(char[][] plateau){
		int [] coordonne = new int [2];
		int i = 0;
		int j = 0;
		/*la machine choisit un entier entre 0 et 1 pour choisir entre la première ligne ou la dernière colonne*/
		int ligneOuColonne = (int) (Math.random() *2);
		/*si on a 0, la machine choisit un entier entre le deuxème indice du tableau et l'avant dernier de la première ligne du tableau*/ 
		if (ligneOuColonne == 0){
			i = plateau.length-1;
			j = (int) (Math.random() * (plateau.length-2)+1);
		/*si on a 1, la machine choisit un entier entre le deuxème indice du tableau et l'avant dernier de la dernière colonne du tableau*/
		}else {
			i = (int) (Math.random() * (plateau.length-2)+1);
			j = plateau.length-1;
		}
		/*enfin on met les indices choisit dans un autre tableau qu'on retourne*/
		coordonne[0] = i;
		coordonne[1] = j;
		//System.out.println(coordonne[0] + " et "  + coordonne[1]);
		return coordonne;
	}
	
	/**
	* Créer un plateau de jeu carré rempli de caractere espace ’ ’
	* @param lg taille du plateau (lg < 10 : pas à vérifier)
	* @return plateau tableau de caractere en deux dimensions
	*/
	char[][] creerPlateau(int lg){
		/*on initialise un tableau avec la même longueur en ordonnée et en abscisse*/
		char[][] plateau = new char [lg][lg];
		for(int i = 0; i < plateau.length; i++){
			for(int j = 0; j < plateau[i].length; j++){
				/*on remplace tout les cases du tableaux par des espaces*/
				plateau[i][j] = ' ';
			}
		}
		return plateau;
	}
	
	/**
	* Teste la méthode creerPlateau()
	*/
	void testCreerPlateau () {
		int longueur1 = 18;
		int longueur3 = 0;
		System.out.println ();
		System.out.println ("*** testCreerPlateau()");
		testCasCreerPlateau (longueur1);
		testCasCreerPlateau (longueur3);
	}
	/**
	* teste un appel de creerPlateau
	* @param longueur la longueur du plateau
	**/
	void testCasCreerPlateau (int longueur) {
		System.out.print ("La méthode doit rendre un plateau de " + longueur + " de longueur : ");
		char[][] resExec = creerPlateau(longueur);
		/* verfifie que la longueur est bien égale a la longueur du tableau redonner pas la méthode*/
		if (longueur == resExec.length){
			System.out.println ("OK");
		} else {
			System.err.println ("ERREUR");
		}
	}
	
	/**
	* mets à jouer le plateau avec les cordonnées données
	* @param plateau le plateau
	* @param tabCoordonne les coordonnes du pion
	* @return plateau tableau de caractere en deux dimensions
	*/
	char[][] updateTab(char[][]plateau, int[] tabCoordonne){
		int ligne = tabCoordonne[0];
		int colonne = tabCoordonne[1];
		/*on reinnitailise le tableau*/
		for(int i = 0; i < plateau.length; i++){
			for(int j = 0; j < plateau[i].length; j++){
				/*on remplace tout les cases du tableaux par des espaces*/
				plateau[i][j] = ' ';
			}
		}
		/*on rajoute au indice donner le pion*/
		plateau[ligne][colonne] = 'O';
		return plateau;
	}
	
	/**
	* Demande deux coordonnees à l’utilisateur. Si les coordonnees sont
	* correctes (dans le plateau et pas de pion déjà mis à cet endroit),
	* le caractere du joueur est ajouté au plateau sinon, on redemande
	* des coordonnées à l’utilisateur en expliquant l’erreur
	* @param plateau le plateau de jeu
	* @param joueur le nom du joueur 
	* @param coordonnePion les coordonnées du pion précédent
	* @return nouveauCoordonne les nouvelles coordonner du pion
	*/
	int[] deplacerPion(char[][] plateau, String joueur , int[] coordonnePion){
		int[] nouveauCoordonne = new int [2];
		char direction = SimpleInput.getChar("Joueur " + joueur + " : Veuillez saisir la direction entre b pour bas, g pour gauche, d pour diagonal (sans majuscule) : ");
		int nb;
		//System.out.println("direction " + direction);
		//System.out.println("ligne " + coordonnePion[0]);
		//System.out.println("colonne " + coordonnePion[1]);
		
		/*si il y a une erreur d'orthographe on redemande*/
		while(direction != 'b' && direction != 'g' && direction != 'd'){
			
				System.out.println("Il y a une erreur d'orthographe, veuillez ressaisir");
				direction = SimpleInput.getChar("Joueur " + joueur + " : Veuillez saisir la direction entre b pour bas, g pour gauche, d pour diagonal (sans majuscule) : ");
			
		}
		
		/*si les choix de l'utilisateur font sortir le pion du plateau*/
		while((direction == 'b' && coordonnePion[0] == 0) || (direction == 'g' && coordonnePion[1] == 0) || (direction == 'd' && (coordonnePion[0] == 0 || coordonnePion[1] == 0))){
			System.out.println("Vous ne pouvez plus aller à " + direction);
			direction = SimpleInput.getChar("Joueur " + joueur + " : Veuillez saisir la direction entre b pour bas, g pour gauche, d pour diagonal (sans majuscule) : ");
		}
		
		nb = SimpleInput.getInt("Joueur " + joueur + " : De combien de case voulez-vous avancer : ");
		//System.out.println("nb " + nb);
		
		/*si les choix de l'utilisateur font sortir le pion du plateau*/
		while((direction == 'b' && coordonnePion[0] - nb < 0) || (direction == 'g' && coordonnePion[1] - nb < 0) || (direction == 'd' && (coordonnePion[0] - nb < 0 || coordonnePion[1] - nb < 0))){
				System.out.println("Vous ne pouvez pas aller à " + direction + " de " + nb);
				nb = SimpleInput.getInt("Joueur " + joueur + " : De combien de case voulez-vous avancer : ");
	
		}
		
		if (direction == 'b'){
			nouveauCoordonne[0] = coordonnePion[0] - nb;
			nouveauCoordonne[1] = coordonnePion[1];
		}else if (direction == 'g'){
			nouveauCoordonne[0] = coordonnePion[0]; 
			nouveauCoordonne[1] = coordonnePion[1] - nb;
		}else if (direction == 'd'){
			nouveauCoordonne[0] = coordonnePion[0] - nb; 
			nouveauCoordonne[1] = coordonnePion[1] - nb;
		}
		//System.out.println(nouveauCoordonne[0] + " et "  + nouveauCoordonne[1]);
		return nouveauCoordonne;
	}
	
	/**
	* Change le joueur courant
	* @param joueurInitial un caractère représentant le joueur : X ou O
	* @param joueur1 le premier joueur enregistrer
	* @param joueur2 le deuxième joueur enregistrer
	* @return si le joueur ’X’ est en parametre alors renvoie ’O’sinon renvoie ’X’
	*/
	String changeJoueur(String joueurInitial, String joueur1, String joueur2){
		String joueur;
		if (joueurInitial == joueur1){
			joueur = joueur2;
		}else{
			joueur = joueur1;
		}
		return joueur;
	}
	
	/**
	* Teste la méthode changeJoueur()
	*/
	void testChangeJoueur () {
		String joueur1 = "nathan";
		String joueur2 = "leo";
		System.out.println ();
		System.out.println ("*** testChangeJoueur()");
		testCasChangeJoueur (joueur1, joueur1, joueur2, joueur2);
		testCasChangeJoueur (joueur2, joueur1, joueur2, joueur1);
	}
	
	/**
	* teste un appel de changeJoueur
	* @param joueurInitial le premier joueur à jouer
	* @param joueur1 premier joueur 
	* @param joueur2 deuxième joueur
	* @param resultat resultat attendu
	*/
	void testCasChangeJoueur (String joueurInitial, String joueur1, String joueur2, String resultat) {
		// Arrange
		System.out.print ("Le premier joueur est : " + joueurInitial + " et le joueur deux est : " + joueur2 + " donc je dois trouver \t= " + resultat + "\t : ");
		// Act
		String resExec = changeJoueur(joueurInitial, joueur1, joueur2);
		// Assert
		if (resExec == resultat){
			System.out.println ("OK");
		} else {
			System.err.println ("ERREUR");
		}
	}
	
	/**
	* Verifie si il y a un pion sur la case gagnant
	* (différentes de ’ ’)
	* @param plateau le plateau de jeu
	* @return true si case rempli, false sinon.
	*/
	boolean aGagner(char[][] plateau){
		boolean gagnant = false;
		if (plateau[0][0] == 'O' ){
			gagnant = true;
		}
		return gagnant;
	}
	
	/**
	* Teste la méthode aGagner()
	*/
	void testAGagner () {
		char[][] plateau1 = new char[3][3];
		plateau1[0][0] = 'O';
		char[][] plateau2 = new char[3][3];
		System.out.println ();
		System.out.println ("*** testAGagner()");
		testCasAGagner (plateau1, true);
		testCasAGagner (plateau2, false);
	}
	
	/**
	* teste un appel de aGagner
	* @param plateau le plataeu de la partie
	* @param resultat le resultat attentdu par la méthode
	**/
	void testCasAGagner (char[][] plateau, boolean resultat) {
		System.out.print ("Est-ce que mon plateau a un pion en [0][0] \t= " + resultat + "\t : ");
		boolean resExec = aGagner(plateau);
		/*Vérifie si il y a bien le même resultat*/
		if (resExec == resultat){
			System.out.println ("OK");
		} else {
			System.err.println ("ERREUR");
		}
	}
	
	/**
	* Lance une partie de Wythoff
	*/
	void jouer(){
		
		
		/* saisie du nom du joueur qui joue en premier*/
		String joueur1 = SimpleInput.getString("Nom du joueur numéro 1 : ");
		/* saisie du nom du joueur qui joue en deuxième*/
		String joueur2 = SimpleInput.getString("Nom du joueur numéro 2 : ");
		/* Le premier joueur a joué*/
		String joueurCourant = joueur1;
		/*Demande une taille de plateau à l’utilisateur*/
		int taille = SimpleInput.getInt("Choisir la taille d'un plateau : ");
		/*Création du jeu*/
		char[][] plateau = creerPlateau(taille);
		/*pion de départ*/
		int[] pion = pointDepart(plateau);
		plateau = updateTab(plateau,pion);
		affichePlateau(plateau);
	
		/*Boucle de jeu : elle continue tant que la conditions de fin n’est pas atteinte*/
		while(aGagner(plateau) == false){
			/*les deux joueurs jouent l’un après l’autre*/
			pion = deplacerPion(plateau,joueurCourant, pion);
			/*Changement de joueur*/
			joueurCourant = changeJoueur(joueurCourant, joueur1, joueur2);
			/*Affichage du tableau*/
			plateau = updateTab(plateau,pion);
			affichePlateau(plateau);
		}
		/* Affichage du gagnant*/
		System.out.println("Waw quel beau match le gagnant est " + changeJoueur(joueurCourant, joueur1, joueur2) + " !!");
	}
}
