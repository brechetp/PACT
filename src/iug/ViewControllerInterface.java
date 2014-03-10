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
	
	//Actualise l'annonce aprés modification
	public void actualiseAnnonce(String valeur, String couleur);
	
	//Aprés validation des valeur
	public void valideValeurAnnonce();
	
	public void annonceJoueurDistant(String valeur, String couleur);

}
