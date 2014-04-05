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
	
	//Actualise l'annonce aprÃ¨s modification
	public void actualiseAnnonce(String valeur, String couleur);
	
	//AprÃ¨s validation des valeur
	public void valideValeurAnnonce();
	
	//annonce du joueur distant
	public void annonceJoueurDistant(String valeur, String couleur);
	
	//Initialisation des menus
	public void init(ImageMenu imageMenuCentre, ImageMenu imageMenuGauche, ImageMenu imageMenuDroite);
	
	//faire défiler les menus vers la droite
	public void droite(ImageMenu imageMenuNew);
	
	//faire défiler les menus vers la gauche
	public void gauche(ImageMenu imageMenuNew);
	
	//passer en mode menu
	public void modeMenu();
	
	//passer en mode partie
	public void modePartie();
	
	//passer en mode options de jeu
	public void modeOption();
	
	//valide le menu au centre
	public void validerMenu();
	
	//accede à l'option k
	public void option(int k);
	
	//modifie option 1
	public void modifOption1();
	
	//modifie option 2
	public void modifOption2();
	
	//modifie option 3
	public void modifOption3(int option3);
}
