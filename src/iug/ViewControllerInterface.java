package iug;

public interface ViewControllerInterface 
{
	//vide l'affichage de carte (fin pli)
	public void effacerCartes();
	
	//affiche la carte correspondant a valeurcouleur
	public void afficherCarte(String s);
	
	//Affiche que le joueur i a coincher
	public void coinche(int i);

	//met a jour l'affichage du joueur en cour
	public void joueurEnCours(int i);
	
	//affiche fin de parti
	public void partieTerminer();
	
	//suprime les message afficher
	public void supprimerMessage();
	
	//Passe en mode annonce sur l'interface
	public void modeAnnonce();
	
	//Passe en mode jeu sur l'interface
	public void modeJeu();
	
	//Efface l'affichage de l'annonce
	public void effaceAnnonce();

	//Affiche une contrecoinche
	public void contreCoinche();

	//Affiche l'annonce avec la valeur et couleur
	public void afficheAnnonce(String val, String couleur);
	
	//Actualise l'annonce après modification
	public void actualiseAnnonce(String valeur, String couleur);
	
	//Après validation des valeur
	public void valideValeurAnnonce();
	
	//annonce du joueur distant
	public void annonceJoueurDistant(String valeur, String couleur);
	
	//Initialisation des menus
	public void init(ImageMenu imageMenuGauche, ImageMenu imageMenuCentre, ImageMenu imageMenuDroite);
	
	//faire d�filer les menus vers la droite
	public void droite(ImageMenu imageMenuNew);
	
	//faire d�filer les menus vers la gauche
	public void gauche(ImageMenu imageMenuNew);
	
	//passer en mode menu
	public void modeMenu();
	
	//passer en mode partie
	public void modePartie();
	
	//passer en mode options de jeu
	public void modeOption();
	
	//valide le menu au centre
	public void validerMenu();
	
	//accede � l'option k
	public void option(int k);
	
	//modifie option 1
	public void modifOption1();
	
	//modifie option 2
	public void modifOption2();
	
	//modifie option 3
	public void modifOption3(int option3);
	
	//option en cours
	public int optionEnCours();
	
	//valide les options
	public void validerOptions();
	
	//d�but distribution
	public void distribution(int k);
	
	//message faites vos annonces
	public void faireAnnonce();
	
	//affichage annonce carte
	public void annonceCarte(String string);
	
	//mode quitter
	public void modeQuitter(int sec);
	
	//marque la seconde pour l'attente du joueur distant
	public void timeOutJD(int sec);
	
	//affiche les points � la fin de la manche
	public void finManche(int points1, int points2, int pointsAnnonce1, int pointsAnnonce2, int pointsPartie1, int pointsPartie2, int pointsTotal1, int pointsTotal2, String annoncelol);


	public void messageSettings(String message);

	public void modeSettings();

	public void modeAPropos();

	public void annulleCouleurAnnonce();

}
