/**
* Ce programme permet de jouer au Wythoff a deux joueur contre joueur.
* @author LEFEVRE Nathan
*/

class Wythoff2 {
	void principal () {
		//char[][] plateau = new char[8][8];
		//affichePlateau(plateau);
		//positionGagnant(plateau);
		//affichePlateau(plateau);
		
		
		/*int nb = 0;
		while (nb <= 50){
			int direction = (int)(Math.random() *3));
			System.out.println("direction " + direction);
			nb++;
		}*/
		
		jouer();
		//testChangeJoueur();
		//testAGagner();
	}
	
	/**
	* Affichage du plateau de Whythoff avec les indices de lignes
	* et de colonnes
	* @param plateau le tableau a afficher
	*/
	void affichePlateau(char[][] plateau){
		
		for(int i = plateau.length-1; i >= 0; i--){
			System.out.print(i + "	");
			System.out.print("|");
			for(int j = 0; j < plateau[i].length; j++){
				System.out.print(" " + plateau[i][j] + " |");
			}
			System.out.println();
		}
		System.out.print("	");
		for(int k = 0; k < plateau.length; k++){
			System.out.print("  " + k + " ");
		}
		System.out.println();
	}
	
	/**
	* choisi un placement pour le premire point
	* @param plateau le tableau du jeux
	* @return un tableau avec les coordonnes du pion 
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
	* Créer un plateau de jeu carré rempli de caractere espace ’ ’
	* @param lg taille du plateau (lg < 10 : pas à vérifier)
	* @return tableau de caractere en deux dimensions
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
	* mets à jouer le plateau avec les cordonnées données
	* @param plateau le plateau
	* @param tabCoordonne les coordonnes du pion
	* @return tableau de caractere en deux dimensions
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
	* le bot fait des déplacements au hasard 
	* @param plateau le plateau de jeu
	* @param joueur le nom de la machine
	* @param coordonnePion les coordonnées du pion d'avant
	* @return nouveauCoordonne les nouveaux coordonne du pion
	*/
	
	int[] deplacementAleatoire(char[][] plateau, String joueur , int[] coordonnePion){
		int[] nouveauCoordonne = new int [2];
		int direction = (int) (Math.random() *3);
		int aleatoireLigne = (int)(Math.random() *(coordonnePion[1]));
		int aleatoireColonne = (int)(Math.random() *(coordonnePion[0]));
		int ligne = 0;
		int colonne = 0;
		boolean botGagne = false;
		//System.out.println("aleatoireLigne " + aleatoireLigne);
		//System.out.println("aleatoireColonne " + aleatoireColonne);
		
		if (coordonnePion[0] == 0 || coordonnePion[1] == 0){
			ligne = 0;
			colonne = 0;
			botGagne = true;
		}
		/*il choisit la direction "bas"*/
		if (direction == 0 && !botGagne){
			ligne = aleatoireColonne;
			colonne = coordonnePion[1];
		/*il choisit la direction "gauche"*/
		}else if (direction == 1 && !botGagne){
			ligne = coordonnePion[0];
			colonne = aleatoireLigne;
		/*il choisit la direction "diagonale"*/
		}else if (direction == 2 && !botGagne){
			if (aleatoireLigne < aleatoireColonne){
				ligne = coordonnePion[0] - aleatoireColonne;
				colonne = coordonnePion[1] - aleatoireColonne;
			}else{
				ligne = coordonnePion[0] - aleatoireLigne;
				colonne = coordonnePion[1] - aleatoireLigne;
			}
		}
		//System.out.println("direction " + direction);
		//System.out.println("botGagne " + botGagne);
		//System.out.println("ligne " + ligne);
		//System.out.println("colonne " + colonne);
		System.out.println("L'ordinateur va jouer :");
		System.out.println();
		nouveauCoordonne[0] = ligne;
		nouveauCoordonne[1] = colonne;
		return nouveauCoordonne;
	}
	/**
	* le bot fait des déplacements au hasard 
	* @param plateau le plateau de jeu
	* @param coordonnePion les coordonnes du pion
	* @return nouveauCoordonne les nouveaux coordonne du pion
	*/
	
	char[][] positionGagnant(char[][] plateau, int[] coordonnePion){
		double nombreOr = (1 + Math.sqrt(5)) / 2;
		int rand =0;
		int abscisse = 0;
		int ordonnee = 0;
		int i = coordonnePion[0];
		int j = coordonnePion[1];
		/*Si je trouve une position gagnante avec le nombre d'or je met un X*/
		while (abscisse < plateau.length && ordonnee < plateau.length){
			plateau[abscisse][ordonnee] = 'X';
			plateau[ordonnee][abscisse] = 'X';
			//System.out.println("abscisse " + abscisse);
			//System.out.println("ordonnee " + ordonnee);
			rand++;
			abscisse = (int) Math.floor(rand * nombreOr);
			ordonnee = (int) Math.floor(rand * (nombreOr * nombreOr));
		}
		plateau[i][j] = 'O';
		return plateau;
	}
	/**
	* le bot fait des déplacements au hasard 
	* @param plateau le plateau de jeu
	* @param joueur le nom de l'ordinateur
	* @param coordonnePion les coordonne du pion
	* @return nouveauCoordonne les nouveaux coordonne du pion
	*/
	int[] deplacerPionOrdinateur(char[][] plateau, String joueur, int[]coordonnePion){
		int[] nouveauCoordonne = new int[2];
		int[] coordonneGagnantLigne = new int[2];
		int[] coordonneGagnantColonne = new int[2];
		int[] coordonneGagnantDiagonale = new int[2];
		boolean GagnantLigne = false;
		boolean GagnantColonne = false;
		boolean GagnantDiagonale = false;
		int compteurAvancerLigne = 0;
		int compteurAvancerColonne = 0;
		int compteurAvancerDiagonale = 0;
		int i = coordonnePion[0];
		int j = coordonnePion[1];
		
		/*il recherche une position gagnant dans la ligne*/
		while(j >= 0 && GagnantLigne == false){
			if(plateau[i][j] == 'X'){
				coordonneGagnantLigne[0] = i;
				coordonneGagnantLigne[1] = j;
				GagnantLigne = true;
			}
			compteurAvancerLigne = compteurAvancerLigne +1;
			//System.out.println(" j " + j);
			j = j -1;
		}
		/*si il ne trouve pas de position gagnant on remet le compteur a 0*/
		if (GagnantLigne == false){
			compteurAvancerLigne = 0;
		}
		
		/*il recherche une position gagnant dans la colonne*/
		i = coordonnePion[0];
		j = coordonnePion[1];
		while(i >= 0 && GagnantColonne == false){
			if(plateau[i][j] == 'X'){
				coordonneGagnantColonne[0] = i;
				coordonneGagnantColonne[1] = j;
				GagnantColonne = true;
			}
			compteurAvancerColonne = compteurAvancerColonne +1;
			//System.out.println(" i " + i);
			i = i -1;
		}
		
		/*si il ne trouve pas de position gagnant on remet le compteur a 0*/
		if (GagnantColonne == false){
			compteurAvancerColonne = 0;
		}
		
		/*il recherche une position gagnant dans la diagonale*/
		i = coordonnePion[0];
		j = coordonnePion[1];
		while(i >= 0 && j >= 0 && GagnantDiagonale == false){
			if(plateau[i][j] == 'X'){
				coordonneGagnantDiagonale[0] = i;
				coordonneGagnantDiagonale[1] = j;
				GagnantDiagonale = true;
			}
		
			compteurAvancerDiagonale = compteurAvancerDiagonale +1;
			//System.out.println(" i " + i);
			//System.out.println(" j " + j);
			i = i -1;
			j = j -1;
		}
		
		/*si il ne trouve pas de position gagnant on remet le compteur a 0*/
		if (GagnantDiagonale == false){
			compteurAvancerDiagonale = 0;
		}
		
		//System.out.println(" compteurAvancerLigne " + compteurAvancerLigne);
		//System.out.println(" compteurAvancerColonne " + compteurAvancerColonne);
		//System.out.println(" compteurAvancerDiagonale " + compteurAvancerDiagonale);
		
		/*Il prend le compteur le plus grand pour avancer de plus de cases possible*/
		if(compteurAvancerLigne > compteurAvancerColonne && compteurAvancerLigne > compteurAvancerDiagonale){
			System.out.println("L'ordinateur va jouer :");
			System.out.println();
			nouveauCoordonne = coordonneGagnantLigne;
		}else if(compteurAvancerColonne > compteurAvancerLigne && compteurAvancerColonne > compteurAvancerDiagonale){
			System.out.println("L'ordinateur va jouer :");
			System.out.println();
			nouveauCoordonne = coordonneGagnantColonne;
		}else{
			System.out.println("L'ordinateur va jouer :");
			System.out.println();
			nouveauCoordonne = coordonneGagnantDiagonale;
		}
		
		/*si je n'ai pas trouvé de placement gagnante l'ordinateur fait un déplacement aleatoire*/
		if (GagnantLigne == false && GagnantColonne == false && GagnantDiagonale == false){
			System.out.println("L'ordinateur n'a pas trouvé de position gagnante, tirage aleatoire. ");
			nouveauCoordonne = deplacementAleatoire(plateau, joueur, coordonnePion);
		}
		
		return nouveauCoordonne;
	}
	
	/**
	* Lance une partie de Wythoff
	*/
	void jouer(){
		int compteurScoreJoueur1 = 0;
		int compteurScoreJoueur2 = 0;
		int compteurNiveauMoyen = 0;
		char recommencerPartie;
		int modeDeJeux;
		String joueur1;
		String joueur2;
		String joueurCourant;
		int quiCommence;
		int taille;
		char[][] plateau;
		int[] pion;
		int niveauDifficulter;
		
		do{
			
			modeDeJeux = SimpleInput.getInt("Quel mode de jeux voulez-vous entre joueur VS joueur (0) ou joueur VS ordinateur (1) : ");
			if(modeDeJeux == 0){
				/* saisie du nom du joueur qui joue en premier*/
				joueur1 = SimpleInput.getString("Nom du joueur numéro 1 : ");
				/* saisie du nom du joueur qui joue en deuxième*/
				joueur2 = SimpleInput.getString("Nom du joueur numéro 2 : ");
				/* pour choisir quel joueur va commancer */
				quiCommence = SimpleInput.getInt("Qui commence entre : " + joueur1 + " (0) et " + joueur2 + " (1) : ");
				/*Met le premier joueur a jouer dans joueurCourant*/
				if (quiCommence == 0){
					joueurCourant = joueur1;
				}else{
					joueurCourant = joueur2;
				}
				/*Demande une taille de plateau à l’utilisateur*/
				taille = SimpleInput.getInt("Choisir la taille d'un plateau : ");
				/*Création du jeu*/
				plateau = creerPlateau(taille);
				/*pion de départ*/
				pion = pointDepart(plateau);
				/*Met a jour le plateau*/
				plateau = updateTab(plateau,pion);
				/*Affichage du tableau*/
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
				
				System.out.println();
				/*Le compteur du joueur gagnant ingremente de 1 a chaque partie*/
				if (changeJoueur(joueurCourant, joueur1, joueur2) == joueur1){
					compteurScoreJoueur1 = compteurScoreJoueur1 + 1;
				}else{
					compteurScoreJoueur2 = compteurScoreJoueur2 + 1;
				}
				
			}else{

				niveauDifficulter = SimpleInput.getInt("De quel niveau voulez-vous l'ordinateur facile (1), moyen (2) ou difficile (3) : ");
				/* saisie du nom du premier joueur*/
				joueur1 = SimpleInput.getString("Nom du joueur numéro 1 : ");
				/* saisie du nom du deuxième joueur*/
				joueur2 = "bot";
				/* pour choisir quel joueur va commancer */
				quiCommence = SimpleInput.getInt("Qui commence entre : " + joueur1 + " (0) et l'ordinateur (1) ? ");
				/*Met le premier joueur a jouer dans joueurCourant*/
				if (quiCommence == 0){
					joueurCourant = joueur1;
				}else{
					joueurCourant = joueur2;
				}
				
				/*Demande une taille de plateau à l’utilisateur*/
				taille = SimpleInput.getInt("Choisir la taille d'un plateau : ");
				/*Création du jeu*/
				plateau = creerPlateau(taille);
				/*pion de départ*/
				pion = pointDepart(plateau);
				/*Met a jour le plateau*/
				plateau = updateTab(plateau,pion);
				/*Montre les positions gagnant*/
				plateau = positionGagnant(plateau,pion);
				/*Affichage du tableau*/
				affichePlateau(plateau);
					
				/*si le niveau de difficulté est facile, tu joue contre un ordinateur avec que des choix aléatoires*/
				if (niveauDifficulter == 1){
					
					/*Boucle de jeu : elle continue tant que la conditions de fin n’est pas atteinte*/
					while(aGagner(plateau) == false){
						/*Si c est à l'ordinateur de jouer il se deplace tout seul*/
						if (joueurCourant == "bot"){
							pion = deplacementAleatoire(plateau,joueurCourant, pion);
						}else{
							/*le joueur choisi ses déplacement*/
							pion = deplacerPion(plateau,joueurCourant, pion);
						}
						
						/*Changement de joueur*/
						joueurCourant = changeJoueur(joueurCourant, joueur1, joueur2);
						/*Met a jour le plateau*/
						plateau = updateTab(plateau,pion);
						/*Montre les positions gagnant*/
						plateau = positionGagnant(plateau,pion);
						/*Affichage du tableau*/
						affichePlateau(plateau);
					}
				/*si le niveau de difficulté est moyen, */	
				}else if (niveauDifficulter == 2){
					
					/*Boucle de jeu : elle continue tant que la conditions de fin n’est pas atteinte*/
					while(aGagner(plateau) == false){
						/*Si c est à l'ordinateur de jouer il se deplace tout seul*/
						if(joueurCourant == "bot" && compteurNiveauMoyen <= 1){
							pion = deplacementAleatoire(plateau,joueurCourant, pion);
							
						}else if (joueurCourant == "bot" && compteurNiveauMoyen > 1){
							pion = deplacerPionOrdinateur(plateau,joueurCourant, pion);
							
						}else{
							/*le joueur choisi ses déplacement*/
							pion = deplacerPion(plateau,joueurCourant, pion);
						}
						
						/*Changement de joueur*/
						joueurCourant = changeJoueur(joueurCourant, joueur1, joueur2);
						/*Met a jour le plateau*/
						plateau = updateTab(plateau,pion);
						/*Montre les positions gagnant*/
						plateau = positionGagnant(plateau,pion);
						/*Affichage du tableau*/
						affichePlateau(plateau);
						compteurNiveauMoyen = compteurNiveauMoyen +1;
					}
					compteurScoreJoueur2 = 0;
						
				/*si le niveau de difficulté est difficile, tu joue contre un ordinateur avec des choix gagnants dès que possibles*/
				}else{
					
					/*Boucle de jeu : elle continue tant que la conditions de fin n’est pas atteinte*/
					while(aGagner(plateau) == false){
						/*Si c est à l'ordinateur de jouer il se deplace tout seul*/
						if (joueurCourant == "bot"){
							pion = deplacerPionOrdinateur(plateau,joueurCourant, pion);
						}else{
							/*le joueur choisi ses déplacement*/
							pion = deplacerPion(plateau,joueurCourant, pion);
						}
						
						/*Changement de joueur*/
						joueurCourant = changeJoueur(joueurCourant, joueur1, joueur2);
						/*Met a jour le plateau*/
						plateau = updateTab(plateau,pion);
						/*Montre les positions gagnant*/
						plateau = positionGagnant(plateau,pion);
						/*Affichage du tableau*/
						affichePlateau(plateau);
						
					}
				}
				System.out.println();
				/*Le compteur du joueur gagnant ingremente de 1 a chaque partie*/
				if (changeJoueur(joueurCourant, joueur1, joueur2) == joueur1){
					compteurScoreJoueur1 = compteurScoreJoueur1 + 1;
				}else{
					compteurScoreJoueur2 = compteurScoreJoueur2 + 1;
				}
			}
			
			/* Affichage du gagnant*/
			System.out.println("Waw quel beau match le gagnant est " + changeJoueur(joueurCourant, joueur1, joueur2) + " !!");
			System.out.println();
			System.out.println("Et le score est de [" + compteurScoreJoueur1 + "] pour " + joueur1 + " et de [" + compteurScoreJoueur2 + "] pour " + joueur2);
			/*Demande à l'utilisateur si il veux rejouer*/
			recommencerPartie = SimpleInput.getChar("Est ce que vous voulez rejouer un partie contre l'ordinateur (oui(o) ou non(n)) : ");
			
		}while(recommencerPartie == 'o');
		System.out.println("Le score final est de [" + compteurScoreJoueur1 + "] pour " + joueur1 + " et de [" + compteurScoreJoueur2 + "] pour " + joueur2);
	}
}
